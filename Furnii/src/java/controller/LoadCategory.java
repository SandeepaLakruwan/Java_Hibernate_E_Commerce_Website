/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Product;
import entity.Product_Category;
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

/**
 *
 * @author sande
 */
@WebServlet(name = "LoadCategory", urlPatterns = {"/LoadCategory"})
public class LoadCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Criteria criteria = session.createCriteria(Product_Category.class);
        criteria.addOrder(Order.desc("name"));
        List<Product> categoryList = criteria.list();
                
        jsonObject.add("category", gson.toJsonTree(categoryList));
        
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jsonObject));
        
    }

}
