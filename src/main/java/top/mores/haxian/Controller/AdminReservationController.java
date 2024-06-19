package top.mores.haxian.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.Utils.LoginCheck;
import top.mores.haxian.service.AdminService.ReservationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminReservationController {

    @Autowired
    private ReservationService reservationService;

    LoginCheck loginCheck = new LoginCheck();

    @GetMapping("/reservation")
    public ResponseEntity<Map<String, Object>> reservationData(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (loginCheck.onLoginCheck(session)) {
            try {
                List<Map<String, Object>> reservations = reservationService.getAllReservationsWithDetails();
                response.put("code", 200);
                response.put("msg", "查询成功");
                response.put("Data", reservations);
            } catch (Exception e) {
                response.put("code", 500);
                response.put("msg", "服务器内部错误");
            }
        } else {
            response.put("code", 404);
            response.put("msg", "您还未登录！");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/update-reservation-status")
    public ResponseEntity<Map<String, Object>> updateReservationStatus(@RequestParam("reservation_id") String reservationID,
                                                                       @RequestParam("status") String status) {
        Map<String, Object> response = new HashMap<>();
        if (status.equals("已预约")) {
            status = "已完成";
        } else {
            status = "已预约";
        }
        int rows = reservationService.updateReservation(reservationID, status);
        if (rows > 0) {
            response.put("code", 200);
            response.put("msg", "更改状态成功");
            response.put("status", status);
        } else {
            response.put("code", 500);
            response.put("msg", "更改状态失败");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
