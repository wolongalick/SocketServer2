package proxyserver;

import java.io.InputStream;
import java.io.OutputStream;

public class Client2ServerThread extends Thread {
    private InputStream cis;
    private OutputStream sos;
    private HttpProxyMainThread.INetInfoCallback iNetInfoCallback;

    public Client2ServerThread(InputStream cis, OutputStream sos,HttpProxyMainThread.INetInfoCallback iNetInfoCallback) {
        this.cis = cis;
        this.sos = sos;
        this.iNetInfoCallback=iNetInfoCallback;
    }

    /**
     * run()方法中的bytes内容形如:
     GET http://10.129.32.56:8080/api/v2/version/getLatestVersion HTTP/1.1
     User-Agent: Dalvik/2.1.0 (Linux; U; Android 8.0.0; MHA-AL00 Build/HUAWEIMHA-AL00)
     Host: 10.129.32.56:8080
     Connection: Keep-Alive
     Accept-Encoding: gzip
     */

    public void run() {
        int length;
        byte bytes[] = new byte[1024*4];
        while (true) {
            try {
                if ((length = cis.read(bytes)) > 0) {
                    sos.write(bytes, 0, length);//将http请求头写到目标主机
                    sos.flush();
                } else if (length < 0)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
                iNetInfoCallback.onGetNewNetInfo(new NetInfo("从客户端读数据,向服务端写数据时失败,原因:"+e.getMessage(),true));
            }
        }
    }
}