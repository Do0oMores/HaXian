package top.mores.haxian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserRegisterDao;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    @Autowired
    private UserRegisterDao userRegisterDao;

    public Map<String, Object> registerUser(String userName, String phone, String password) {
        Map<String, Object> response = new HashMap<>();
        int count = userRegisterDao.countByUserName(userName);
        if (count > 0) {
            response.put("code", 401);
            response.put("msg", "已存在该用户名");
        } else {
            userRegisterDao.insertUser(userName, password, phone);
            response.put("code", 200);
            response.put("msg", "注册成功");
        }
        return response;
    }
}
