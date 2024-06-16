package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class QueryProductsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findProductByName(String productName) {
        String sql =
                "select product_id,name,description,price,stock,origin,production_date,support,create_time,shelf_life,type " +
                        "from products where name = ?";
        try {
            return jdbcTemplate.queryForMap(sql, productName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
