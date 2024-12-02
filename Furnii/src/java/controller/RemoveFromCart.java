/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dto.Response_DTO;
import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sande
 */
@WebServlet(name = "RemoveFromCart", urlPatterns = {"/RemoveFromCart"})
public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();

        Response_DTO response_DTO = new Response_DTO();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        response_DTO.setSuccess(false);

        try {
            String id = request.getParameter("id");
            int intId = Integer.parseInt(id);
            System.out.println(id);
            if (id.isEmpty()) {
                response_DTO.setContent("Id Not Recognised.");
            } else {

                Criteria criteria = session.createCriteria(Cart.class);
                criteria.add(Restrictions.eq("id", id));

                if (criteria.list().isEmpty()) {
                    response_DTO.setContent("No Cart Item To Remove");
                } else {
                    Cart cart = new Cart();
                    cart.setId(intId);

                    session.delete(cart);
                    transaction.commit();

                    response_DTO.setSuccess(true);
                    response_DTO.setContent("Product removed from the cart.");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(response_DTO));
        session.close();

    }

}
