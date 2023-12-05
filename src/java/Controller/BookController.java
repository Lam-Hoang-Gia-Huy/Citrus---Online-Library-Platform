/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Book.BookDAO;
import Book.BookDTO;
import User.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author legion
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        HttpSession session = request.getSession(false);
        UserDTO currentUser = null;

        if (session != null) {
            currentUser = (UserDTO) session.getAttribute("usersession");
        }

        String action = request.getParameter("action");
        BookDAO bookDAO = new BookDAO();

        if (action == null) {
            List<BookDTO> list = bookDAO.BookList();
            List<BookDTO> newestlist = bookDAO.getNewestUploadList();
            request.setAttribute("newestupload", newestlist);
            request.setAttribute("PopularList", list);
            if (currentUser != null) {
                List<BookDTO> BookmarkList = bookDAO.getBookmarkList(currentUser.getUserID());
                request.setAttribute("BookmarkList", BookmarkList);
            }
            RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
            rd.forward(request, response);
        }

        if (action.equals("detail")) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException ex) {
            }
            BookDTO book = null;
            if (id != null) {
                book = bookDAO.load(id);
            }
            if (currentUser != null) {
                boolean bookmarkCheck = bookDAO.Bookmark(id, currentUser.getUserID());
                if (bookmarkCheck == false) {
                    request.setAttribute("bookmarkDisabled", "Bookmarked");
                } else {
                    bookDAO.Unbookmark(id, currentUser.getUserID());
                }
            }
            request.setAttribute("object", book);
            RequestDispatcher rd = request.getRequestDispatcher("BookDetail.jsp");
            rd.forward(request, response);
        }

        if (action.equals("editbook")) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException ex) {
            }
            BookDTO book = null;
            if (id != null) {
                book = bookDAO.load(id);
            }
            request.setAttribute("object", book);
            request.setAttribute("action", "editconfirm");
            RequestDispatcher rd = request.getRequestDispatcher("Upload.jsp");
            rd.forward(request, response);
        }

        if (action.equals("Bookmark")) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("bookId"));
            } catch (NumberFormatException ex) {
            }
            bookDAO.Bookmark(id, currentUser.getUserID());
            response.sendRedirect("BookController?action=detail&id=" + id);
        }

        if (action.equals("Unbookmark")) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("bookId"));
            } catch (NumberFormatException ex) {
            }
            bookDAO.Unbookmark(id, currentUser.getUserID());
            response.sendRedirect("BookController?action=detail&id=" + id);
        }

        if (action.equals("editconfirm")) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException ex) {
            }
            bookDAO.updateBook(request.getParameter("name"), request.getParameter("genre"), request.getParameter("author"),
                    request.getParameter("cover"), request.getParameter("content"), request.getParameter("summary"), id);
            response.sendRedirect("BookController?action=detail&id=" + id);
        }

        if (action.equals("upload")) {
            List<BookDTO> list = bookDAO.BookList();
            int id = list.size() + 1;
            request.setAttribute("uploadid", id);
            request.setAttribute("action", "uploadconfirm");
            RequestDispatcher rd = request.getRequestDispatcher("Upload.jsp");
            rd.forward(request, response);
        }

        if (action.equals("uploadconfirm")) {
            Long id = null;
            try {
                id = Long.parseLong(request.getParameter("uploadid"));
            } catch (NumberFormatException ex) {
            }
            bookDAO.uploadBook(request.getParameter("name"), request.getParameter("genre"), request.getParameter("author"),
                    request.getParameter("cover"), request.getParameter("content"), request.getParameter("summary"), id, currentUser.getUserID());
            response.sendRedirect("BookController?action=detail&id=" + id);
        }

        if (action.equals("search")) {
            String bookname = request.getParameter("searchbar");
            String genre = request.getParameter("genre");
            List<BookDTO> list = bookDAO.getSearchList(genre, bookname);
            request.setAttribute("searchlist", list);
            request.setAttribute("searchvalue", "You are looking for all the books with: " + bookname);
            request.setAttribute("searchgenre", "Genre: " + genre);
            RequestDispatcher rd = request.getRequestDispatcher("SearchPage.jsp");
            rd.forward(request, response);
        }

        if (action.equals("uploadlist")) {
            List<BookDTO> list = bookDAO.getUploadList(currentUser.getUserID());
            request.setAttribute("text", "Books that you have uploaded " + "(" + list.size() + ")");
            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("YourList.jsp");
            rd.forward(request, response);
        }

        if (action.equals("bookmarklist")) {
            if (currentUser != null) {
                List<BookDTO> BookmarkList = bookDAO.getBookmarkList(currentUser.getUserID());
                request.setAttribute("text", "Books that you have bookmarked " + "(" + BookmarkList.size() + ")");
                request.setAttribute("list", BookmarkList);
            }
            RequestDispatcher rd = request.getRequestDispatcher("YourList.jsp");
            rd.forward(request, response);
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
