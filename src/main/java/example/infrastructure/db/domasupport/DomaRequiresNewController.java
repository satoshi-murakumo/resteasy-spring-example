package example.infrastructure.db.domasupport;

import org.seasar.doma.jdbc.RequiresNewController;
import org.springframework.stereotype.Component;

import example.infrastructure.transaction.ReadWriteRequiresNew;

@Component
public class DomaRequiresNewController implements RequiresNewController {

    @Override
    @ReadWriteRequiresNew
    public <R> R requiresNew(Callback<R> callback) throws Throwable {
        return callback.execute();
    }

}
