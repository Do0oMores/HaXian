package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.Utils.LoginCheck;
import top.mores.haxian.service.AdminService.queryUserInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserInformationController {
    @Autowired
    private queryUserInformation userService;

    LoginCheck loginCheck=new LoginCheck();

    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> userInformation(@RequestParam("username") String username,
                                                               HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            try {
                Map<String, Object> user = userService.getUserByName(username);
                if (user != null) {
                    response.put("code", 200);
                    response.put("msg", "查询成功");
                    List<Map<String, Object>> data = new ArrayList<>();
                    data.add(user);
                    response.put("Data", data);
                } else {
                    response.put("code", 404);
                    response.put("msg", "用户" + username + "不存在");
                }
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器内部错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/save-user")
    public ResponseEntity<Map<String,Object>> saveUser(@RequestParam("userID")String userID,
                                                       @RequestParam("name")String userName,
                                                       @RequestParam("phone")String phone,
                                                       @RequestParam("isAdmin")String isAdmin,
                                                       @RequestParam("registerDate")String registerDate){
        Map<String,Object> response=new HashMap<>();
        int rows= userService.saveUserInformation(userID,userName,phone,isAdmin,registerDate);
        if (rows>0){
            response.put("code",200);
            response.put("msg","保存成功");
        }else {
            response.put("code",500);
            response.put("msg","保存失败");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("fetch-users")
    public ResponseEntity<Map<String,Object>> fetchUsers(HttpSession session){
        Map<String,Object> response=new HashMap<>();
        if (loginCheck.onLoginCheck(session)){
            List<Map<String,Object>> data=userService.selectUsers();
            response.put("Data",data);
            response.put("code",200);
        }else {
            response.put("code",404);
            response.put("msg","您还未登录");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
