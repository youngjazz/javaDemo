package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description:
 *
 * @author 张洋
 * @date: 2019-06-12 18:58
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            //启动一个服务
            serverSocket = new ServerSocket(8888);

            while (true) {
                //等待接受一个请求
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        //读取数据
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //发送数据
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                        while (true) {
                            //读取客户端发送过来的消息
                            String clientData = reader.readLine();
                            if (clientData == null) {
                                break;
                            }
                            System.out.println("服务端接受到的数据：" + clientData);

                            writer.println("Hello Leon; ^^");
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
