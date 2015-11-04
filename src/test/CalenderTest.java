import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by MattBrown on 11/4/15.
 */
public class CalenderTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        Calender.createTables(conn);
        return conn;
    }
    public void endConnection(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE events");
        conn.close();
    }
    @Test
    public void testEvents() throws SQLException {
        Connection conn = startConnection();
        Calender.insertEvent(conn, "This is a test", LocalDateTime.now());
        ArrayList<Event> events = Calender.selectEvents(conn);
        endConnection(conn);

        assertTrue(events.size() == 1);
    }

}