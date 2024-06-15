package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.AdminReservationDao;

import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    private AdminReservationDao reservationDAO;

    public List<Map<String, Object>> getAllReservationsWithDetails() {
        List<Map<String, Object>> reservations = reservationDAO.getAllReservations();
        for (Map<String, Object> reservation : reservations) {
            String userId = reservation.get("userId").toString();
            String userName = reservationDAO.getUserNameById(userId);
            reservation.put("userId", userName != null ? userName : "Unknown User");

            String productId = reservation.get("productId").toString();
            String productName = reservationDAO.getProductNameById(productId);
            reservation.put("productId", productName != null ? productName : "Unknown Product");
        }
        return reservations;
    }
}
