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
public class queryReservation {

    @Autowired
    JdbcTemplate jdbcTemplate;

    LoginCheck loginCheck = new LoginCheck();

    @GetMapping("/reservation")
    public ResponseEntity<Map<String, Object>> reservationData(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            String sql = "select * from reservations";
            String sql1 = "select name from users where id = ?";
            String sql2 = "select name from products where product_id = ?";

            try {
                List<Map<String, Object>> reservations = jdbcTemplate.queryForList(sql);
                if (reservations.isEmpty()) {
                    response.put("code", 404);
                    response.put("msg", "没有查询到预约信息");
                } else {
                    for (Map<String, Object> reservation : reservations) {
                        String userId = reservation.get("userId").toString();
                        String userName = jdbcTemplate.queryForObject(sql1, new Object[]{userId}, String.class);
                        reservation.put("userId", userName);
                        String productId = reservation.get("productId").toString();
                        String productName = jdbcTemplate.queryForObject(sql2, new Object[]{productId}, String.class);
                        reservation.put("productId", productName);
                    }
                    response.put("code", 200);
                    response.put("msg", "查询成功");
                    response.put("Data", reservations);
                }
            } catch (EmptyResultDataAccessException e) {
                response.put("code", 404);
                response.put("msg", "没有查询到预约信息");
            } catch (Exception e) {
                //e.printStackTrace();
                response.put("code", 500);
                response.put("msg", "服务器内部错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
