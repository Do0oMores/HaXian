package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserReservationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> queryUserReservation(Integer userID) {
        String sql = "select * from reservations where userId = ?";
        return jdbcTemplate.queryForList(sql, userID);
    }

    public Map<String, Object> queryProductInformation(Integer productID) {
        String SQL = "select name,price from products where product_id=?";
        return jdbcTemplate.queryForMap(SQL, productID);
    }
}
