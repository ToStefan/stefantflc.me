package t.stefan.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import t.stefan.portfolio.rethinkdb.DBConnectionFactory;
import t.stefan.portfolio.rethinkdb.DbInitializer;

@Configuration
public class RethinkDBConfig {

    @Value("${rethinkdb.url}")
    private String dbUrl;

    @Value("${rethinkdb.user}")
    private String dbUsername;

    @Value("${rethinkdb.pass}")
    private String dbPassword;

    @Bean
    public DBConnectionFactory connectionFactory() {
        return new DBConnectionFactory(dbUrl, dbUsername, dbPassword);
    }

    @Bean
    public DbInitializer dbInitializer() {
        return new DbInitializer();
    }
}

