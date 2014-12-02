package luke.projectmprg.entity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import luke.projectmprg.entity.Product;

public class ProductBuilder implements IEntityBuilder <Product> {

    @Override
    public Product build(ResultSet rs) throws SQLException {
        Product result = new Product();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        result.setPrice(rs.getDouble("price"));
        result.setWeight(rs.getDouble("weight"));
        return result;
    }

}
