package luke.projectmprg.entity.builder;

import java.sql.ResultSet;
import java.sql.SQLException;
import luke.projectmprg.entity.Entity;

public interface IEntityBuilder <TEntity extends Entity> {
    
    public TEntity build(ResultSet rs) throws SQLException;
    
}
