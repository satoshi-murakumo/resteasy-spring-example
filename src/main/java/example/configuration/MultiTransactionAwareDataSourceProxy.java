package example.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;

public class MultiTransactionAwareDataSourceProxy extends TransactionAwareDataSourceProxy {

    private HashMap<String, DataSource> targetDataSources;

    public MultiTransactionAwareDataSourceProxy(HashMap<String, DataSource> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    @Override
    public DataSource getTargetDataSource() {
        String name = TransactionHelper.getCurrentTransactionQualifier();
        Assert.notNull(name, "current transaction qualifieris null");
        DataSource target = this.targetDataSources.get(name);
        Assert.notNull(target, "current datasource is null.(" + name + ")");
        return target;
    }

    @Override
    public void afterPropertiesSet() {
        // nothing
    }

    public String getCurrenTransactionName() {
        return TransactionHelper.getCurrentTransactionQualifier();
    }

    private static abstract class TransactionHelper extends TransactionAspectSupport {

        private static String getCurrentTransactionQualifier() {
            TransactionAspectSupport.TransactionInfo info = currentTransactionInfo();
            Assert.notNull(info, "transaction not start");
            return info.getTransactionAttribute().getQualifier();
        }

    }
}
