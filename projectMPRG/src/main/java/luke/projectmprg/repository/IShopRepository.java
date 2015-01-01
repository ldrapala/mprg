package luke.projectmprg.repository;

import java.util.List;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;

public interface IShopRepository extends IRepository <Shop> {
    
    List<Shop> withProduct(Product product);
    List<Shop> withProduct(int productId);
    List<Shop> withProduct(String productName);
}
