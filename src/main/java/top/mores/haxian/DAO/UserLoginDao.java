package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getPasswordByUserName(String userName) {
        String sql = "select pwd from users where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName}, String.class);
    }

    public int getUserRole(String userName) {
        String sql = "select is_admin from users where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName}, int.class);
    }
}
