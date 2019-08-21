package t.stefan.portfolio.web.controller;

import com.rethinkdb.RethinkDB;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import t.stefan.portfolio.rethinkdb.DBConnectionFactory;
import t.stefan.portfolio.util.Constants;
import t.stefan.portfolio.web.dto.ChatMessageDTO;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/chat")
public class ChatController {

    private final DBConnectionFactory DBConnectionFactory;

    @PostMapping(value = "/{channel-name}")
    public ChatMessageDTO postMessage(@RequestBody ChatMessageDTO chatMessageDTO,
                                      @PathVariable("channel-name") String channelName) {
        chatMessageDTO.setTime(OffsetDateTime.now());
        RethinkDB.r.db(Constants.RETHINK_DB_NAME).table(channelName).insert(chatMessageDTO)
                .run(DBConnectionFactory.createConnection());
        return chatMessageDTO;
    }

    @GetMapping(value = "/{channel-name}")
    public List<ChatMessageDTO> getMessages(@PathVariable("channel-name") String channelName) {
        List<ChatMessageDTO> messages = RethinkDB.r.db(Constants.RETHINK_DB_NAME).table(channelName)
                .orderBy().optArg("index", RethinkDB.r.desc("time"))
                .limit(30)
                .orderBy("time")
                .run(DBConnectionFactory.createConnection(), ChatMessageDTO.class);
        return messages;
    }
}

