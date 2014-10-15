import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SockServer {
	private String clientAddress;
	private int clientPort;
	ServerSocket listener;
	String returnMessage;
	private ReceiveMessage recmes;
	
    public SockServer() throws Exception {
        System.out.println("The server is running.");
        listener = new ServerSocket(clientPort);
        recmes = new ReceiveMessage();
    }

    public String receiveMessage() throws IOException {
        try {
        	this.recmes.done = false;
            while (!this.recmes.done) {
                new MessageHandler(listener.accept(), recmes).start();
            }
            return recmes.getReceived_message();
        } finally {
            listener.close();
        }
    }
    
    public void sendMessage(String message) throws IOException {
    	PrintWriter out = new PrintWriter(listener.accept().getOutputStream(), true);
    	out.println("message");
    }
    
    public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	/**
     * A private thread to handle capitalization requests on a particular
     * socket.  The client terminates the dialogue by sending a single line
     * containing only a period.
     */
    private static class MessageHandler extends Thread {
        private Socket socket;
        private int clientNumber;
        private String fullmes = "";
        private ReceiveMessage rec;

        public MessageHandler(Socket socket, ReceiveMessage rec) {
            this.socket = socket;
            log("New connection with client" + socket);
        }

        /**
         * Services this thread's client by first sending the
         * client a welcome message then repeatedly reading strings
         * and sending back the capitalized version of the string.
         */
        public void run() {
            try {

                // Decorate the streams so we can send characters
                // and not just bytes.  Ensure output is flushed
                // after every newline.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                // Send a welcome message to the client.
              out.println("Please send a message");
              this.rec.done = false;
                // Get messages from the client, line by line; return them
                while (true) {
                    String input = in.readLine();
                    //TODO: add case for breaking server listening loop here. 
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    System.out.println(input); //prints out
                    this.fullmes += input;
                }
                
                this.rec.setReceived_message(fullmes);
                this.rec.done = true;
                
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        /**
         * Logs a simple message.  In this case we just write the
         * message to the server applications standard output.
         */
        private void log(String message) {
            System.out.println(message);
        }
    }
    
    private static class ReceiveMessage {
    	private String received_message;
    	public boolean done; //flag for exiting loop
    	public ReceiveMessage() {
    		received_message = "";
    	}
		public String getReceived_message() {
			return received_message;
		}

		public void setReceived_message(String received_message) {
			this.received_message = received_message;
		}
    }
}