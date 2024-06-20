package top.mores.haxian.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.UserEditInformationDao;

import java.util.Map;

@Service
public class EditInformationService {
    @Autowired
    UserEditInformationDao userEditInformationDao;

    public Map<String,Object> getUserInformation(Integer userID){
        return userEditInformationDao.getInformation(userID);
    }

}
