package top.mores.haxian.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserShoppingCartDao;
import top.mores.haxian.POJO.ShoppingCart;
import top.mores.haxian.POJO.ShoppingCartResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
}
