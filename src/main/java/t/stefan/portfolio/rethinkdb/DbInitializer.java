package t.stefan.portfolio.rethinkdb;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import t.stefan.portfolio.service.listener.ChatChangesListener;

public class DbInitializer implements InitializingBean {

    @Autowired
    private ChatChangesListener chatChangesListener;

    @Override
    public void afterPropertiesSet() {
        chatChangesListener.pushChangesToWebSocket();
    }
}
