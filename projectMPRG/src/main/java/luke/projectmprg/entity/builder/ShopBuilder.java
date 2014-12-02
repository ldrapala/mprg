package luke.projectmprg.entity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import luke.projectmprg.entity.Shop;

public class ShopBuilder implements IEntityBuilder <Shop> {

    @Override
    public Shop build(ResultSet rs) throws SQLException {
        Shop result = new Shop();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        return result;
    }

}
