import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerMain {
    public static void main(String []args) throws IOException {
        byte[] dataFromClient=new byte[1000];
        byte[] serverData = new byte[1000];
        int socketTry=0;

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket connectedSocket = serverSocket.accept();
        SocketWorker socketWorker = new SocketWorker(connectedSocket);
        System.out.println(connectedSocket.getInetAddress() + " connected.");

        while(socketTry<10) {
            socketWorker.socketRead(dataFromClient);
//            System.out.println("server socket read fiished.");
            String dataFromClientString = new String(dataFromClient);
            System.out.println(socketTry + "'st dataFromClient : " + dataFromClientString);
            String lengthData = dataFromClientString.substring(0, 8);
//            System.out.println("client data length : " + Integer.parseInt(lengthData));

            byte[] dataToClient = dataFromClientString.substring(8).getBytes();
            socketWorker.socketWrite(dataToClient);
//            System.out.println("server socket write finished.");

            if (connectedSocket.isConnected())
                socketTry++;
        }
        socketWorker.socketClose();
        System.out.println("socket closed : " + new Date());
    }
}
