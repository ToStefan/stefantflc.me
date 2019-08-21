package t.stefan.portfolio.service.listener;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.rethinkdb.DBConnectionFactory;
import t.stefan.portfolio.util.Constants;
import t.stefan.portfolio.web.dto.ChatMessageDTO;

@AllArgsConstructor
@Service
public class ChatChangesListener {

    private final DBConnectionFactory DBConnectionFactory;
    private final SimpMessagingTemplate webSocket;

    @Async
    public void pushChangesToWebSocket() {
        Cursor<ChatMessageDTO> cursor = RethinkDB.r.db(Constants.RETHINK_DB_NAME).table("global_channel").changes()
                .getField("new_val")
                .run(DBConnectionFactory.createConnection(), ChatMessageDTO.class);
        while (cursor.hasNext()) {
            ChatMessageDTO chatMessageDTO = cursor.next();
            webSocket.convertAndSend("/channel/global_channel", chatMessageDTO);
        }
    }
}

