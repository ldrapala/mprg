package luke.projectmprg.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.builder.IEntityBuilder;
import luke.projectmprg.unitofwork.IUnitOfWork;

public class CategoryRepository extends Repository<Category> {

    public CategoryRepository(Connection connection, IEntityBuilder <Category> builder, 
            IUnitOfWork unitOfWork) {
        super(connection, builder, unitOfWork);
    }

    @Override
    protected void setUpUpdateQuery(Category entity) throws SQLException {
        this.update.setString(1, entity.getName());
        this.update.setInt(2, entity.getId());
    }

    @Override
    protected void setUpInsertQuery(Category entity) throws SQLException {
        this.insert.setString(1, entity.getName());
    }

    @Override
    protected String getTableName() {
        return "category";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO category (name) VALUES(?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE category SET (name)=(?) WHERE id=?";
    }

}
