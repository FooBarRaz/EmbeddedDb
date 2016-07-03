import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Raz on 7/3/2016.
 */
public class EmbeddedDatabaseHelper {


    public DataSource getDataSource() {

        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Documents/dev/EmbeddedDb/testdb0;AUTO_SERVER=TRUE", "sa", "");


        Handle connectionHandle = DBI.open(cp);
        connectionHandle.execute("create table if not exists foobar (id int primary key auto_increment, name varchar(100))");
        connectionHandle.execute("insert into foobar (name) values (?)", "Jeff");

        return cp;
    }

}
