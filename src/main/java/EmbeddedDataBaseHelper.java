import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Update;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmbeddedDataBaseHelper implements Runnable {

    private Server server;
    private JdbcConnectionPool connectionPool;

    public EmbeddedDataBaseHelper() throws SQLException {


    }

    public void insertTestData(String testData) throws SQLException {
//        connectionPool.getM

        Handle connectionHandler = DBI.open(getDataSource());
        Update statement = connectionHandler.createStatement("insert into foobar (name) values (:name)").bind("name", testData);

        statement.execute();
        connectionHandler.commit();
        connectionHandler.close();

    }

    public JdbcConnectionPool getDataSource() {

        if (connectionPool == null) {
            connectionPool = JdbcConnectionPool.
                    create("jdbc:h2:tcp://localhost:9123/~/dev/DbTest/testdb1", "sa", "");

        }

        return connectionPool;
    }

    public void stopServer() {
        server.stop();
    }

    public void run() {
        server = null;
        try {
            server = Server.createTcpServer(
                    "-tcp", "-tcpPort", "9123", "-tcpAllowOthers").start();
        } catch (SQLException e) {

        }
    }
}
