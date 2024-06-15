package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class queryUsersOrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllOrders() {
        String sql = "select * from orders";
        return jdbcTemplate.queryForList(sql);
    }

    public String getUserNameById(String userId) {
        String sql = "select name from users where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getProductNameById(String productId) {
        String sql = "select name from products where product_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
