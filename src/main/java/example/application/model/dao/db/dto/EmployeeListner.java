package example.application.model.dao.db.dto;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import example.infrastructure.transaction.RequestMetaData;
import example.infrastructure.utill.ApplicationContextProvider;

public class EmployeeListner implements EntityListener<Employee> {

    private Logger logger = LoggerFactory.getLogger(EmployeeListner.class);

    @Override
    public void preInsert(Employee entity, PreInsertContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void preUpdate(Employee entity, PreUpdateContext context) {
        ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();
        RequestMetaData metaData = appContext.getBean(RequestMetaData.class);

        logger.debug(metaData.getClientId());
        logger.debug(metaData.getRequestUri());
    }

    @Override
    public void preDelete(Employee entity, PreDeleteContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postInsert(Employee entity, PostInsertContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postUpdate(Employee entity, PostUpdateContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void postDelete(Employee entity, PostDeleteContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
