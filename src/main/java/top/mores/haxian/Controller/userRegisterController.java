package top.mores.haxian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class userRegisterController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //注册返回后端信息
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> GetUserRegisterData(@RequestParam("username") String registerUsername,
                                                   @RequestParam("phone") String registerPhoneNumber,
                                                   @RequestParam("userpassword") String registerPassword) {
        String sql = "select COUNT(*) from users where name = ?";
        String inName = jdbcTemplate.queryForObject(sql, new Object[]{registerUsername}, String.class);
        Map<String,Object> response=new HashMap<>();
        if (!Objects.equals(inName, "0")) {
            response.put("code",401);
            response.put("msg","已存在该用户名");
        }else {
            String sql1="insert into users(name,pwd,phone) values (?,?,?)";
            jdbcTemplate.update(sql1,registerUsername,registerPassword,registerPhoneNumber);
            response.put("code",200);
            response.put("msg","注册成功");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
