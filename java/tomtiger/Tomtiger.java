package tomtiger;

/**
 * @author liuleilei liuleilei2015@gmail.com
 * @date 2017��11��20�� ����4:52:55
 * @Description:the new Web Server Tomtiger, haha! 
 */

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.LinkedList;

public class Tomtiger {
    //����htmlҳ��ȵĴ��λ��
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WEB_ROOT";
    //����web������ռ�õĶ˿ں�
    public static final int port = 8080;
    //����һ���б���� Ϊÿ�����ӵ�socket�������̡߳�
    private List<Thread> connectlist = null;

    //main��������������ʼ����
    public static void main(String[] args) throws MalformedURLException {
//    	URL url=new URL("www.baidu.com");
        //webserver ��ʼ����
        Tomtiger server = new Tomtiger();
        server.start();
    }

    public void start() {
        ServerSocket serversocket = null;
        try {
            //server ����127.0.0.1:8080
            serversocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));

            //server �Ѿ�����
            System.out.println("Tomtiger is running!!");

        }catch(IOException e) {
            e.printStackTrace();
        }

        //������¼���������
        int count = 0;

        //���̴߳�ŵ��б�
        connectlist = new LinkedList<Thread>();
        while(true) {
            Socket socket = null;
            try {
                //Ϊ������������
                socket = serversocket.accept();

                System.out.println("����"+ count +"�Խ�������");

                //Ϊ��socket�������̣߳��������������б�
                ConnectionThread connectionthread = new ConnectionThread(socket);
                Thread thread = new Thread(connectionthread);
                thread.start();
                connectlist.add(thread);

                System.out.println("����"+ count++ +"���߳���������������У���");

            }catch(Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
