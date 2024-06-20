package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.Utils.LoginCheck;
import top.mores.haxian.service.UserService.EditInformationService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserEditInformationController {
    @Autowired
    EditInformationService editInformationService;

    LoginCheck loginCheck=new LoginCheck();

    @GetMapping("fetch-information")
    public ResponseEntity<Map<String,Object>> fetchUserInformation(@RequestParam("userID")Integer userID,
                                                                   HttpSession session){
        Map<String,Object> response=new HashMap<>();
        if (loginCheck.onLoginCheck(session)){
            Map<String,Object> data=editInformationService.getUserInformation(userID);
            response.put("Data",data);
            response.put("code",200);
        }else {
            response.put("code",404);
            response.put("msg","您还未登录");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/edit-user")
    public ResponseEntity<Map<String,Object>> saveUser(@RequestParam("userName")String userName,
                                                       @RequestParam("userPhone")String phone,
                                                       @RequestParam("userPwd")String pwd,
                                                       @RequestParam("userID")Integer userID,
                                                       HttpSession session){
        Map<String,Object> response=new HashMap<>();
        if (loginCheck.onLoginCheck(session)){
            int rows=editInformationService.saveUserInformation(userID,userName,phone,pwd);
            if (rows>0){
                response.put("code",200);
                response.put("msg","编辑已保存");
            }else {
                response.put("code",500);
                response.put("msg","保存失败");
            }
        }else {
            response.put("code",404);
            response.put("msg","您还未登录");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
