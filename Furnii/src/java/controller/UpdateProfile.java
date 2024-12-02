/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.HibernateUtil;
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sande
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/UpdateProfile"})
@MultipartConfig
public class UpdateProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();

        JsonObject responseJsonObject = new JsonObject();
        responseJsonObject.addProperty("success", false);

        Session session = HibernateUtil.getSessionFactory().openSession();
        HttpSession httpSession = request.getSession();
        Transaction transaction = session.beginTransaction();

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");

        if (fname.isEmpty()) {
            responseJsonObject.addProperty("message", "Please Fill First Name");
        } else if (lname.isEmpty()) {
            responseJsonObject.addProperty("message", "Please Fill Last Name");
        } else if (password.isEmpty()) {
            responseJsonObject.addProperty("message", "Please Add Old Password");
        } else if (newPassword.isEmpty()) {
            responseJsonObject.addProperty("message", "Please Fill New Password");
        } else if (!Validations.isPasswordValid(newPassword)) {
            responseJsonObject.addProperty("message", "Give Strong Password");
        } else if (newPassword.equals(password)) {
            responseJsonObject.addProperty("message", "New Password Cannot Be Old Password");
        } else if (email.isEmpty()) {
            responseJsonObject.addProperty("message", "Suspicious Activity Found!!!");
        } else {

            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.eq("email", email));
            criteria1.add(Restrictions.eq("password", password));
            User user = (User) criteria1.list().get(0);

            if (user == null) {
                responseJsonObject.addProperty("message", "Invalid Details");
            } else {

                user.setFname(fname);
                user.setLname(lname);
                user.setPassword(newPassword);
                
                session.update(user);
                transaction.commit();

                responseJsonObject.addProperty("success", true);
                responseJsonObject.addProperty("message", "Profile Update Successfully.");
            }

        }

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseJsonObject));
        session.close();

    }

}
