package in.kce.book.dao;

import java.sql.*;
import in.kce.book.bean.AuthorBean;
import in.kce.book.util.DBUtil;


public class AuthorDAO {

    public AuthorBean getAuthor(int authorCode) {
        AuthorBean author = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM AUTHOR_TBL WHERE AUTHOR_CODE=?");
            ps.setInt(1, authorCode);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new AuthorBean();
                author.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setContactNo(rs.getLong("CONTACT_NO"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public AuthorBean getAuthor(String authorName) {
        AuthorBean author = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM AUTHOR_TBL WHERE AUTHOR_NAME=?");
            ps.setString(1, authorName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                author = new AuthorBean();
                author.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                author.setAuthorName(rs.getString("AUTHOR_NAME"));
                author.setContactNo(rs.getLong("CONTACT_NO"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }
}
