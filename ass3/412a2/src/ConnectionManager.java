import java.io.IOException;

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
	
	public void setMode(ConnectionType type, String ip, int port) {
		this.ip = ip;
		this.port = port;
		
		switch (type)
		{
		case Server:
			try {
                server = new SockServer();
                server.setClientAddress(ip);
			} catch (Exception ex) {
				MainGUI.Log(ex.getMessage());
			}
            break;

		case Client:
			try {
                client = new SockClient();
                client.setServerAddress(ip);
                client.setServerPort(port);
                client.connectToServer();
			} catch (Exception ex) {
				MainGUI.Log(ex.getMessage());
			}
            break;
		}
	}

	public void sendMessage(String data) throws IOException {
		switch(this.mode) {
		case Server:
			server.sendMessage(data);
		case Client:
			client.sendMessage(data);
		}
	}

	public boolean hasMessage() {
		return hasNewMessage;
	}

	public String getMessage() throws IOException {
		switch(this.mode) {
		case Server:
			return server.receiveMessage();
		case Client:
			return client.receiveMessage();
		}
		return ip; //note: will never reach here.
	}

	public ConnectionStatus getConnectionStatus() {
		return ConnectionStatus.Disconnected;
	}

}
