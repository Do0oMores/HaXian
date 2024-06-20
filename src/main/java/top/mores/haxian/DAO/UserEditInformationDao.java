package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserEditInformationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int editInformation(Integer userID,String name,String pwd,String phone){
        String sql="update users set name=?,pwd=?,phone=? where user_id=?";
        return jdbcTemplate.update(sql,name,pwd,phone,userID);
    }

    public Map<String, Object> getInformation(Integer userID){
        String sql="select id,name,pwd,phone from users where id=?";
        return jdbcTemplate.queryForMap(sql,userID);
    }
}
