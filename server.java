package JavaClient;

import java.net.*;
import java.io.*;


//This class produces a socket and sends it to the client
//while it waits for the client to confirm the 
//connection
public class server {

	public static void main(String[] args) throws IOException {
		ServerSocket ab= new ServerSocket(8000);
	       System.out.println("Waiting for connection...");
	     //server will be waiting for the connection using this accept method
	       Socket s=ab.accept(); 
	       System.out.println("Connection Established");
	     //reads the input and sets it equal to the stream
	       DataInputStream input=new DataInputStream(s.getInputStream());
	     //writes the string given from the user
	       String s1=(String)input.readUTF();
	     //response will be displayed
	       System.out.println(s1);
	       input.close();

	}

}