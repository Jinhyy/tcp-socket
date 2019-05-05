import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientMain {
    public static void main(String []args) throws IOException {
        final String serverAddr = "localhost";
        final int serverPort = 8080;
        int socketTry=0;
        String commonData = "CommonData";
        String data = String.format("%-110s","REAL DATA");
        String lengthData = "110234";
        byte[] clientData;

        // 1. Socket 객체 생성, 연결
        Socket socket = new Socket(serverAddr,serverPort);
        SocketWorker socketWorker = new SocketWorker(socket);

        while(socketTry<10) {
            // 2. 데이터 설정 및 보내기
            DataModel dataModel = new DataModel(lengthData, socketTry+"'st Data: "+commonData, data);
            clientData = dataModel.toString().getBytes();
            socketWorker.socketWrite(clientData);
//            System.out.println("client write finished.");

            // 3. 응답 받기
            byte[] dataFromServer = new byte[1000];
            socketWorker.socketRead(dataFromServer);
            System.out.println("socket read : " + new String(dataFromServer));

            if(socket.isConnected())
                socketTry++;
        }

        socketWorker.socketClose();
        System.out.println("socket closed : " + new Date());
    }
}
