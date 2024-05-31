package top.mores.haxian.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.mores.haxian.POJO.User;

import static top.mores.haxian.Controller.UserController.USER_LOGIN_STATE;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) {
        System.out.println("执行了拦截器");
        try {
            HttpSession session=request.getSession();
            User user=(User) session.getAttribute(USER_LOGIN_STATE);
            if (user!=null){
                return true;
            }else {
                // 重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 可以根据需要在处理请求之后但在视图渲染之前进行处理
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 可以在整个请求完成后进行处理
    }
}
