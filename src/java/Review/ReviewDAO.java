/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Review;

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
public class ReviewDAO {

    public List<ReviewDTO> ReviewList(Long bookID) {
        ArrayList<ReviewDTO> list;
        list = new ArrayList<ReviewDTO>();
        String sql = "  select customer_name, review_score,review_content from Review inner join Customer on Review.owner_id=Customer.customer_id  where book_id=?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, bookID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ReviewDTO(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3)
                ));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    public boolean insert(int id, String content, int score, int owner_id, Long book_id) {
        String sql = "INSERT INTO Review( review_id, review_content, review_score,owner_id,book_id) VALUES (?,?,?,?,?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, content);
            ps.setInt(3, score);
            ps.setInt(4, owner_id);
            ps.setLong(5, book_id);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Insert error!" + ex.getMessage());
        }
        return false;
    }
    public int getReviewNumber() {
        String sql = "select top 1 review_id from Review ORDER BY review_id DESC";
        int number=0;
        try {            
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                number=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return number;
    }
    
    public float ScoreCal(long id) {
        String ScoreSql = "select review_score from Review where book_id=?";
        int review_number = 0;
        float score = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps_score = conn.prepareStatement(ScoreSql);
            ps_score.setLong(1, id);
            ResultSet rs_score = ps_score.executeQuery();
            
            while (rs_score.next()) {
                review_number++;
                score += rs_score.getFloat("review_score");
            }
            
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
            
        }
        DecimalFormat df = new DecimalFormat("#.##");
        float AvgScore = score / review_number;
        String s = df.format(AvgScore);
        AvgScore = Float.parseFloat(s);
        return AvgScore;
    }    

    public boolean updateScore(Long id, float score) {
        String sql = "update Book set score= ? where book_id = ?";
        try {
            Connection con = DBUtils.getConnection();
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, score);
            ps.setLong(2, id);            
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Update error!" + ex.getMessage());
        }        
        return false;
    }
}
