package example.configuration;

import org.seasar.doma.jdbc.RequiresNewController;
import org.springframework.stereotype.Component;

@Component
public class DomaRequiresNewController implements RequiresNewController {

    @Override
    @ReadWriteRequiresNew
    public <R> R requiresNew(Callback<R> callback) throws Throwable {
        return callback.execute();
    }

}
