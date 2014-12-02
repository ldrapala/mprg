package luke.projectmprg;

import java.util.List;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;

public interface IProductRepository extends IRepository<Product> {

    public List<Product> withCategory(Category category);

    public List<Product> withCategory(String categoryName);

    public List<Product> withCategory(int categoryId);

}
