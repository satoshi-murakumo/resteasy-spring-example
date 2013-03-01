package example.infrastructure.db;

import org.joda.time.LocalDateTime;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.context.ApplicationContext;

import example.infrastructure.utill.ApplicationContextProvider;

public class ConventinalEntityListener implements EntityListener<Conventional> {

    @Override
    public void preInsert(Conventional entity, PreInsertContext context) {
        ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();

        entity.setCrtdate(LocalDateTime.now());
    }

    @Override
    public void preUpdate(Conventional entity, PreUpdateContext context) {
        if (context.isEntityChanged()) {
            entity.setUpddate(LocalDateTime.now());
        }
    }

    @Override
    public void preDelete(Conventional entity, PreDeleteContext context) {
    }

    @Override
    public void postInsert(Conventional entity, PostInsertContext context) {
    }

    @Override
    public void postUpdate(Conventional entity, PostUpdateContext context) {
    }

    @Override
    public void postDelete(Conventional entity, PostDeleteContext context) {
    }

}
