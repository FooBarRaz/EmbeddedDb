import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class DatabaseHelper {


    public DatabaseHelper() {
//        JdbcConnectionPool cp = JdbcConnectionPool.
//                create("jdbc:h2:~/dev/DbTest/test;AUTO_SERVER=TRUE", "sa", "");



        JdbcConnectionPool cp = JdbcConnectionPool.create(
                "jdbc:h2:tcp://localhost:50734/~/test",
                "sa",
                "");

        DBI dbi = new DBI(cp);
        Handle connectionHandle = dbi.open();
        connectionHandle.execute("create table if not exists something (id int primary key, name varchar(100))");

        connectionHandle.execute("insert into something (id, name) values (?, ?)", 1, "Brian");
    }

}
