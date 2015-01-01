package luke.projectmprg.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.builder.IEntityBuilder;
import luke.projectmprg.unitofwork.IUnitOfWork;

public class ShopRepository extends Repository<Shop> {

    public ShopRepository(Connection connection, IEntityBuilder<Shop> builder,
            IUnitOfWork unitOfWork) {
        super(connection, builder, unitOfWork);
    }

    @Override
    protected void setUpUpdateQuery(Shop entity) throws SQLException {
        this.update.setString(1, entity.getName());
        this.update.setInt(2, entity.getId());
    }

    @Override
    protected void setUpInsertQuery(Shop entity) throws SQLException {
        this.insert.setString(1, entity.getName());
    }

    @Override
    protected String getTableName() {
        return "shop";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO shop (name) VALUES(?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE shop SET (name)=(?) WHERE id=?";
    }

}
