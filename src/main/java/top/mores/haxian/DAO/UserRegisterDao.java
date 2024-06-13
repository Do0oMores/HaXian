package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRegisterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int countByUserName(String userName) {
        String sql = "select COUNT(*) from users where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName}, Integer.class);
    }

    public void insertUser(String userName, String password, String phone) {
        String sql = "insert into users(name, pwd, phone) values (?, ?, ?)";
        jdbcTemplate.update(sql, userName, password, phone);
    }
}
