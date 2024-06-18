package top.mores.haxian.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserShoppingCartDao;
import top.mores.haxian.POJO.ShoppingCart;
import top.mores.haxian.POJO.ShoppingCartResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {
    @Autowired
    UserShoppingCartDao userShoppingCartDao;

    public List<Map<String, Object>> getShoppingCart(Integer userID) {
        return userShoppingCartDao.queryUserShoppingCart(userID);
    }

    public ShoppingCartResponse getShoppingCartDetails(Integer userID) {
        List<Map<String, Object>> shoppingCart = getShoppingCart(userID);

        List<ShoppingCart> items = shoppingCart.stream()
                .map(item -> {
                    Integer productID = (Integer) item.get("product_id");
                    Integer quantity = (Integer) item.get("amount");
                    List<Map<String, Object>> productInfo = userShoppingCartDao.queryProductInformation(productID);

                    if (!productInfo.isEmpty()) {
                        Map<String, Object> product = productInfo.get(0);
                        String productName = (String) product.get("name");
                        BigDecimal unitPrice = BigDecimal.valueOf((Double) product.get("price")).setScale(2, RoundingMode.HALF_UP);
                        BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
                        return new ShoppingCart(productName, quantity, unitPrice.doubleValue(), totalPrice.doubleValue(), productID);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        BigDecimal totalCartPrice = items.stream()
                .map(ShoppingCart::getTotalPrice)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        return new ShoppingCartResponse(items, totalCartPrice.doubleValue());
    }

    public Map<String, Object> createOrder(Integer user_id) {
        Map<String, Object> order = new HashMap<>();
        List<Map<String, Object>> shopping = userShoppingCartDao.queryUserShoppingCart(user_id);
        if (!shopping.isEmpty()) {
            for (Map<String, Object> item : shopping) {
                Integer product_id = (Integer) item.get("product_id");
                Integer amount = (Integer) item.get("amount");
                Map<String, Object> productInformation = userShoppingCartDao.queryProductInformation(product_id).get(0);
                BigDecimal unitPrice = BigDecimal.valueOf((Double) productInformation.get("price")).setScale(2, RoundingMode.HALF_UP);
                BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(amount)).setScale(2, RoundingMode.HALF_UP);
                String status = "等待结账";
                userShoppingCartDao.insertOrder(user_id, product_id, status, totalPrice.doubleValue(), amount);
            }
            order.put("code", 200);
            order.put("msg", "订单创建成功");
        } else return null;
        return order;
    }

    public int changeStatus(String status, Integer userID) {
        return userShoppingCartDao.changeOrderStatus(status, userID);
    }

    public int cleanUserShop(Integer userID) {
        return userShoppingCartDao.cleanUserShoppingCart(userID);
    }

    public List<Map<String, Object>> queryOrders(Integer userID) {
        List<Map<String, Object>> orders = new ArrayList<>();
        List<Map<String, Object>> data = userShoppingCartDao.queryOrdersForUser(userID);
        for (Map<String, Object> item : data) {
            Integer productID = (Integer) item.get("product_id");
            List<Map<String, Object>> productInformation = userShoppingCartDao.queryProductInformation(productID);

            if (productInformation != null && !productInformation.isEmpty()) {
                Map<String, Object> information = productInformation.get(0);
                item.put("product_name", information.get("name"));
                item.put("price", information.get("price"));
            } else {
                item.put("product_name", "Unknown Product");
                item.put("price", 0);
            }
            orders.add(item);
        }
        return orders;
    }

    public int cleanOrder(Integer userID){
        return userShoppingCartDao.cleanOrder(userID);
    }
}
