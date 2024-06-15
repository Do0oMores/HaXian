package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserInformationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findUserByName(String username) {
        String sql = "select id,name,phone,is_admin,register_date from users where name = ?";
        try {
            return jdbcTemplate.queryForMap(sql, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
