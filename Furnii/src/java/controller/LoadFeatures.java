/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.Response_DTO;
import dto.User_DTO;
import entity.Color;
import entity.OrderItem;
import entity.Product;
import entity.Product_Category;
import entity.Product_Condition;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sande
 */
@WebServlet(name = "LoadFeatures", urlPatterns = {"/LoadFeatures"})
public class LoadFeatures extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Response_DTO response_DTO = new Response_DTO();
        
        Gson gson = new Gson();
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria1 = session.createCriteria(Product_Category.class);
        criteria1.addOrder(Order.asc("name"));
        List<Product_Category> categoryList = criteria1.list();
        
        Criteria criteria3 = session.createCriteria(Color.class);
        criteria3.addOrder(Order.asc("name"));
        List<Color> colorList = criteria3.list();
        
        Criteria criteria5 = session.createCriteria(Product_Condition.class);
        criteria5.addOrder(Order.asc("name"));
        List<Product_Condition> conditionList = criteria5.list();
        
        User_DTO user_DTO = (User_DTO) request.getSession().getAttribute("user");
        
        Criteria criteria6 = session.createCriteria(User.class);
        criteria6.add(Restrictions.eq("email", user_DTO.getEmail()));
        User user = (User) criteria6.uniqueResult();
        
        Criteria criteria7 = session.createCriteria(Product.class);
        criteria7.add(Restrictions.eq("user", user));
        List<Product> productList = criteria7.list();
        
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("categoryList", gson.toJsonTree(categoryList));
        jsonObject.add("colorList", gson.toJsonTree(colorList));
        jsonObject.add("conditionList", gson.toJsonTree(conditionList));
        jsonObject.add("user", gson.toJsonTree(user));
        jsonObject.add("productList", gson.toJsonTree(productList));
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jsonObject));
        
    }

}
