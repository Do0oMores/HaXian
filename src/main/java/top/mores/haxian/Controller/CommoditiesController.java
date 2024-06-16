package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.Utils.LoginCheck;
import top.mores.haxian.service.UserService.CommoditiesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommoditiesController {
    @Autowired
    private CommoditiesService commoditiesService;
    LoginCheck loginCheck=new LoginCheck();

    @GetMapping("/commodity")
    public ResponseEntity<Map<String, Object>> showCommodity(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            try {
                List<Map<String, Object>> commodities = commoditiesService.getAllProducts();
                response.put("code", 200);
                response.put("Data", commodities);
            } catch (EmptyResultDataAccessException e) {
                response.put("code", 404);
                response.put("msg", "没有商品信息");
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/add-to-cart")
    public ResponseEntity<Map<String, Object>> addToCart(@RequestParam("userId") Integer userId,
                                                         @RequestParam("productID") Integer productId,
                                                         @RequestParam("quantity") int quantity,
                                                         HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            try {
                boolean success = commoditiesService.addToCart(userId, productId, quantity);
                if (success) {
                    response.put("code", 200);
                    response.put("msg", "添加到购物车成功，库存已更新");
                } else {
                    response.put("code", 400);
                    response.put("msg", "添加到购物车失败，库存不足");
                }
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/remove-from-cart")
    public ResponseEntity<Map<String, Object>> removeFromCart(@RequestParam("userId") Integer userId,
                                                              @RequestParam("productId") Integer productId,
                                                              @RequestParam("quantity") int quantity,
                                                              HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            try {
                Map<String, Object> result = commoditiesService.removeFromCart(userId, productId, quantity);
                if ((Boolean) result.get("remove")) {
                    response.put("code", 200);
                    response.put("msg", "商品数量为0，已从购物车移除");
                } else {
                    response.put("code", 200);
                    response.put("msg", "已从购物车减少商品");
                }
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
