package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    //映射返回路径
    @RequestMapping("/HaXianMain")
    public String toMainPage(){
        return "HaXianMain";
    }

    @RequestMapping("/UserLogin")
    public String userLogin(){
        return "UserLogin";
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getAllUsers(){
        String sql="select * from users";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
//        try {
//            jdbcTemplate.execute("insert into users (id, name, pwd, phonenumber) values (2, '李四', '3344', 13300000000)");
//        } catch (DataAccessException exception) {
//            throw new RuntimeException("数据库报错");
//        }
        return list;
    }

    //检查密码
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public void getUserPassword(@RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword){
        String sql = "select pwd from users where name = ?";
        try {
            String password = jdbcTemplate.queryForObject(sql, new Object[]{userName}, String.class);
            if (userPassword.equals(password)) {
                System.out.println(userName);
            } else {
                System.out.println(userPassword);
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println(22);
        }
    }
}
