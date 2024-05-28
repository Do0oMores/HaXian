package top.mores.haxian.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.mores.haxian.POJO.User;

@Controller
public class userLoginController {
    @RequestMapping("/HaXianMain")
    public void GetUserName(User user){
        System.out.println(user.getUserName()+":"+user.getUserPassword());
    }
}
