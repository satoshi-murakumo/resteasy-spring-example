package example.infrastructure.db.domasupport;

import java.sql.SQLException;

import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.SqlExecutionSkipCause;
import org.seasar.doma.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataAccessLogger implements JdbcLogger {

    private Logger logger = LoggerFactory.getLogger(DataAccessLogger.class);

    @Override
    public void logDaoMethodEntering(String callerClassName, String callerMethodName, Object... args) {
        logger.debug("ENTER {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logDaoMethodExiting(String callerClassName, String callerMethodName, Object result) {
        logger.debug("RETURN {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logDaoMethodThrowing(String callerClassName, String callerMethodName, RuntimeException e) {
        logger.debug("THROW {}#{} - {}", callerClassName, callerMethodName, e.toString());
    }

    @Override
    public void logSqlExecutionSkipping(String callerClassName, String callerMethodName, SqlExecutionSkipCause cause) {
        logger.debug("SKIP SQL {}#{} - {}", callerClassName, callerMethodName, cause.name());
    }

    @Override
    public void logSql(String callerClassName, String callerMethodName, Sql<?> sql) {
        logger.debug("SQL {}#{} - {}", callerClassName, callerMethodName,
                Message.DOMA2076.getMessage(sql.getSqlFilePath(), sql.getFormattedSql()));

    }

    @Override
    public void logLocalTransactionBegun(String callerClassName, String callerMethodName, String transactionId) {
        logger.debug(Message.DOMA2063.getMessage(transactionId) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionEnded(String callerClassName, String callerMethodName, String transactionId) {
        logger.debug(Message.DOMA2064.getMessage(transactionId) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionCommitted(String callerClassName, String callerMethodName, String transactionId) {
        logger.debug(Message.DOMA2067.getMessage(transactionId) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionSavepointCreated(String callerClassName, String callerMethodName,
            String transactionId, String savepointName) {
        logger.debug(Message.DOMA2065.getMessage(transactionId, savepointName) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionSavepointReleased(String callerClassName, String callerMethodName,
            String transactionId, String savepointName) {
        logger.debug(Message.DOMA2066.getMessage(transactionId, savepointName) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionRolledback(String callerClassName, String callerMethodName, String transactionId) {
        logger.debug(Message.DOMA2068.getMessage(transactionId) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionSavepointRolledback(String callerClassName, String callerMethodName,
            String transactionId, String savepointName) {
        logger.debug(Message.DOMA2069.getMessage(transactionId, savepointName) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logLocalTransactionRollbackFailure(String callerClassName, String callerMethodName,
            String transactionId, SQLException e) {
        logger.debug(Message.DOMA2070.getMessage(transactionId) + " {}#{}", callerClassName, callerMethodName);
    }

    @Override
    public void logAutoCommitEnablingFailure(String callerClassName, String callerMethodName, SQLException e) {
        logger.debug(Message.DOMA2071.getMessage() + " {}#{} - {}", callerClassName, callerMethodName, e);
    }

    @Override
    public void logTransactionIsolationSettingFailuer(String callerClassName, String callerMethodName,
            int transactionIsolationLevel, SQLException e) {
        logger.debug(Message.DOMA2072.getMessage(transactionIsolationLevel) + " {}#{} - {}", callerClassName, callerMethodName, e);
    }

    @Override
    public void logConnectionClosingFailure(String callerClassName, String callerMethodName, SQLException e) {
        logger.debug(Message.DOMA2073.getMessage() + " {}#{} - {}", callerClassName, callerMethodName, e);
    }

    @Override
    public void logStatementClosingFailure(String callerClassName, String callerMethodName, SQLException e) {
        logger.debug(Message.DOMA2074.getMessage() + " {}#{} - {}", callerClassName, callerMethodName, e);
    }

    @Override
    public void logResultSetClosingFailure(String callerClassName, String callerMethodName, SQLException e) {
        logger.debug(Message.DOMA2075.getMessage() + " {}#{} - {}", callerClassName, callerMethodName, e);
    }

}
