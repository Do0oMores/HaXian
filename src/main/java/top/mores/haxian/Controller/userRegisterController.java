package top.mores.haxian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class userRegisterController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public void GetUserRegisterData(@RequestParam("username") String registerUsername,
                                    @RequestParam("phone") String registerPhoneNumber,
                                    @RequestParam("userpassword") String registerPassword){
        String sql="select COUNT(*) from users where name = ?";
        String inName = jdbcTemplate.queryForObject(sql, new Object[]{registerUsername}, String.class);
        if (!Objects.equals(inName, "0")){
            System.out.println("用户名重复");
        }
        System.out.println(registerUsername+":"+registerPassword+":"+registerPhoneNumber);

    }
}
