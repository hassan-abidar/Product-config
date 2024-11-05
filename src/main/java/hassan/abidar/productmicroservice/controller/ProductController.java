package hassan.abidar.productmicroservice.controller;

import hassan.abidar.productmicroservice.Model.Product;
import hassan.abidar.productmicroservice.configurations.ApplicationPropertiesConfiguration;
import hassan.abidar.productmicroservice.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements HealthIndicator {
    @Autowired
    ProductDao productDao;
    @Autowired
    ApplicationPropertiesConfiguration appPro;
    @Override
    public Health health() {
        return null;
    }

    @GetMapping("/Produits")
    public List<Product> getProducts(){
        List<Product> products = productDao.findAll();
        if (products.isEmpty()){
            System.out.println("Aucun produit est disponible");
        }
        List<Product> listLimitee = products.subList(0, appPro.getLimitDeProduits());
        System.out.println("getLimit"+appPro.getLimitDeProduits());
        return listLimitee;
    }
}
