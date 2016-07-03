import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EmbeddedDatabaseHelper {

    private Server server;

    public EmbeddedDatabaseHelper() {
        server = null;
        try {
            server = Server.createTcpServer(
                    "-tcpPort", "9192", "-tcpAllowOthers").start();
        } catch (SQLException e) {

        }

    }

    public DataSource getDataSource() {

        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:~/Documents/dev/EmbeddedDb/testdb0;AUTO_SERVER=TRUE", "sa", "");


//        Handle connectionHandle = DBI.open(cp);
//        connectionHandle.execute("create table if not exists foobar (id int primary key auto_increment, name varchar(100))");
//        connectionHandle.execute("insert into foobar (name) values (?)", "Jeff");
//        connectionHandle.close();

        return cp;
    }

    public void stopServer() {
        server.stop();
    }
}
