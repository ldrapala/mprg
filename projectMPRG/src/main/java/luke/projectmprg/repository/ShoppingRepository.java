package luke.projectmprg.repository;

import java.sql.Connection;
import java.sql.SQLException;
import luke.projectmprg.Repository;
import luke.projectmprg.entity.Shopping;
import luke.projectmprg.entity.builder.IEntityBuilder;

public class ShoppingRepository extends Repository<Shopping> {

    public ShoppingRepository(Connection connection, IEntityBuilder<Shopping> builder) {
        super(connection, builder);
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
