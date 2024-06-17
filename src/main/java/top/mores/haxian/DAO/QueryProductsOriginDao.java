package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QueryProductsOriginDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> productOrigin(Integer productID){
        if (productID==null){
            return null;
        }else {
            String sql="select name,description,price,origin,production_date,support,create_time,shelf_life,type " +
                    "from products where product_id=?";
            return jdbcTemplate.queryForList(sql,productID);
        }
    }

    public Integer queryProductIDByProductName(String productName){
        String sql="select product_id from products where name=?";
        try{
            return jdbcTemplate.queryForObject(sql,new  Object[]{productName},Integer.class);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
