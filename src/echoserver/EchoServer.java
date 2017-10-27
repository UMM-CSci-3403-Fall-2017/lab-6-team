
package echoserver;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket sock = new ServerSocket(6013);
			
			while (true) {
				Socket client = sock.accept();
				System.out.println("Got a request!");
				
				InputStream ourInput = client.getInputStream();
				OutputStream ourOutput = client.getOutputStream();
				
				byte[] byteStream = new byte[1024];
				int readLength = ourInput.read(byteStream);
				
				while (readLength != -1) {
					ourOutput.write(byteStream, 0, readLength);
					readLength = ourInput.read(byteStream);
					ourOutput.flush();
				}
				
				client.close();
			}
		} catch (IOException ioe) {
			
			System.err.println(ioe);
		}
	}
}