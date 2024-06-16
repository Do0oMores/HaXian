package top.mores.haxian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserLoginDao;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserLoginDao userLoginDao;

    public Map<String, Object> authenticateUser(String userName, String userPassword) {
        Map<String, Object> response = new HashMap<>();
        try {
            String password = userLoginDao.getPasswordByUserName(userName);
            int isAdmin = userLoginDao.getUserRole(userName);
            Integer userID=userLoginDao.getUserID(userName);
            if (userPassword.equals(password)) {
                response.put("code", 200);
                response.put("msg", "登录成功");
                response.put("userID",userID);
                response.put("role", isAdmin == 1 ? "admin" : "user");
            } else {
                response.put("code", 401);
                response.put("msg", "密码错误");
            }
        } catch (EmptyResultDataAccessException e) {
            response.put("code", 404);
            response.put("msg", "用户不存在");
        }
        return response;
    }
}
