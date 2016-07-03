import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import java.util.Map;

public class DatabaseHelper {


    public DatabaseHelper() {
        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Documents/dev/EmbeddedDb/testdb0;AUTO_SERVER=TRUE", "sa", "");


//        JdbcConnectionPool cp = JdbcConnectionPool.create(
//                "jdbc:h2:tcp://localhost:50734/~/test",
//                "sa",
//                "");

        DBI dbi = new DBI(cp);
        Handle connectionHandle = dbi.open();
//        connectionHandle.execute("DROP ALL OBJECTS");
//        connectionHandle.execute("create table if not exists something (id int primary key auto_increment, name varchar(100))");

        connectionHandle.execute("insert into something (name) values (?)", "Beth");

        Query<Map<String, Object>> query = connectionHandle.createQuery("select * from something");

        System.out.println(query);
    }

}
