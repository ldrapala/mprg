package luke.projectmprg.repository.dummy;

import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.repository.IProductRepository;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;

public class DummyProductRepository implements IProductRepository {

    private DummyDB db;

    public DummyProductRepository(DummyDB db) {
        super();
        this.db = db;
    }

    @Override
    public List<Product> withCategory(Category category) {
        return withCategory(category.getId());
    }

    @Override
    public List<Product> withCategory(String categoryName) {
        for (Category el : db.categories) {
            if (el.getName().equals(categoryName)) {
                return el.getProducts();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> withCategory(int categoryId) {
        for (Category el : db.categories) {
            if (el.getId() == categoryId) {
                return el.getProducts();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Product entity) {
        this.db.products.add(entity);
    }

    @Override
    public void update(Product entity) {
    }

    @Override
    public void delete(Product entity) {
        this.db.products.remove(entity);
    }

    @Override
    public Product get(int id) {
        for (Product el : db.products) {
            if (el.getId() == id) {
                return el;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return this.db.products;
    }

}
