
import java.io.*;
import java.net.*;

public class UppercaseServer {
   
    private static boolean cflag = true;   
    private static int SERVER_PORT = 300; // port number 
    
    
    
    public static void main(String[] args) throws IOException {
        try (ServerSocket sSocket = new ServerSocket(SERVER_PORT)) {  // open a server socket on port 300
            System.out.println("Server is listening on port " + SERVER_PORT);

            
            
            while (cflag) {  //accepting connections while cflag is true
                try (Socket cSocket = sSocket.accept(); // Wait for a client connection
                     PrintWriter out = new PrintWriter(cSocket.getOutputStream(), true); //  send data to the client
                     BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()))) { //receive data from the client
	
                	
                    String userInput; 
                    while ((userInput = in.readLine()) != null) {    // Read from the input stream until the client closes the connection                 
                        if (userInput.equalsIgnoreCase("exit")) {    // if the user has typed the exit regardless of case
                            System.out.println("Server is shutting down...");  // If so, print a shutdown message
                            cflag = false; 
                            sSocket.close();                        // close the server socket releasing the port
                            return; 
                        }
                           
                                                                                
                        System.out.println("Received from client: " + userInput);//returns text typed in the client to the server
                        out.println(userInput.toUpperCase());        // Send the typed string back to the client in uppercase
                    }
                } catch (IOException e) {
                    if (!cflag) {
                        System.out.println("Server has shut down successfully.");
                    } else {
                        System.out.println("server shut down failed on port " + SERVER_PORT);
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
