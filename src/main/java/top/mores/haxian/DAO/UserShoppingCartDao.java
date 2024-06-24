package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserShoppingCartDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryUserShoppingCart(Integer userID) {
        String sql = "select * from shopping where user_id = ? ";
        return jdbcTemplate.queryForList(sql, userID);
    }

    public List<Map<String, Object>> queryProductInformation(Integer productID) {
        String sql = "select name,price,image from products where product_id = ? ";
        return jdbcTemplate.queryForList(sql, productID);
    }

    public void insertOrder(Integer user_id, Integer product_id,
                            String status, double price, Integer amount) {
        String sql = "insert into orders(user_id,product_id,status,total_price,amount) " +
                "values (?,?,?,?,?)";
        jdbcTemplate.update(sql, user_id, product_id, status, price, amount);
    }

    public int changeOrderStatus(String status, Integer userId) {
        String sql = "update orders set status = ? where user_id=?";
        return jdbcTemplate.update(sql, status, userId);
    }

    public int cleanUserShoppingCart(Integer userID) {
        String sql = "delete from shopping where user_id=?";
        return jdbcTemplate.update(sql, userID);
    }

    public List<Map<String, Object>> queryOrdersForUser(Integer userID) {
        String sql = "select product_id,amount,total_price from orders where user_id=? and status=?";
        String status = "等待结账";
        return jdbcTemplate.queryForList(sql, userID, status);
    }

    public int cleanOrder(Integer userID) {
        String sql = "delete from orders where user_id=? and status=?";
        String status = "等待结账";
        return jdbcTemplate.update(sql, userID, status);
    }
}
