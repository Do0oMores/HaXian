package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public int saveProduct(Integer productID,
                           String productName,
                           String description,
                           double price,
                           Integer stock,
                           String origin,
                           String productionDate,
                           String support,
                           LocalDateTime createTime,
                           Integer shelfLife,
                           String type){
        String sql="update products set name=?,description=?,price=?,stock=?,origin=?,production_date=?,support=?,create_time=?,shelf_life=?,type=? where product_id=?";
        return jdbcTemplate.update(sql,productName,description,price,stock,origin,productionDate,support,createTime,shelfLife,type,productID);
    }
}
