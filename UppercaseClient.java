
import java.io.*;
import java.net.*;

public class UppercaseClient {
	
  
    private static final String SERVER_ID = "localhost";  // Server IP or hostname to connect to
    private static final int SERVER_PORT = 300;  // Port number 
    
    public static void main(String[] args) throws IOException {
      
       
        try (Socket socket = new Socket(SERVER_ID, SERVER_PORT);   // makes a connection to the server at SERVER_ID and SERVER_PORT
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);  // output streams for the connection, 
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //input streams for  connection
             BufferedReader bfreader = new BufferedReader(new InputStreamReader(System.in))) {//BufferedReader for user input

          
            System.out.println("Enter either text to send to the server (type 'EXIT' or 'exit' to quit):");
            String userInput;
            
  
            while (true) {              
                userInput = bfreader.readLine();  // read user input and send it to the server
                
                
                if (userInput.equalsIgnoreCase("exit")) { // if the user has typed the exit regardless of case
                    
                    output.println(userInput);  // Send exit string to the server indicating closing the connection
                    System.out.println("Exiting client...");
                    break; 
                } else {
                    
                    output.println(userInput);   // else if its not exit, send the string  to the server
                    String response = input.readLine();
                    System.out.println("Response from server: " + response);
                }
            }
        // 
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + SERVER_ID);
        }
    }
}
