import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWorker {
    // 읽기, 쓰기 구현
    private final Socket socket;
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private int socketTry;

    public SocketWorker(Socket socket) throws IOException {
        this.socket = socket;
        this.socketInputStream = socket.getInputStream();
        this.socketOutputStream = socket.getOutputStream();
        this.socketTry = 10;
    }

    public void socketWrite(byte[] data){
        try {
            socketOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void socketRead(byte[] data){
        try {
            socketInputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void socketClose() throws IOException {
        this.socket.close();
    }

    public int getSocketTry() {
        return socketTry;
    }
}
