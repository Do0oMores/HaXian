package top.mores.haxian.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserReservationDao;

import java.util.List;
import java.util.Map;

@Service
public class UserReservationService {
    @Autowired
    UserReservationDao userReservationDao;

    public List<Map<String, Object>> getUserReservations(Integer userID) {
        List<Map<String, Object>> data = userReservationDao.queryUserReservation(userID);
        if (data == null) {
            return null;
        } else {
            for (Map<String, Object> item : data) {
                Integer productID = (Integer) item.get("productId");
                Map<String, Object> products = userReservationDao.queryProductInformation(productID);
                item.put("productName", products.get("name"));
                item.put("price", products.get("price"));
            }
        }
        return data;
    }
}
