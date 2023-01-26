import java.io.*;
import java.net.*;
import java.util.*;

class Server {
	ServerSocket server = null;
	Socket client = null;

	public static void main(String[] arg) {
		Server s = new Server();
		s.doConnections();
	}

	public void doConnections() {

		try {
			server = new ServerSocket(8888);
			while (true) {
				client = server.accept();
				ClientThread ct = new ClientThread(client);
				ct.start();
			}
		} catch (Exception e) {
			System.out.println("Could not start the server!");
		}
	}
}

class ClientThread extends Thread {
	public Socket client = null;
	public DataInputStream dis = null;
	public DataOutputStream dos = null;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public BufferedReader br = null;
	public String inputFromUser = "";
	public File file = null;

	public ClientThread(Socket c) {
		try {
			client = c;
			dis = new DataInputStream(c.getInputStream());
			dos = new DataOutputStream(c.getOutputStream());

		} catch (Exception e) {
			System.out.println("Could not connect to client!");
		}
	}

	public void run() {
		while (true) {
			try {
				String input = dis.readUTF();
				String filename = "", filedata = "";
				System.out.println("\nServer Started!.........");
				byte[] data;
				if (input.equals("FILE_SEND_FROM_CLIENT")) {
					System.out.println("\nClient is sending the file!........");
					filename = dis.readUTF();
					filedata = dis.readUTF();
					fos = new FileOutputStream(filename);
					fos.write(filedata.getBytes());
					fos.close();
					System.out.println("\nFile received from client!");
				} else if (input.equals("DOWNLOAD_FILE")) {
					filename = dis.readUTF();
					file = new File(filename);
					System.out.println("\nSending file to client!.........");
					if (file.isFile()) {
						fis = new FileInputStream(file);
						data = new byte[fis.available()];
						fis.read(data);
						filedata = new String(data);
						fis.close();
						dos.writeUTF(filedata);
						System.out.println("\nFile sent to client!");
					} else {
						dos.writeUTF(""); // NO FILE FOUND
						System.out.println("\nFile not found!");
					}
				} else {
					System.out.println("\nError at Server");
				}
			} catch (Exception e) {
				System.out.println("\nServer stopped!");
			}
		}
	}
}