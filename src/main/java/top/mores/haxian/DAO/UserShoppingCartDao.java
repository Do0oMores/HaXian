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
}
