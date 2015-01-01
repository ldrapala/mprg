package luke.projectmprg.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;
import luke.projectmprg.entity.builder.IEntityBuilder;
import luke.projectmprg.repository.IShoppingRepository;
import luke.projectmprg.unitofwork.IUnitOfWork;

public class ShoppingRepository extends Repository<Shopping> {

    public ShoppingRepository(Connection connection, IEntityBuilder<Shopping> builder,
            IUnitOfWork unitOfWork) {
        super(connection, builder, unitOfWork);
    }

    @Override
    protected void setUpUpdateQuery(Shopping entity) throws SQLException {
        this.update.setDate(1, entity.getDate());
        this.update.setInt(2, entity.getId());
    }

    @Override
    protected void setUpInsertQuery(Shopping entity) throws SQLException {
        this.insert.setDate(1, entity.getDate());
    }

    @Override
    protected String getTableName() {
        return "shopping";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO shopping (name) VALUES(?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE shopping SET (name)=(?) WHERE id=?";
    }

}
