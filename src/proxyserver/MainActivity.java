package proxyserver;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainActivity {

    private ServerSocket serverSocket;

    public void launch() throws IOException {
        System.out.println("服务启动成功");
        serverSocket = new ServerSocket(4100);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("已连接的客户端ip:" + socket.getInetAddress().getHostAddress());
            new HttpProxyMainThread(socket, new HttpProxyMainThread.INetInfoCallback() {
                public void onGetNewNetInfo(NetInfo netInfo) {
                    System.out.println("信息:" + netInfo.getContent());
                }
            }).start();
        }
    }


}
