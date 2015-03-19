package mylog4jdbc.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;

import net.sf.log4jdbc.ConnectionSpy;

public class PooledLoggingConnection implements PooledConnection {

	protected PooledConnection parent;

	public PooledLoggingConnection( PooledConnection pConnection ) {
		parent = pConnection;
	}

	public void addConnectionEventListener( ConnectionEventListener pListener ) {
		parent.addConnectionEventListener(pListener);
	}

	public void close() throws SQLException {
		parent.close();
	}

	public Connection getConnection() throws SQLException {
		return new ConnectionSpy(parent.getConnection()); // log4jdbc entry point
	}

	@Override
	public void addStatementEventListener(StatementEventListener arg0) {
	}

	@Override
	public void removeConnectionEventListener(ConnectionEventListener pListener) {
		parent.removeConnectionEventListener(pListener);
	}

	@Override
	public void removeStatementEventListener(StatementEventListener arg0) {
	}
}
