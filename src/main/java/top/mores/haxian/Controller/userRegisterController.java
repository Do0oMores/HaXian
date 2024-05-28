package top.mores.haxian.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class userRegisterController {
    @RequestMapping("/register")
    public void GetUserRegisterData(String registerUsername,
                                    String registerPhoneNumber,
                                    String registerPassword){
        System.out.println(registerUsername+":"+registerPassword+":"+registerPhoneNumber);

    }
}
