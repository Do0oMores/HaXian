package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.Utils.LoginCheck;
import top.mores.haxian.service.AdminService.queryProductsInformationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QueryProductsController {

    @Autowired
    private queryProductsInformationService productService;

    LoginCheck loginCheck=new LoginCheck();

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> productsInformation(@RequestParam("productName") String productName,
                                                                   HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            Map<String, Object> product = productService.getProductByName(productName);
            if (product != null) {
                response.put("code", 200);
                response.put("msg", "查询成功");
                List<Map<String, Object>> data = new ArrayList<>();
                data.add(product);
                response.put("Data", data);
            } else {
                response.put("code", 404);
                response.put("msg", "没有名为" + productName + "的商品");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
