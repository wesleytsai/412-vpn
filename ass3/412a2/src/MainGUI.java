import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

public class MainGUI {

	/**
	 * Managers
	 */
	private ConnectionManager connectionManager;
	private AuthenticationManager rsaManager;

	/**
	 * The UI Components
	 */
	private JFrame frame;
	private JTextField textIP;
	private JTextField textPort;
	private JTextField textSharedkey;
	private JTextField textRecieved;
	private JTextField textSendMessage;
	private JLabel lblConnectionStatus;
	private JComboBox<ConnectionManager.ConnectionType> comboBoxConnectionMode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		connectionManager = new ConnectionManager();
		rsaManager = new AuthenticationManager();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * WT NOTE: Please don't change this directly. Use the Window Builder
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAssignmentTitle = new JLabel("EECE 412 Assignment 2 - Group 19");
		lblAssignmentTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAssignmentTitle.setBounds(32, 11, 231, 33);
		frame.getContentPane().add(lblAssignmentTitle);
		
		comboBoxConnectionMode = new JComboBox<ConnectionManager.ConnectionType>();
		comboBoxConnectionMode.setModel(new DefaultComboBoxModel<ConnectionManager.ConnectionType>(ConnectionManager.ConnectionType.values()));
		comboBoxConnectionMode.setBounds(32, 55, 109, 33);
		frame.getContentPane().add(comboBoxConnectionMode);
		
		JButton btnConnectionStart = new JButton("Start");
		btnConnectionStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onConnectionStartButtonClicked(e);
			}
		});
		btnConnectionStart.setBounds(546, 55, 103, 33);
		frame.getContentPane().add(btnConnectionStart);
		
		textIP = new JTextField();
		textIP.setBounds(151, 55, 259, 33);
		frame.getContentPane().add(textIP);
		textIP.setColumns(10);
		
		textPort = new JTextField();
		textPort.setBounds(420, 55, 116, 33);
		frame.getContentPane().add(textPort);
		textPort.setColumns(10);
		
		lblConnectionStatus = new JLabel("Status: Disconnected");
		lblConnectionStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConnectionStatus.setForeground(Color.RED);
		lblConnectionStatus.setBounds(32, 99, 667, 33);
		frame.getContentPane().add(lblConnectionStatus);
		
		textSharedkey = new JTextField();
		textSharedkey.setBounds(151, 160, 498, 33);
		frame.getContentPane().add(textSharedkey);
		textSharedkey.setColumns(10);
		
		JLabel lblSharedKey = new JLabel("Shared Key:");
		lblSharedKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSharedKey.setBounds(32, 158, 103, 33);
		frame.getContentPane().add(lblSharedKey);
		
		JLabel lblRecieved = new JLabel("Recieved:");
		lblRecieved.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRecieved.setBounds(32, 245, 131, 33);
		frame.getContentPane().add(lblRecieved);
		
		JLabel lblSendMessage = new JLabel("Send Message:");
		lblSendMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSendMessage.setBounds(32, 321, 116, 33);
		frame.getContentPane().add(lblSendMessage);
		
		textRecieved = new JTextField();
		textRecieved.setColumns(10);
		textRecieved.setBounds(151, 247, 498, 33);
		frame.getContentPane().add(textRecieved);
		
		textSendMessage = new JTextField();
		textSendMessage.setColumns(10);
		textSendMessage.setBounds(151, 323, 498, 33);
		frame.getContentPane().add(textSendMessage);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onSendButtonClicked(e);
			}
		});
		btnSend.setBounds(286, 393, 89, 23);
		frame.getContentPane().add(btnSend);
	}
	
	private void onConnectionStartButtonClicked(MouseEvent e) {
		// WT TODO: If the mode is client, start authentication with server at IP:PORT
		// 			If the mode is server, start listening on specified port
		
		switch ((ConnectionManager.ConnectionType)comboBoxConnectionMode.getSelectedItem())
		{
		case Client:
			System.out.println("Client Mode Clicked");
			break;
		case Server:
			System.out.println("Server Mode Clicked");
			break;
		}
	}

	private void onSendButtonClicked(MouseEvent e) {
		// WT TODO: IF connection is open and is authenticated, then send over encrypted message
		if (connectionManager.getConnectionStatus() == ConnectionManager.ConnectionStatus.Authenticated)
		{
			// Do yo thang
		}
		else
		{

		}
	}
}
