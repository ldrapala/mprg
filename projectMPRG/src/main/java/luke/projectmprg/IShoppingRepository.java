package luke.projectmprg;

import java.util.List;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;

public interface IShoppingRepository extends IRepository <Shopping> {
    
    public List <Shopping> inShop(Shop shop);
    
    public List <Shopping> inShop(int shopId);
    
    public List <Shopping> inShop(String shopName);
    
}
