package top.mores.haxian.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.Utils.LoginCheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class commoditiesMain {
    @Autowired
    JdbcTemplate jdbcTemplate;

    LoginCheck loginCheck = new LoginCheck();

    //加载商品信息
    @GetMapping("/commodity")
    public ResponseEntity<Map<String, Object>> showCommodity(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            String sql = "select * from products";
            try {
                List<Map<String, Object>> commodities = jdbcTemplate.queryForList(sql);
                response.put("code",200);
                response.put("Data",commodities);
            } catch (EmptyResultDataAccessException e) {
                response.put("code", 404);
                response.put("msg", "没有商品信息");
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //加入购物车
    @GetMapping("/addToCard")
    public ResponseEntity<Map<String,Object>> addToCard(HttpSession session,
                                                        @RequestParam("productId") String productId){
        Map<String,Object> response=new HashMap<>();
        if (loginCheck.onLoginCheck(session)){

        }else {
            response.put("code", 404);
            response.put("msg", "您还未登录");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
