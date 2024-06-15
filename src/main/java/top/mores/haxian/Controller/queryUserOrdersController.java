package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.mores.haxian.Utils.LoginCheck;
import top.mores.haxian.service.AdminService.queryUserOrders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class queryUserOrdersController {
    @Autowired
    private queryUserOrders orderService;

    LoginCheck loginCheck=new LoginCheck();

    @GetMapping("/orders")
    public ResponseEntity<Map<String, Object>> userOrders(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            try {
                List<Map<String, Object>> orders = orderService.getAllOrdersWithDetails();
                response.put("code", 200);
                response.put("msg", "查询成功");
                response.put("Data", orders);
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
