import java.io.*;
import java.net.*;
import java.util.*;

public class CalculatorServer {
	
	private final static int portnum = 9090;
	
	public void connect(int port) throws IOException
	{
		ServerSocket listener = new ServerSocket(port);
		Socket clientSocket;
		
		while(true)
		{
			
			clientSocket = listener.accept();
				
			BufferedReader in = null;
			PrintWriter out = null;
			String input = null;
			
			try {
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				
				while(true)
				{
					input = in.readLine();
					
					//input is Q, it means client wants to quit connecting
					if(input.equalsIgnoreCase("Q"))
					{
						out.println("Q");
						System.out.println("End!");
						break;
					}
					
					else
					{
						String[] calculate = new String[2];
						float result = 0;
						Calculator cal = new Calculator(input);

						calculate = cal.CalcReturnValue(); 
						
						//Print whether it is error
						out.println(calculate[0]);
						
						//Success
						if(calculate[0].equalsIgnoreCase("S"))
						{
							result = cal.calc();
							System.out.println("Success! : " + input + " is " + result);
							
							out.println(result);
						}
					
						//Error
						else
						{
							System.out.println("Error! You entered " + input);
							out.println(calculate[1]);
						}
					}
				}
			
				clientSocket.close();
			}
			catch (IOException e)
			{
				return;
			}
			
			out.flush();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		CalculatorServer server = new CalculatorServer();
		server.connect(portnum);
	}

}
