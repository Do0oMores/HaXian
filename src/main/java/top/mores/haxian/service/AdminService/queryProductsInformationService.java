package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.QueryProductsDao;

import java.util.Map;

@Service
public class queryProductsInformationService {

    @Autowired
    private QueryProductsDao productDAO;

    public Map<String, Object> getProductByName(String productName) {
        return productDAO.findProductByName(productName);
    }
}
