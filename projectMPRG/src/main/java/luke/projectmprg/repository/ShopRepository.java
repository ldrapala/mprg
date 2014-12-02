package luke.projectmprg.repository;

import java.sql.Connection;
import java.sql.SQLException;
import luke.projectmprg.Repository;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.builder.IEntityBuilder;

public class ShopRepository extends Repository<Shop> {

    public ShopRepository(Connection connection, IEntityBuilder<Shop> builder) {
        super(connection, builder);
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
