package JavaClient;

import java.net.*;
import java.io.*;

//This class reaches out to the client from the server and checks
//to see if a connection has been established with the server
public class Clients {

	public static void main(String[] args) throws IOException {
		 //Socket a is the connection that gets connected with the server
		   Socket a=new Socket("localhost",8000);
		 //prints out that a connection was successful 
	       System.out.println("Connection Established");
	     //reads socket a's output and writes it into 'output'
	       DataOutputStream output=new DataOutputStream(a.getOutputStream());
	     //sample string
	       output.writeUTF("Hello World"); 
	       output.close();
	}

}