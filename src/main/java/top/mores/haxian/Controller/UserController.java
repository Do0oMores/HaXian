package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        try {
            jdbcTemplate.execute("insert into users (id, name, pwd, phonenumber) values (2, '李四', '3344', 13300000000)");
        } catch (DataAccessException exception) {
            throw new RuntimeException("数据库报错");
        }
        return list;
    }

    //检查密码
    @RequestMapping(value = "/getUserPassword", method = RequestMethod.POST)
    @ResponseBody
    public void getUserPassword(@RequestParam("userName") String username,
                                @RequestParam("userPassword") String userPassword,
                                HttpServletResponse response)throws IOException {
        String sql = "select pwd from users where name = ?";
        try {
            String password = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
            if (userPassword.equals(password)) {
                response.sendRedirect("http://localhost:8080/HaXianMain.jsp");
            } else {
                response.sendRedirect("http://localhost:8080/UserLoginFailed.jsp");
            }
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect("http://localhost:8080/UserLoginFailed.jsp");
        }
    }
}
