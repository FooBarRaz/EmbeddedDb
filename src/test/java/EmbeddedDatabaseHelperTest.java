import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EmbeddedDatabaseHelperTest {

    @Test
    public void constructor_startsServer() throws Exception {

        EmbeddedDataBaseHelper embeddedDatabaseHelper = new EmbeddedDataBaseHelper();

//        new Thread(embeddedDatabaseHelper).run();
        String testData = "testy007";


        JdbcConnectionPool cp = JdbcConnectionPool.
                create("jdbc:h2:tcp://localhost:9123/~/dev/DbTest/testdb1", "sa", "");
        Handle connectionHandler = DBI.open(cp);
//        connectionHandler.execute("create table if not exists foobar " +
//                "(" +
//                    "id int primary key auto_increment, " +
//                    "name varchar(100), " +
//                    "timestamp timestamp default current_timestamp, " +
//                ")");



        Query<Map<String, Object>> query = connectionHandler.createQuery("select * from foobar where name = :name").bind("name", testData);
        int previousSize = query.list().size();

        embeddedDatabaseHelper.insertTestData(testData);

        assertThat(query.list().size(), is(previousSize + 1));
        connectionHandler.commit();

        assertThat(connectionHandler.getConnection().isValid(100), is(true));
        connectionHandler.close();
        assertThat(connectionHandler.getConnection().isValid(100), is(false));

//        embeddedDatabaseHelper.stopServer();
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
