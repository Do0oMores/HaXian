package top.mores.haxian.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.mores.haxian.Interceptor.LoginInterceptor;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

//    @Autowired
//    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        System.out.println("65645646");
        //注册拦截器
        InterceptorRegistration registration=registry.addInterceptor(new LoginInterceptor());
        //拦截所有路径
        registration.addPathPatterns("/**");
        //不拦截路径
        registration.excludePathPatterns(
                "/login",
                "/register"
        );
    }
}
