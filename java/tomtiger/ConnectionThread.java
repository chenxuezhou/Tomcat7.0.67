package tomtiger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;

public class ConnectionThread implements Runnable{

    Socket socket = null;
    InputStream inputstream = null;
    OutputStream outputstream = null;

    // ����socket��ʼ��socket�Ķ��߳���
    public ConnectionThread(Socket socket) {
        this.socket = socket;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            // ��ø����ӵ����������
            inputstream = socket.getInputStream();
            outputstream = socket.getOutputStream();
            //Ϊ�����ӽ���request��request��inputstream�ж���http�����ģ�ת��Ϊ�ַ���������ȡ���������Դ��
            Request request = new Request(inputstream);
            //��request�л��������Դ��uri
            String uri = request.getUri();

            // Ϊ������response��response���ݴ����outputstream����uri������������Դ�ļ���outputstream֮���ͨ·������Ӧ��Ӧ����Դ������Դ�����ڣ���ӦĬ��ҳ��
            Response response = new Response(outputstream);
            // response��Ӧ�������Դ
            response.responseResource(uri);

        }catch(IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

}