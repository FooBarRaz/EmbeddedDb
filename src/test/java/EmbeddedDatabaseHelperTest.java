import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmbeddedDatabaseHelperTest {

    @Test
    public void constructor_startsServer() throws Exception {
        EmbeddedDatabaseHelper embeddedDatabaseHelper = new EmbeddedDatabaseHelper();

        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:tcp://localhost:9192/~/Documents/dev/EmbeddedDb/testdb0", "sa", "");

        Handle connectionHandler = DBI.open(cp);
        connectionHandler.execute("create table if not exists foobar (id int primary key auto_increment, name varchar(100))");
        connectionHandler.execute("insert into foobar (name) values (?)", "Bar");

        List<Map<String, Object>> list = connectionHandler.createQuery("select * from foobar").list();



        assertThat(connectionHandler.getConnection().isValid(100), is(true));
        connectionHandler.close();
        assertThat(connectionHandler.getConnection().isValid(100), is(false));

        embeddedDatabaseHelper.stopServer();
    }

//    @Test
//    public void constructor_initializesDataSource() throws Exception {
//        EmbeddedDatabaseHelper embeddedDatabaseHelper = new EmbeddedDatabaseHelper();
//
//        Handle connectionHandler = DBI.open(embeddedDatabaseHelper.getDataSource());
//
//        assertThat(connectionHandler.getConnection().isValid(100), is(true));
//        connectionHandler.close();
//        assertThat(connectionHandler.getConnection().isValid(100), is(false));
//    }


}
