package example.infrastructure.db.domasupport;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.DomaAbstractConfig;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.RequiresNewController;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;

import example.infrastructure.db.MultiTransactionAwareDataSourceProxy;

public class DataSourceConfiguration extends DomaAbstractConfig {

    private DataSource dataSource;

    private Dialect dialect;

    private SqlFileRepository sqlFileRepository;

    private JdbcLogger jdbcLogger;

    private RequiresNewController requiresNewController;


    public DataSourceConfiguration() {
        this.sqlFileRepository = super.getSqlFileRepository();
        this.jdbcLogger = new DataAccessLogger();
        this.requiresNewController = super.getRequiresNewController();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public String getDataSourceName() {
        if (dataSource instanceof MultiTransactionAwareDataSourceProxy) {
            return ((MultiTransactionAwareDataSourceProxy) dataSource).getCurrenTransactionName();
        }
        return super.getDataSourceName();
    }

    public void setSqlFileRepository(SqlFileRepository sqlFileRepository ) {
        this.sqlFileRepository = sqlFileRepository;
    }

    @Override
    public SqlFileRepository getSqlFileRepository() {
        return sqlFileRepository;
    }

    public void setJdbcLogger(JdbcLogger jdbcLogger) {
        this.jdbcLogger = jdbcLogger;
    }

    @Override
    public JdbcLogger getJdbcLogger() {
        return jdbcLogger;
    }

    public void setRequiresNewController(RequiresNewController requiresNewController) {
        this.requiresNewController = requiresNewController;
    }

    @Override
    public RequiresNewController getRequiresNewController() {
        return requiresNewController;
    }

}
