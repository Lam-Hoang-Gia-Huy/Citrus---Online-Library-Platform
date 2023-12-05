/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author legion
 */
public class BookDAO {

    public BookDTO load(Long id) {
        String sql = "select book_id, book_name, genre, author, cover, book_content, score, summary, uploader_id from Book where book_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BookDTO(
                        rs.getLong("book_id"),
                        rs.getString("book_name"),
                        rs.getString("genre"),
                        rs.getString("author"),
                        rs.getString("cover"),
                        rs.getString("book_content"),
                        rs.getFloat("score"),
                        rs.getString("summary"),
                        rs.getInt(9)
                );
            }
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    public List<BookDTO> BookList() {
        ArrayList<BookDTO> list;
        list = new ArrayList<BookDTO>();
        String sql = "select book_id, book_name, genre, author, cover, book_content, score, summary, uploader_id from Book order by score desc";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getInt(9)
                ));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    public List<BookDTO> getBookmarkList(int userID) {
        ArrayList<BookDTO> list;
        list = new ArrayList<BookDTO>();
        String sql = "select Book.book_id, book_name, genre, author, cover, book_content, score, summary, uploader_id from Book inner join Bookmark on Bookmark.book_id=Book.book_id where customer_id=?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getInt("uploader_id")
                )
                );
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    public boolean Bookmark(Long id, int user_id) {
        String sql = "INSERT INTO Bookmark(book_id,customer_id) VALUES (?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, user_id);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Insert error!" + ex.getMessage());
        }
        return false;
    }

    public boolean Unbookmark(Long id, int user_id) {
        String sql = "DELETE FROM Bookmark WHERE book_id=? and customer_id=?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, user_id);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Insert error!" + ex.getMessage());
        }
        return false;
    }

    public boolean updateBook(String name, String genre, String author, String cover, String content, String summary, Long id) {
        String sql = "update Book set book_name= ?, genre=?, author=?, cover=?, book_content=?, summary=? where book_id = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, genre);
            ps.setString(3, author);
            ps.setString(4, cover);
            ps.setString(5, content);
            ps.setString(6, summary);
            ps.setLong(7, id);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Update error!" + ex.getMessage());
        }
        return false;
    }

    public boolean uploadBook(String name, String genre, String author, String cover, String content, String summary, Long id, int uploader) {
        String sql = "insert into Book (book_name, genre, author, cover, book_content, summary, book_id, uploader_id) VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, genre);
            ps.setString(3, author);
            ps.setString(4, cover);
            ps.setString(5, content);
            ps.setString(6, summary);
            ps.setLong(7, id);
            ps.setInt(8, uploader);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Update error!" + ex.getMessage());
        }
        return false;
    }

    public List<BookDTO> getSearchList(String genre, String searchvalue) {
        ArrayList<BookDTO> list;
        list = new ArrayList<BookDTO>();
        String sql = "select book_id, book_name, genre, author, cover, book_content, score, summary, uploader_id from Book where book_name like CONCAT('%',?,'%') and genre like CONCAT('%',?,'%')";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, searchvalue);
            ps.setString(2, genre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getInt(9)
                )
                );
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }
    
    public List<BookDTO> getUploadList(int userid) {
        ArrayList<BookDTO> list;
        list = new ArrayList<BookDTO>();
        String sql = "select book_id, book_name, genre, author, cover, book_content, score, summary, uploader_id from Book where uploader_id=?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getInt(9)
                )
                );
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }
    
    public List<BookDTO> getNewestUploadList() {
        ArrayList<BookDTO> list;
        list = new ArrayList<BookDTO>();
        String sql = "select book_id, book_name, genre, author, cover, book_content, score, summary, uploader_id from Book order by book_id desc";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getInt(9)
                )
                );
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }    
}
