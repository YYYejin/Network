import java.net.*;
import java.io.*;
import java.util.*;

public class CalculatorClient {
	
public static void main(String[] args) throws IOException {
	
	PrintWriter out = null;
	BufferedReader in = null;
	Socket socket = null;
	CalculatorClient client = new CalculatorClient();
	Scanner sc = new Scanner(System.in);
	String file = "server_info.dat";
	String ip = null;
	String port = null;
	int portnum = 0;
	
	//read File
	try {
		BufferedReader fileIn = new BufferedReader(new FileReader (file));
		ip = fileIn.readLine();
		port = fileIn.readLine();
		portnum = Integer.parseInt(port);
		fileIn.close();
		System.out.println("Success");
	}
	
	catch (FileNotFoundException e) {
		//If file is not exist, default ip = "localhost" and port number = 9090
		System.out.println(file + " is not exist.");
		ip = "localhost";
		portnum = 9090;
	}
	
	//connect server
	try {
		socket = new Socket(ip, portnum);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		}
	catch(IOException e)
	{
		System.out.println("Disconnected");
	}
		
		
	String input;
	String response;
	float result;
	
	System.out.println("input format : [operator] [first value] [second value]");
	System.out.println("operator : add, sub, mul, div");
	
	while(true)
	{
		input = sc.nextLine(); //Read operator, first num, second num 
		out.println(input);
		response = in.readLine(); //Read response
				
		if(response.equalsIgnoreCase("Q")) //When client wants quit.
			break;
		else if(response.equalsIgnoreCase("S")) { //When response is success
			result = Float.valueOf(in.readLine()).floatValue();
			System.out.println("= " + result);
		}
		
		else //When response is error
		{
			String response1 = in.readLine(); //Error type
			String errorname = null;
			switch(response1)
			{
			case "F":
				errorname = "Format error! Please enter [operator] [first value] [second value] format.";
				break;
			case "N":
				errorname = "Number error! Please enter first value and second value using numbers!";
				break;
			case "O":
				errorname = "Operator error! Operator is add, sub, mul div!";
				break;
			case "D":
				errorname = "Divided by 0 error! You must another number!";
				break;
			}
			
			System.out.println("Error! " + errorname);
		}
	}
	
	in.close();
	out.flush();
	socket.close();
	
	}


}