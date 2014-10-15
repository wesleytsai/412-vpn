public class ConnectionManager {
	public enum ConnectionStatus {
		Disconnected, MaliciousConnection, Authenticated
	}

	public enum ConnectionType {
		Server, Client
	}

	private ConnectionType mode;
	private ConnectionStatus status;
	private String ip;
	private int port;
	private SockClient client;
	private SockServer server;
	private boolean hasNewMessage;

	private byte[] recievedMessage;
	
	public ConnectionManager() { 
	}

	public void setMode(ConnectionType type) {
		switch (type)
		{
		case Server:
			try {
                server = new SockServer();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
            break;

		case Client:
			try {
                client = new SockClient();
                client.setServerAddress(ip);
                client.setServerPort(port);
                client.connectToServer();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
            break;
		}
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
