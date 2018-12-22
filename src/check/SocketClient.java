package check;

 
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
 
public class SocketClient {
    private String address = "127.0.0.1";
    private int port = 8765;
 
    public SocketClient() {
 
        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        try {
            client.connect(isa, 10000);
            BufferedOutputStream out = new BufferedOutputStream(client
                    .getOutputStream());

            out.write("Send From Client ".getBytes());
            out.flush();
            out.close();
            out = null;
            
            
            BufferedInputStream in = new java.io.BufferedInputStream(client.getInputStream());
            byte[] b = new byte[1024];
            String data = "";
            int length;
            while ((length = in.read(b)) > 0)
            {
                data += new String(b, 0, length);
            }
            
            System.out.println("value:" + data);
            
            
            client.close();
            client = null;
 
        } catch (java.io.IOException e) {
            System.out.println("Socket連線有問題 !");
            System.out.println("IOException :" + e.toString());
        }
    }
 
    public static void main(String args[]) {
        new SocketClient();
    }
}