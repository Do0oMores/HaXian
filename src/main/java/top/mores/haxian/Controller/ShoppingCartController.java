package top.mores.haxian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.POJO.ShoppingCartResponse;
import top.mores.haxian.service.UserService.ShoppingCartService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

    @GetMapping("/show-shopping")
    public ResponseEntity<Map<String, Object>> showShoppingCart(@RequestParam("userID") Integer UserID) {
        Map<String, Object> response = new HashMap<>();
        if (UserID == null) {
            response.put("code", 404);
            response.put("msg", "您还未登录");
        } else {
            ShoppingCartResponse shoppingCartData = service.getShoppingCartDetails(UserID);
            response.put("code", 200);
            response.put("Data", shoppingCartData);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestParam("UserID") Integer userID) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> create = service.createOrder(userID);
        if (create == null) {
            response.put("code", 404);
            response.put("msg", "您的购物车内没有商品");
        } else {
            response.put("code", create.get("code"));
            response.put("msg", create.get("msg"));
            List<Map<String, Object>> data = service.queryOrders(userID);
            response.put("Data", data);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestParam("UserID") Integer userID) {
        Map<String, Object> response = new HashMap<>();
        int rows1 = service.cleanUserShop(userID);
        int rows2 = service.changeStatus("交易成功", userID);
        if (rows1 > 0 && rows2 > 0) {
            response.put("code", 200);
            response.put("msg", "感谢购买");
        } else {
            response.put("code", 404);
            response.put("msg", "没有查询到订单信息");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cleanorders")
    public ResponseEntity<Map<String, Object>> cleanOrder(@RequestParam("userID") Integer userID) {
        Map<String, Object> response = new HashMap<>();
        int rows = service.cleanOrder(userID);
        if (rows > 0) {
            response.put("code", 200);
        } else {
            response.put("code", 404);
            response.put("msg", "删除未处理订单出错");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
