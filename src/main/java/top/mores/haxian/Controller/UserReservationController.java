package top.mores.haxian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mores.haxian.service.UserService.UserReservationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserReservationController {
    @Autowired
    UserReservationService userReservationService;

    @GetMapping("/get-user-reservation")
    public ResponseEntity<Map<String,Object>> userReservation(@RequestParam("userID")Integer userID){
        Map<String,Object> response=new HashMap<>();
        List<Map<String,Object>> data=userReservationService.getUserReservations(userID);
        if (data==null){
            response.put("code",404);
            response.put("msg","没有查询到预约信息");
        }else {
            response.put("Data",data);
            response.put("code",200);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
