package top.mores.haxian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.mores.haxian.service.RegisterService;

import java.util.Map;

@Controller
public class userRegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserRegisterData(@RequestParam("username") String registerUsername,
                                                                   @RequestParam("phone") String registerPhoneNumber,
                                                                   @RequestParam("userpassword") String registerPassword) {
        Map<String, Object> response = registerService.registerUser(registerUsername, registerPhoneNumber, registerPassword);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}