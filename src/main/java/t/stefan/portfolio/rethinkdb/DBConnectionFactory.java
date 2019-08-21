package t.stefan.portfolio.rethinkdb;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DBConnectionFactory {

    private String host;
    private String username;
    private String password;

    public Connection createConnection() {
        return RethinkDB.r
                .connection()
                .hostname(host)
                .user(username, password)
                .connect();
    }
}
