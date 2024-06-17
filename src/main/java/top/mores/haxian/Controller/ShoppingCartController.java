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
            ShoppingCartResponse shoppingCartData=service.getShoppingCartDetails(UserID);
            response.put("code",200);
            response.put("Data",shoppingCartData);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
