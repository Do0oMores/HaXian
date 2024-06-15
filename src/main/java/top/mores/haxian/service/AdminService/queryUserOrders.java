package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.queryUsersOrderDao;

import java.util.List;
import java.util.Map;

@Service
public class queryUserOrders {

    @Autowired
    private queryUsersOrderDao orderDAO;

    public List<Map<String, Object>> getAllOrdersWithDetails() {
        List<Map<String, Object>> orders = orderDAO.getAllOrders();
        for (Map<String, Object> order : orders) {
            String userId = order.get("user_id").toString();
            String userName = orderDAO.getUserNameById(userId);
            order.put("user_name", userName != null ? userName : "Unknown User");

            String productId = order.get("product_id").toString();
            String productName = orderDAO.getProductNameById(productId);
            order.put("product_name", productName != null ? productName : "Unknown Product");
        }
        return orders;
    }

}
