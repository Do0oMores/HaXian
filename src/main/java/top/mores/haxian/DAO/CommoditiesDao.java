package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommoditiesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllProducts() {
        String sql = "select * from products";
        return jdbcTemplate.queryForList(sql);
    }

    public void addCommodityToCart(Integer userId, Integer productId,int amount) {
        String sql = "insert into shopping(user_id, product_id, amount) values (?, ?, ?) " +
                "on duplicate key update amount = amount + ?";
        jdbcTemplate.update(sql, userId, productId, amount, amount);
    }

    public int updateCommodityAmount(Integer userId, Integer productId, int amount) {
        String sql = "update shopping set amount = amount + ? where user_id = ? and product_id = ?";
        return jdbcTemplate.update(sql, amount, userId, productId);
    }

    public int getCommodityAmount(Integer userId, Integer productId) {
        String sql = "select amount from shopping where user_id = ? and product_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, productId}, Integer.class);
    }

    public void removeCommodityFromCart(Integer userId, Integer productId) {
        String sql = "delete from shopping where user_id = ? and product_id = ?";
        jdbcTemplate.update(sql, userId, productId);
    }

    public int updateProductStock(int productId, int quantity) {
        String sql = "update products set stock = stock - ? where product_id = ? and stock >= ?";
        return jdbcTemplate.update(sql, quantity, productId, quantity);
    }

    public void restoreProductStock(int productId, int quantity) {
        String sql = "update products set stock = stock + ? where product_id = ?";
        jdbcTemplate.update(sql, quantity, productId);
    }

    public int addReservation(Integer userID,Integer productID,Integer amount){
        String sql="insert into reservations(userId,productId,amount) values (?,?,?)";
        return jdbcTemplate.update(sql,userID,productID,amount);
    }
}
