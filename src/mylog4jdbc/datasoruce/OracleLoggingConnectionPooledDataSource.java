package mylog4jdbc.datasoruce;

import java.sql.SQLException;

import javax.sql.PooledConnection;

import mylog4jdbc.connection.PooledLoggingConnection;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import org.apache.log4j.PropertyConfigurator;

public class OracleLoggingConnectionPooledDataSource extends OracleConnectionPoolDataSource {

	public OracleLoggingConnectionPooledDataSource() throws SQLException {
		super();
		initLogging();
	}

	protected void initLogging() {
		try {
			PropertyConfigurator.configure( getClass()
											.getClassLoader()
											.getResource("log4jdbc_log4j.properties") );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	public PooledConnection getPooledConnection() throws SQLException {
		return new PooledLoggingConnection(super.getPooledConnection());
	}

	public PooledConnection getPooledConnection(String arg0, String arg1) throws SQLException {
		return new PooledLoggingConnection(super.getPooledConnection(arg0, arg1));
	}

}
