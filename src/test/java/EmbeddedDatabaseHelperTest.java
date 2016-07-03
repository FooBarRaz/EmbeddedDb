import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class EmbeddedDatabaseHelperTest {

    @Test
    public void constructor_initializesDataSource() throws Exception {

        EmbeddedDatabaseHelper embdeddedDatabaseHelper = new EmbeddedDatabaseHelper();

        Handle connectionHandler = DBI.open(embdeddedDatabaseHelper.getDataSource());
        assertThat(connectionHandler, notNullValue());
    }


}
