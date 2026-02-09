package in.kce.book.service;

import in.kce.book.bean.BookBean;
import in.kce.book.dao.BookDAO;

public class Administrator {

    BookDAO bookDAO = new BookDAO();

    public String addBook(BookBean bookBean) {

        if (bookBean == null ||
            bookBean.getBookName() == null ||
            bookBean.getIsbn() == null ||
            bookBean.getAuthor() == null ||
            bookBean.getCost() <= 0 ||
            (bookBean.getBookType() != 'G' && bookBean.getBookType() != 'T')) {

            return "INVALID";
        }

        return bookDAO.createBook(bookBean) > 0 ? "SUCCESS" : "FAILURE";
    }

    public BookBean viewBook(String isbn) {
        return bookDAO.fetchBook(isbn);
    }
}
