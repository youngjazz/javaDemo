package socket.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author 张洋
 * @date: 2019-06-12 20:02
 */
public class MulticastServer {
    public static void main(String[] args) {
        //地址段 224.0.0.0 ~ 239.255.255.255
        try {
            InetAddress group = InetAddress.getByName("224.5.6.7");
            MulticastSocket socket = new MulticastSocket();
            for (int i = 0; i < 10; i++) {
                String data = "hello mic";
                byte[] bytes = data.getBytes();
                socket.send(new DatagramPacket(bytes, bytes.length, group, 8888));
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
