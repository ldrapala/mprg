package luke.projectmprg.repository.dummy;

import luke.projectmprg.ICategoryRepository;
import luke.projectmprg.IProductRepository;
import luke.projectmprg.IRepositoryCatalog;
import luke.projectmprg.IShoppingRepository;

public class DummyRepositoryCatalog implements IRepositoryCatalog {
    
    DummyDB db = new DummyDB();

    @Override
    public IProductRepository getProducts() {
        return new DummyProductRepository(db);
    }

    @Override
    public ICategoryRepository getCategories() {
        return new DummyCategoryRepository(db);
    }

    @Override
    public IShoppingRepository getShopping() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
