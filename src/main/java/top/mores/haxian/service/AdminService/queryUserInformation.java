package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserInformationDao;

import java.util.Map;

@Service
public class queryUserInformation {

    @Autowired
    private UserInformationDao userDAO;

    public Map<String, Object> getUserByName(String username) {
        Map<String, Object> user = userDAO.findUserByName(username);
        if (user != null) {
            user.put("is_admin", (Integer) user.get("is_admin") == 1 ? "是" : "否");
        }
        return user;
    }
}
