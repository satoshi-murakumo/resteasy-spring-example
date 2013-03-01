package example.infrastructure.msgpack;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.msgpack.MessageTypeException;
import org.msgpack.packer.Packer;
import org.msgpack.template.AbstractTemplate;
import org.msgpack.unpacker.Unpacker;

public class LocalDateTemplate extends AbstractTemplate<LocalDate> {

    @Override
    public void write(Packer pk, LocalDate v, boolean required) throws IOException {
        if (v == null) {
            if (required) {
                throw new MessageTypeException("Attempted to write null");
            }
            pk.writeNil();
            return;
        }
        pk.write(v.toDateTimeAtStartOfDay().getMillis());
    }

    @Override
    public LocalDate read(Unpacker u, LocalDate to, boolean required) throws IOException {
        if (!required && u.trySkipNil()) {
            return null;
        }
        return new LocalDate(u.readLong());
    }

}
