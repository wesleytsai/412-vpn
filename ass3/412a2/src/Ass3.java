
public class Ass3 {

	public static void main(String[] args) throws Exception {
		// if !client, application is in server mode.
		boolean client = false;
		// TODO Auto-generated method stub
		System.out.println("Please enter client or server mode");
		if(client) {
			SockClient myClient = new SockClient();
		}
		else {
			SockServer myServer = new SockServer();
			
		}
	}

}
