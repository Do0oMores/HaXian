package top.mores.haxian.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class UserInformationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findUserByName(String username) {
        String sql = "select id, name, phone, is_admin, register_date from users where name like ?";
        try {
            return jdbcTemplate.queryForMap(sql, "%" + username + "%");
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public int saveUser(Integer userID, String name, String phone, Integer isAdmin, LocalDateTime registerDate) {
        String sql = "update users set name=?,phone=?,is_admin=?,register_date=? where id=?";
        return jdbcTemplate.update(sql, name, phone, isAdmin, registerDate, userID);
    }

    public List<Map<String, Object>> selectUsers() {
        String sql = "select id,name,phone,is_admin,register_date from users";
        return jdbcTemplate.queryForList(sql);
    }
}
