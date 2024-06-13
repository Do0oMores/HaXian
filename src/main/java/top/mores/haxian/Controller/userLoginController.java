package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.mores.haxian.POJO.User;
import top.mores.haxian.service.LoginService;

import java.util.Map;

@Controller
public class userLoginController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final String USER_LOGIN_STATE="userLoginState";

    @RestController
    @RequestMapping("/login")
    public class UserController {

        @Autowired
        private LoginService userService;

        @GetMapping
        public ResponseEntity<Map<String, Object>> getUserPassword(@RequestParam("userName") String userName,
                                                                   @RequestParam("userPassword") String userPassword,
                                                                   HttpSession session) {
            Map<String, Object> response = userService.authenticateUser(userName, userPassword);
            if ((int) response.get("code") == 200) {
                User user = new User();
                user.setUserName(userName);
                session.setAttribute(USER_LOGIN_STATE, user);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
