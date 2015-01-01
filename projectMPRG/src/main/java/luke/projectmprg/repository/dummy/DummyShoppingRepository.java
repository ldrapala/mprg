package luke.projectmprg.repository.dummy;

import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;
import luke.projectmprg.repository.IShoppingRepository;

public class DummyShoppingRepository implements IShoppingRepository {
    
    private DummyDB db;

    public DummyShoppingRepository(DummyDB db) {
        this.db = db;
    }
    
    @Override
    public List<Shopping> inShop(Shop shop) {
        return inShop(shop.getId());
    }

    @Override
    public List<Shopping> inShop(int shopId) {
        for (Shop el : db.shops) {
            if(el.getId() == shopId){
                return el.getShopping();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<Shopping> inShop(String shopName) {
        for (Shop el : db.shops) {
            if(el.getName().equals(shopName)){
                return el.getShopping();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Shopping entity) {
        this.db.shopping.add(entity);
    }

    @Override
    public void update(Shopping entity) {
    }

    @Override
    public void delete(Shopping entity) {
         this.db.shopping.remove(entity);
    }

    @Override
    public Shopping get(int id) {
         for (Shopping el : db.shopping) {
            if (el.getId() == id) {
                return el;
            }
        }
        return null;
    }

    @Override
    public List<Shopping> getAll() {
        return db.shopping;
    }

}
