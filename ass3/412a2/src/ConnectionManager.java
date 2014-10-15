public class ConnectionManager {
	public enum ConnectionStatus {
		Disconnected, MaliciousConnection, Authenticated
	}

	public enum ConnectionType {
		Server, Client
	}

	private ConnectionType mode;
	private ConnectionStatus status;
	private int ip;
	private int port;
	private SockClient client;
	private SockServer server;
	private boolean hasNewMessage;

	private byte[] recievedMessage;
	
	public ConnectionManager() { 
	}

	public void setMode(ConnectionType type) {

	}

	public void sendMessage(byte[] data) {
	}

	public boolean hasMessage() {
		return hasNewMessage;
	}

	public byte[] getMessage() {
		return null;
	}

	public ConnectionStatus getConnectionStatus() {
		return ConnectionStatus.Disconnected;
	}

}
