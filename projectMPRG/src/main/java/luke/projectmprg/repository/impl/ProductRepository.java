package luke.projectmprg.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.builder.IEntityBuilder;
import luke.projectmprg.unitofwork.IUnitOfWork;

public class ProductRepository extends Repository<Product> {

    public ProductRepository(Connection connection, IEntityBuilder<Product> builder,
            IUnitOfWork unitOfWork) {
        super(connection, builder, unitOfWork);
    }

    @Override
    protected void setUpUpdateQuery(Product entity) throws SQLException {
        this.update.setString(1, entity.getName());
        this.update.setDouble(2, entity.getPrice());
//            update.setInt(3, entity.getCategory().getId());
        this.update.setDouble(3, entity.getWeight());
        this.update.setInt(4, entity.getId());
    }

    @Override
    protected void setUpInsertQuery(Product entity) throws SQLException {
        this.insert.setString(1, entity.getName());
        this.insert.setDouble(2, entity.getPrice());
//            insert.setInt(3, entity.getCategory().getId());
        this.insert.setDouble(3, entity.getWeight());
    }

    @Override
    protected String getTableName() {
        return "product";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO product(name, price, weight)"
                + "VALUES(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE product SET (name, price, weight)=(?,?,?) WHERE id=?";
    }

}
