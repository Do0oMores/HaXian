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
public class queryUserInformation {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> userInformation(@RequestParam("username") String username) {
        String sql = "select id,name,phone,is_admin,register_date from users where name = ? ";
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> user = jdbcTemplate.queryForMap(sql, username);
            response.put("code", 200);
            response.put("msg", "查询成功");
            user.put("is_admin", (Integer) user.get("is_admin") == 1 ? "是" : "否");
            List<Map<String, Object>> data = new ArrayList<>();
            data.add(user);
            response.put("Data", data);
        } catch (EmptyResultDataAccessException e) {
            response.put("code", 404);
            response.put("msg", "用户"+username+"不存在");
        } catch (Exception e) {
            response.put("code", 500);
            response.put("msg", "服务器内部错误");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
