package echoserver;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;

public class EchoClient {
	public static void main(String[] args) {
		String host;
		
		if (args.length > 0) {
			host = args[0];
		} else {
			host = "127.0.0.1";
		}
		
		try {
			Socket socket = new Socket(host, 6013);
			
			InputStream ourInput = System.in;
			OutputStream ourOutput = System.out;
			OutputStream serverInput = socket.getOutputStream();
			InputStream serverOutput = socket.getInputStream();
			
			byte[] byteStream = new byte[1];
			int readLength = 0;
			
			while (true) {
				readLength = ourInput.read(byteStream);
				
				if (readLength == -1) {
					break;
				}
				
				serverInput.write(byteStream, 0, readLength);
				serverInput.flush();
				
				readLength = serverOutput.read(byteStream);
				ourOutput.write(byteStream, 0, readLength);
				ourOutput.flush();
			}
			
			ourOutput.flush();
			socket.close();
		} catch (IOException ioe) {
			System.out.println("We caught an exception");
			System.err.println(ioe);
		}
	}
}