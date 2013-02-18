package example.configuration;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.dialect.Dialect;

public class DataSourceConfiguration extends DomaAbstractConfig {

    private Dialect dialect;

    private DataSource dataSource;

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public String getDataSourceName() {
        if (dataSource instanceof MultiTransactionAwareDataSourceProxy) {
            return ((MultiTransactionAwareDataSourceProxy)dataSource).getCurrenTransactionName();
        }
        return super.getDataSourceName();
    }

}
