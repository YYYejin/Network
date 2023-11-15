import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;
public class DataClient {
public static void main(String[] args) throws IOException {
var socket = new Socket("127.0.0.1", 59090);
var in = new Scanner(socket.getInputStream());
System.out.println("Server response: " + in.nextLine());
}
}