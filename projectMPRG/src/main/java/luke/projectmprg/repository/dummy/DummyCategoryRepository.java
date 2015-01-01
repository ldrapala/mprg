package luke.projectmprg.repository.dummy;

import java.util.List;
import luke.projectmprg.repository.ICategoryRepository;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;

public class DummyCategoryRepository implements ICategoryRepository {
    
    private DummyDB db;

    public DummyCategoryRepository(DummyDB db) {
        this.db = db;
    }

    @Override
    public Category fromProduct(String productName) {
        for (Product el : db.products) {
            if (el.getName().equals(productName)) {
                return el.getCategory();
            }
        }
        return null;
    }

    @Override
    public Category fromProduct(int productId) {
        for (Product el : db.products) {
            if (el.getId() == productId) {
                return el.getCategory();
            }
        }
        return null;
    }

    @Override
    public Category fromProduct(Product product) {
        return fromProduct(product.getId());
    }

    @Override
    public void save(Category entity) {
        this.db.categories.add(entity);
    }

    @Override
    public void update(Category entity) {
    }

    @Override
    public void delete(Category entity) {
        this.db.categories.remove(entity);
    }

    @Override
    public Category get(int id) {
        for (Category el : db.categories) {
            if (el.getId() == id) {
                return el;
            }
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        return db.categories;
    }

}
