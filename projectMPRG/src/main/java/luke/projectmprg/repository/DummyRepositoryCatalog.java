package luke.projectmprg.repository;

import luke.projectmprg.IProductRepository;
import luke.projectmprg.IRepositoryCatalog;

public class DummyRepositoryCatalog implements IRepositoryCatalog {
    
    DummyDB db = new DummyDB();

    @Override
    public IProductRepository getProducts() {
        return new DummyProductRepository(db);
    }

}