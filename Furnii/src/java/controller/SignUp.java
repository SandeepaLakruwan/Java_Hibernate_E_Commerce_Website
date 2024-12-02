/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Response_DTO;
import dto.User_DTO;
import entity.Product_Status;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.Mail;
import model.Validations;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sande
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Response_DTO response_DTO = new Response_DTO();

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        User_DTO user_DTO = gson.fromJson(request.getReader(), User_DTO.class);

        if (user_DTO.getFname().isEmpty()) {
            response_DTO.setContent("Please Enter Your First Name");
        } else if (user_DTO.getLname().isEmpty()) {
            response_DTO.setContent("Please Enter Your Last Name");
        } else if (user_DTO.getEmail().isEmpty()) {
            response_DTO.setContent("Please Enter Your Email");
        } else if (!Validations.isEmailValid(user_DTO.getEmail())) {
            response_DTO.setContent("Please Enter Valid Email");
        } else if (user_DTO.getPassword().isEmpty()) {
            response_DTO.setContent("Please Enter Your Password");
        } else if (!Validations.isPasswordValid(user_DTO.getPassword())) {
            response_DTO.setContent("Password must include at least one uppercase "
                    + "letter, number, special character and at least 8 characters long.");
        } else {

            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.eq("email", user_DTO.getEmail()));

            if (!criteria1.list().isEmpty()) {
                response_DTO.setContent("User already registered with this email");
            } else {

                //get status
                Criteria criteria2 = session.createCriteria(Product_Status.class);
                criteria2.add(Restrictions.eq("name", "Inactive"));
                Product_Status status = (Product_Status) criteria2.uniqueResult();
                
                //generate verification code
                int code = (int) (Math.random() * 1000000);

                final User user = new User();
                user.setEmail(user_DTO.getEmail());
                user.setFname(user_DTO.getFname());
                user.setLname(user_DTO.getLname());
                user.setPassword(user_DTO.getPassword());
                user.setVerification_code(String.valueOf(code));
                user.setJoined_date(new Date());
                user.setStatus(status);

                request.getSession().setAttribute("email", user_DTO.getEmail());
                //send verification email
                Thread sendMailThread = new Thread() {
                    @Override
                    public void run() {
                        Mail.sendMail(user.getEmail(), "Smart Trade Verification",
                        "<h1 style=\"color:#6e3c08\">Your Verification Code: " + user.getVerification_code()+ "</h1>");
                    }
                    
                };
                sendMailThread.start();
                
                session.save(user);
                session.beginTransaction().commit();
                response_DTO.setSuccess(true);
                response_DTO.setContent("Registration Complete. Please verify your account to Sign In");
                
            }
            session.close();

        }
        response.setHeader("content-Type","application/json");
        response.getWriter().write(gson.toJson(response_DTO));
    }

        
    }