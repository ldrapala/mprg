package luke.projectmprg.entity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import luke.projectmprg.entity.Shopping;

public class ShoppingBuilder implements IEntityBuilder <Shopping> {

    @Override
    public Shopping build(ResultSet rs) throws SQLException {
        Shopping result = new Shopping();
        result.setId(rs.getInt("id"));
        result.setDate(rs.getDate("date"));
        return result;
    }

}
