package luke.projectmprg.entity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import luke.projectmprg.entity.Category;

public class CategoryBuilder implements IEntityBuilder<Category> {

    @Override
    public Category build(ResultSet rs) throws SQLException {
        Category result = new Category();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        return result;
    }

}
