package top.mores.haxian.service.AdminService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.mores.haxian.Utils.LoginCheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class queryUserOrders {

    @Autowired
    JdbcTemplate jdbcTemplate;

    LoginCheck loginCheck = new LoginCheck();

    @GetMapping("/orders")
    public ResponseEntity<Map<String, Object>> userOrders(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            String sql = "select * from orders";
            String sql1 = "select name from users where id = ?";
            String sql2 = "select name from products where product_id = ?";

            try {
                // 查询orders表
                List<Map<String, Object>> orders = jdbcTemplate.queryForList(sql);
                if (orders.isEmpty()) {
                    response.put("code", 404);
                    response.put("msg", "没有查到消费记录");
                } else {
                    for (Map<String, Object> order : orders) {
                        // 从users表获取userName
                        String userId = order.get("user_id").toString();
                        String userName = jdbcTemplate.queryForObject(sql1, new Object[]{userId}, String.class);
                        order.put("user_name", userName);

                        // 从product表获取productName
                        String productId = order.get("product_id").toString();
                        String productName = jdbcTemplate.queryForObject(sql2, new Object[]{productId}, String.class);
                        order.put("product_name", productName);
                    }
                    response.put("code", 200);
                    response.put("msg", "查询成功");
                    response.put("Data", orders);
                }
            } catch (EmptyResultDataAccessException e) {
                response.put("code", 404);
                response.put("msg", "没有查到消费记录");
            } catch (Exception e) {
                //e.printStackTrace();
                response.put("code", 500);
                response.put("msg", "服务器错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
