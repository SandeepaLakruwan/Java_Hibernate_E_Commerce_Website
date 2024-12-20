/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Response_DTO;
import dto.User_DTO;
import entity.Color;
import entity.Product;
import entity.Product_Category;
import entity.Product_Condition;
import entity.Product_Status;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.HibernateUtil;
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sande
 */
@MultipartConfig
@WebServlet(name = "ProductListing", urlPatterns = {"/ProductListing"})
public class ProductListing extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Response_DTO response_DTO = new Response_DTO();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String categoryId = request.getParameter("categoryId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String colorId = request.getParameter("colorId");
        String conditionId = request.getParameter("conditionId");
        String price = request.getParameter("price");
        String quentity = request.getParameter("quentity");
        String deliveryFee = request.getParameter("deliveryFee");

        Part image1 = request.getPart("image1");
        Part image2 = request.getPart("image2");
        Part image3 = request.getPart("image3");

        Session session = HibernateUtil.getSessionFactory().openSession();

        if (!Validations.isInteger(categoryId)) {
            response_DTO.setContent("Invalid Category");
        } else if (!Validations.isInteger(colorId)) {
            response_DTO.setContent("Invalid Color");
        } else if (!Validations.isInteger(conditionId)) {
            response_DTO.setContent("Invalid Condition");
        } else if (title.isEmpty()) {
            response_DTO.setContent("Please Fill Title");
        } else if (description.isEmpty()) {
            response_DTO.setContent("Please Fill Description");
        } else if (price.isEmpty()) {
            response_DTO.setContent("Please Fill Price");
        } else if (!Validations.isDouble(price)) {
            response_DTO.setContent("Invalid price");
        } else if (Double.parseDouble(price) <= 0) {
            response_DTO.setContent("Price must be greater than 0");
        } else if (quentity.isEmpty()) {
            response_DTO.setContent("Please fill quentity");
        } else if (!Validations.isInteger(quentity)) {
            response_DTO.setContent("Invalid Quentity");
        } else if (Integer.parseInt(quentity) <= 0) {
            response_DTO.setContent("Quentity must be greater than 0");
        } else if (deliveryFee.isEmpty()) {
            response_DTO.setContent("Please Fill Delivery Fee");
        } else if (!Validations.isDouble(deliveryFee)) {
            response_DTO.setContent("Invalid Delivery Fee");
        } else if (Double.parseDouble(deliveryFee) <= 0) {
            response_DTO.setContent("Delivery Fee must be greater than 0");
        } else if (image1.getSubmittedFileName() == null) {
            response_DTO.setContent("Please upload image 1");
        } else if (image2.getSubmittedFileName() == null) {
            response_DTO.setContent("Please upload image 2");
        } else if (image3.getSubmittedFileName() == null) {
            response_DTO.setContent("Please upload image 3");
        } else {
            Product_Category category = (Product_Category) session.get(Product_Category.class, Integer.parseInt(categoryId));

            if (category == null) {
                response_DTO.setContent("Please select valid category");
            } else {

                    if (category.getId() != category.getId()) {
                        response_DTO.setContent("Please select a valid model");
                    } else {
                        
                            Color color = (Color) session.get(Color.class, Integer.parseInt(colorId));

                            if (color == null) {
                                response_DTO.setContent("Please select valid color");
                            } else {
                                Product_Condition condition = (Product_Condition) session.get(Product_Condition.class, Integer.parseInt(conditionId));

                                if (condition == null) {
                                    response_DTO.setContent("Please select valid condition");
                                } else {
                                    Product product = new Product();
                                    product.setColor(color);
                                    product.setCategory(category);
                                    product.setCondition(condition);
                                    product.setDate_time(new Date());
                                    product.setDescription(description);
                                    product.setPrice(Double.parseDouble(price));
                                    product.setDelivery_fee(Double.parseDouble(deliveryFee));

                                    //get active status
                                    Product_Status product_Status = (Product_Status) session.get(Product_Status.class, 1);
                                    product.setProduct_Status(product_Status);

                                    product.setQty(Integer.parseInt(quentity));
                                    product.setTitle(title);

                                    User_DTO user_DTO = (User_DTO) request.getSession().getAttribute("user");
                                    Criteria criteria1 = session.createCriteria(User.class);
                                    criteria1.add(Restrictions.eq("email", user_DTO.getEmail()));
                                    User user = (User) criteria1.uniqueResult();
                                    product.setUser(user);

                                    int pid = (int) session.save(product);
                                    session.beginTransaction().commit();

                                    String applicationPath = request.getServletContext().getRealPath("");
                                    String newApplicationPath = applicationPath.replace("build"+File.separator+"web", "web");

                                    File folder = new File(newApplicationPath + "//product-images//" + pid);
                                    folder.mkdir();
                                    
                                    File b_folder = new File(applicationPath + "//product-images//" + pid);
                                    b_folder.mkdir();
                                    
                                    File b_file1 = new File(b_folder, "image1.png");
                                    InputStream b_inputStream1 = image1.getInputStream();
                                    Files.copy(b_inputStream1, b_file1.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                    File b_file2 = new File(b_folder, "image2.png");
                                    InputStream b_inputStream2 = image2.getInputStream();
                                    Files.copy(b_inputStream2, b_file2.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                    File b_file3 = new File(b_folder, "image3.png");
                                    InputStream b_inputStream3 = image3.getInputStream();
                                    Files.copy(b_inputStream3, b_file3.toPath(), StandardCopyOption.REPLACE_EXISTING);


                                    File file1 = new File(folder, "image1.png");
                                    InputStream inputStream1 = image1.getInputStream();
                                    Files.copy(inputStream1, file1.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                    File file2 = new File(folder, "image2.png");
                                    InputStream inputStream2 = image2.getInputStream();
                                    Files.copy(inputStream2, file2.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                    File file3 = new File(folder, "image3.png");
                                    InputStream inputStream3 = image3.getInputStream();
                                    Files.copy(inputStream3, file3.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                    response_DTO.setSuccess(true);
                                    response_DTO.setContent("New Product Added");

                                }
                            }
                        
                    }
                
            }
        }

        response.setHeader("content-Type", "application/json");
        response.getWriter().write(gson.toJson(response_DTO));
        session.close();

        
    }

}
