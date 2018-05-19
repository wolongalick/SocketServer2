package proxyserver;

import java.io.InputStream;
import java.io.OutputStream;

public class Server2ClientThread extends Thread {
    private InputStream sis;
    private OutputStream cos;
    private HttpProxyMainThread.INetInfoCallback iNetInfoCallback;
    public Server2ClientThread(InputStream sis, OutputStream cos,HttpProxyMainThread.INetInfoCallback iNetInfoCallback) {
        this.sis = sis;
        this.cos = cos;
        this.iNetInfoCallback=iNetInfoCallback;
    }

    public void run() {
        int length;
        byte bytes[] = new byte[1024*4];
        while (true) {
            try {
                if ((length = sis.read(bytes)) > 0) {
                    cos.write(bytes, 0, length);//将目标主机响应的数据返回到客户端
                    cos.flush();
                } else if (length < 0)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
                iNetInfoCallback.onGetNewNetInfo(new NetInfo("从服务端读数据,向客户端写数据时失败,原因:"+e.getMessage(),true));
            }
        }
    }
}