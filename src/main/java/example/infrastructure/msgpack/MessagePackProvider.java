package example.infrastructure.msgpack;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.msgpack.MessagePack;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagePackProvider {

    @Bean
    public MessagePack getMessagePack() {
        MessagePack msgPack = new MessagePack();
        msgPack.register(LocalDate.class, new LocalDateTemplate());
        msgPack.register(LocalDateTime.class, new LocalDateTimeTemplate());
        return msgPack;
    }
}
