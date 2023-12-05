/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Review.ReviewDAO;
import Review.ReviewDTO;
import User.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author legion
 */
@WebServlet(name = "ReviewController", urlPatterns = {"/ReviewController"})
public class ReviewController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UserDTO currentUser = null;

        if (session != null) {
            currentUser = (UserDTO) session.getAttribute("usersession");
        }

        String action = request.getParameter("action");
        Long id = null;
        try {
            id = Long.parseLong(request.getParameter("bookId"));
        } catch (NumberFormatException ex) {
        }

        if (action == null) {
            ReviewDAO reviewDAO = new ReviewDAO();
            List<ReviewDTO> list = reviewDAO.ReviewList(id);
            request.setAttribute("list", list);
            request.setAttribute("bookId", id);
            RequestDispatcher rd = request.getRequestDispatcher("ReviewSection.jsp");
            rd.forward(request, response);
        }
        
        if (action.equals("Review")) {
            Long Book_id=null;
            int score = 0;
            String content=request.getParameter("content");
            try {
                Book_id = Long.parseLong(request.getParameter("bookId"));
            } catch (NumberFormatException ex) {
            }
            ReviewDAO reviewDAO = new ReviewDAO();
            int listSize=reviewDAO.getReviewNumber()+1;
            
            try {
                score = Integer.parseInt(request.getParameter("score"));
            } catch (NumberFormatException ex) {
            }
            reviewDAO.insert(listSize,content, score, currentUser.getUserID(), Book_id);
            reviewDAO.updateScore(Book_id, reviewDAO.ScoreCal(id));           
            response.sendRedirect("BookController?action=detail&id="+Book_id);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
