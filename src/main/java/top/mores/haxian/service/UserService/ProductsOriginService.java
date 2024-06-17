package top.mores.haxian.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.QueryProductsOriginDao;

import java.util.List;
import java.util.Map;

@Service
public class ProductsOriginService {
    @Autowired
    QueryProductsOriginDao queryProductsOriginDao;

    public List<Map<String,Object>> getProductOrigin(String productName){
        Integer productID= queryProductsOriginDao.queryProductIDByProductName(productName);
        return queryProductsOriginDao.productOrigin(productID);
    }
}
