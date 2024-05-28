package top.mores.haxian.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getUsers(){
        String sql="select * from users";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }
}
