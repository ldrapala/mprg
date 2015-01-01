package luke.projectmprg;

import luke.projectmprg.repository.IRepository;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;

public interface IRepositoryCatalog {
    
    IRepository<Product> getProducts();
    IRepository<Category> getCategories();
    IRepository<Shopping> getShopping();
    IRepository<Shop> getShops();
    
}
