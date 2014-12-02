package luke.projectmprg;

import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;

public interface ICategoryRepository extends IRepository <Category> {
    
    public Category fromProduct(String productName);
    
    public Category fromProduct(int productId);
    
    public Category fromProduct(Product product);
    
}
