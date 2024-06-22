package top.mores.haxian.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mores.haxian.DAO.QueryProductsDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@Service
public class queryProductsInformationService {

    @Autowired
    private QueryProductsDao productDAO;

    public Map<String, Object> getProductByName(String productName) {
        return productDAO.findProductByName(productName);
    }

    public int saveProductInformation(Integer productID,
                                      String productName,
                                      String description,
                                      double price,
                                      Integer stock,
                                      String origin,
                                      String productionDate,
                                      String support,
                                      String createTime,
                                      Integer shelfLife,
                                      String type){
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(createTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            return 0;
        }
        return productDAO.saveProduct(productID,productName,description,price,stock,origin,productionDate,support,localDateTime,shelfLife,type);
    }

    public List<Map<String,Object>> selectCommodities(){
        return productDAO.selectCommodities();
    }
}
