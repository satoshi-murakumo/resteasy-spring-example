package example.configuration;

import org.seasar.doma.jdbc.RequiresNewController;

public class DomaRequiresNewController implements RequiresNewController {

    @Override
    public <R> R requiresNew(Callback<R> callback) throws Throwable {
        return callback.execute();
    }

}
