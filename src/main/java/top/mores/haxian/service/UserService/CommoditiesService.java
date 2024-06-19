package top.mores.haxian.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.CommoditiesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommoditiesService {
    @Autowired
    private CommoditiesDao commoditiesDao;

    public List<Map<String, Object>> getAllProducts() {
        return commoditiesDao.getAllProducts();
    }

    public boolean addToCart(Integer userId, Integer productId, int quantity) {
        int rowsAffected = commoditiesDao.updateProductStock(productId, quantity);
        if (rowsAffected > 0) {
            int addCommodity = commoditiesDao.updateCommodityAmount(userId, productId, quantity);
            if (addCommodity <= 0) {
                commoditiesDao.addCommodityToCart(userId, productId, quantity);
            }
            return true;
        }
        return false;
    }

    public Map<String, Object> removeFromCart(Integer userId, Integer productId, int quantity) {
        int currentAmount = commoditiesDao.getCommodityAmount(userId, productId);
        Map<String, Object> result = new HashMap<>();
        if (currentAmount <= quantity) {
            commoditiesDao.removeCommodityFromCart(userId, productId);
            result.put("remove", true);
        } else {
            commoditiesDao.updateCommodityAmount(userId, productId, -quantity);
            result.put("remove", false);
        }
        commoditiesDao.restoreProductStock(productId, quantity);
        return result;
    }

    public int addReservation(Integer userID, Integer productID, Integer amount) {
        return commoditiesDao.addReservation(userID, productID, amount);
    }
}
