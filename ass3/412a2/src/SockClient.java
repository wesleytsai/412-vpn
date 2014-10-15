import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SockClient {

    private BufferedReader in;
    private PrintWriter out;
    
    private String serverAddress;
    private int serverPort;

    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Enter in the
     * listener sends the textfield contents to the server.
     */
    public SockClient() {
    	
    	
    }

    /**
     * Implements the connection logic by prompting the end user for
     * the server's IP address, connecting, setting up streams, and
     * consuming the welcome messages from the server.  The Capitalizer
     * protocol says that the server sends three lines of text to the
     * client immediately after establishing a connection.
     */
    public void connectToServer() throws IOException {

        // Make connection and initialize streams
        Socket socket = new Socket(serverAddress, serverPort);
        System.out.println("Socket number" + serverPort);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public void sendMessage(String message) {
		out.println(message);
	}
	
	public String receiveMessage() throws IOException {
        String return_message = " ";
		while(true) {
            String input = in.readLine();
            //TODO: add case for breaking server listening loop here. 
            if (input == null || input.equals(".")) {
                break;
            }
            System.out.println(input); //prints out
            return_message += input; 
        }
        
        return return_message;
	}
    
}