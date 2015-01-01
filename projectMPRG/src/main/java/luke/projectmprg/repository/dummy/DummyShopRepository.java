package luke.projectmprg.repository.dummy;

import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.repository.IShopRepository;

public class DummyShopRepository implements IShopRepository {
    
    private DummyDB db;

    public DummyShopRepository(DummyDB db) {
        this.db = db;
    }
    
    @Override
    public List<Shop> withProduct(Product product) {
        return withProduct(product.getId());
    }

    @Override
    public List<Shop> withProduct(int productId) {
        for (Product el : db.products) {
            if (el.getId() == productId) {
                return el.getShops();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Shop> withProduct(String productName) {
        for (Product el : db.products) {
            if (el.getName().equals(productName)) {
                return el.getShops();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Shop entity) {
        this.db.shops.add(entity);
    }

    @Override
    public void update(Shop entity) {
    }

    @Override
    public void delete(Shop entity) {
        this.db.shops.remove(entity);
    }

    @Override
    public Shop get(int id) {
         for (Shop el : db.shops) {
            if (el.getId() == id) {
                return el;
            }
        }
        return null;
    }

    @Override
    public List<Shop> getAll() {
        return db.shops;
    }

}
