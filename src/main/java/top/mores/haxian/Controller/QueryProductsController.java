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
                response.put("msg", "没有名为 " + productName + " 的商品");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/save-product")
    public ResponseEntity<Map<String,Object>> saveProduct(@RequestParam("product_id")String productID,
                                                          @RequestParam("name")String name,
                                                          @RequestParam("description")String description,
                                                          @RequestParam("price")String price,
                                                          @RequestParam("stock")String stock,
                                                          @RequestParam("origin")String origin,
                                                          @RequestParam("product_date")String productDate,
                                                          @RequestParam("support")String support,
                                                          @RequestParam("create_time")String createTime,
                                                          @RequestParam("shelf_life")String shelfLife,
                                                          @RequestParam("type")String type){
        Map<String,Object> response=new HashMap<>();
        Integer product_id= Integer.valueOf(productID);
        double product_price= Double.parseDouble(price);
        Integer product_stock=Integer.valueOf(stock);
        Integer shelf_life= Integer.valueOf(shelfLife);
        int rows=productService.saveProductInformation(product_id,name,description,product_price,product_stock,origin,productDate,support,createTime,shelf_life,type);
        if (rows>0){
            response.put("code",200);
            response.put("msg","编辑已保存");
        }else {
            response.put("code",500);
            response.put("msg","保存失败");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/fetch-commodities")
    public ResponseEntity<Map<String,Object>> fetchCommodities(HttpSession session){
        Map<String,Object> response=new HashMap<>();
        if (loginCheck.onLoginCheck(session)){
            List<Map<String,Object>> data=productService.selectCommodities();
            response.put("Data",data);
            response.put("code",200);
        }else {
            response.put("code",404);
            response.put("msg","您还未登录");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
