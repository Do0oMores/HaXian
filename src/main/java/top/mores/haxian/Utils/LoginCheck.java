package top.mores.haxian.Utils;

import jakarta.servlet.http.HttpSession;
import top.mores.haxian.POJO.User;

import static top.mores.haxian.Controller.userLoginController.USER_LOGIN_STATE;

public class LoginCheck {

    User user;

    public boolean onLoginCheck(HttpSession session) {
        Object check = session.getAttribute(USER_LOGIN_STATE);
        return check != null;
    }
}
