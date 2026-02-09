package in.kce.book.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.kce.book.bean.BookBean;
import in.kce.book.dao.AuthorDAO;
import in.kce.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Administrator administrator = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String result = addBook(request);

            if ("SUCCESS".equals(result)) {
                response.sendRedirect("SUCCESS.html");
            } else if ("INVALID".equals(result)) {
                response.sendRedirect("INVALID.html");
            } else {
                response.sendRedirect("FAILURE.html");
            }

        } else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean bookBean = viewBook(isbn);

            if (bookBean == null) {
                response.sendRedirect("INVALID.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", bookBean);

                RequestDispatcher rd =
                        request.getRequestDispatcher("ViewServlet");
                rd.forward(request, response);
            }
        }
    }

    private String addBook(HttpServletRequest request) {

        try {
            String isbn = request.getParameter("isbn");
            String bookName = request.getParameter("bookName");
            char bookType = request.getParameter("bookType").charAt(0);
            String authorName = request.getParameter("authorName");
            float cost = Float.parseFloat(request.getParameter("cost"));

            BookBean bookBean = new BookBean();
            bookBean.setIsbn(isbn);
            bookBean.setBookName(bookName);
            bookBean.setBookType(bookType);
            bookBean.setCost(cost);
            bookBean.setAuthor(new AuthorDAO().getAuthor(authorName));

            return administrator.addBook(bookBean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAILURE";
        }
    }

    // 👇 THIS IS THE METHOD YOU ASKED ABOUT
    public BookBean viewBook(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }
        return administrator.viewBook(isbn);
    }
}
