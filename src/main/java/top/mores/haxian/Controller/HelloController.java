package top.mores.haxian.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.mores.haxian.POJO.User;

@Controller
public class HelloController {
    @RequestMapping("/UserLogin")
    @ResponseBody
    public String hello(User user){

        System.out.println(user.getUserName()+":"+user.getUserPassword());
        return "你好,Spring boot";
    }
    @RequestMapping("/UserLogin2")
    @ResponseBody
    public String hello1(){
        return "UserLogin.jsp";
    }


    @PostMapping("/UserLogin3")
    public ModelAndView loginUser(
            @RequestParam("userName") String userName,
            @RequestParam("userPassword") String userPassword) {

        // 处理表单数据
        System.out.println("Username: " + userName);
        System.out.println("Password: " + userPassword);

        // 你可以添加逻辑来验证用户信息，或者重定向到另一个页面
        ModelAndView mav = new ModelAndView();
        mav.setViewName("HaXianMain.jsp"); // 例如，返回一个名为loginResult的视图
        mav.addObject("userName", userName);

        return mav;
    }
}
