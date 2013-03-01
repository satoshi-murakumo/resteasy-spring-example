package example.infrastructure.msgpack;

import java.io.IOException;

import org.joda.time.LocalDateTime;
import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

public class LocalDateTimeTemplate extends AbstractTemplate<LocalDateTime> {

    @Override
    public void write(Packer pk, LocalDateTime v, boolean required) throws IOException {
        if (v == null) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }
        pk.write(v.toDateTime().getMillis());
    }

    @Override
    public LocalDateTime read(Unpacker u, LocalDateTime to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }
        return new LocalDateTime(u.readLong());
    }

}
