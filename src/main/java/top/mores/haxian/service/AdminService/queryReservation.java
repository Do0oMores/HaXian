package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class queryReservation {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/reservation")
    public ResponseEntity<Map<String, Object>> reservationData() {
        String sql = "select * from reservations";
        String sql1 = "select name from users where id = ?";
        String sql2 = "select name from products where product_id = ?";
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> reservations = jdbcTemplate.queryForMap(sql);
            response.put("code", 200);
            response.put("msg", "查询成功");
            String userName = jdbcTemplate.queryForObject(sql1, new Object[]{reservations.get("userId")}, String.class);
            String productName=jdbcTemplate.queryForObject(sql2,new Object[]{reservations.get("productId")},String.class);
            reservations.put("userId",userName);
            reservations.put("productId",productName);
            List<Map<String, Object>> data = new ArrayList<>();
            data.add(reservations);
            response.put("Data", data);
        } catch (EmptyResultDataAccessException e) {
            response.put("code", 404);
            response.put("msg", "没有查询到预约信息");
        } catch (Exception e) {
            //e.printStackTrace();
            response.put("code", 500);
            response.put("msg", "服务器内部错误");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
