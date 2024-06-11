package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class queryProductsInformation {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/products")
    public ResponseEntity<Map<String,Object>> productsInformation(@RequestParam("productName") String productName){
        String sql=
                "select product_id,name,description,price,stock,origin,production_date,support,create_time,shelf_life from products where name =?";
        Map<String,Object> response=new HashMap<>();
        try{
            Map<String,Object> products=jdbcTemplate.queryForMap(sql,productName);
            response.put("code",200);
            response.put("msg","查询成功");
            List<Map<String, Object>> data = new ArrayList<>();
            data.add(products);
            response.put("Data", data);
        }catch (EmptyResultDataAccessException e){
            response.put("code",404);
            response.put("msg","没有名为"+productName+"的商品");
        }catch (Exception e){
            response.put("code",500);
            response.put("msg","服务器错误");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
