package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mores.haxian.POJO.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class userLoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final String USER_LOGIN_STATE="userLoginState";

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getAllUsers() {
        String sql = "select * from users";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    /**
     * 登录检查
     * @param userName 前端登录的用户名
     * @param userPassword 前端登录的密码
     * @return 封装的Map响应
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserPassword(@RequestParam("userName") String userName,
                                                               @RequestParam("userPassword") String userPassword,
                                                               HttpSession session) {
        String sql = "select pwd from users where name = ?";
        String sql1 = "select is_admin from users where name = ?";
        Map<String, Object> response = new HashMap<>();
        try {
            String password = jdbcTemplate.queryForObject(sql, new Object[]{userName}, String.class);
            int isAdmin = jdbcTemplate.queryForObject(sql1, new Object[]{userName}, int.class);
            //判断密码
            if (userPassword.equals(password)) {
                response.put("code", 200);
                response.put("msg", "登录成功");
                //创建User对象
                User user=new User();
                user.setUserName(userName);
                session.setAttribute(USER_LOGIN_STATE,user);
                //判断用户是否为管理
                if (isAdmin == 1) {
                    response.put("role", "admin");
                } else {
                    response.put("role", "user");
                }
            } else {
                //ERROR处理
                response.put("code", 401);
                response.put("msg", "密码错误");
            }
        } catch (EmptyResultDataAccessException e) {
            response.put("code", 404);
            response.put("msg", "用户不存在");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
