package tomtiger;

/**
 * @author liuleilei liuleilei2015@gmail.com
 * @date 2017��11��20�� ����4:37:49
 * @Description: implement the response of connection
 */
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Response {
    // �����ȡ�ļ�ʱbyte[] ����Ĵ�С
    private static final int BUFFER_SIZE = 1024;
    // ������Ӧ��outputstream����������� Ϊ����������socket�л�ã�������
    OutputStream outputstream = null;

    // ��outputstream��ʼ��response
    public Response(OutputStream outputstream) {
        this.outputstream = outputstream;
    }

    // ��������Դ���ļ�·��
    public void responseResource(String uri) {
        // ��ȡ�ļ�ʱ��byte[]
        byte[] resourcetemp = new byte[BUFFER_SIZE];
        // ������
        FileInputStream fileinputstream = null;
        if(uri == null)
            return;
        try {
            // �����ļ�
            File resource = new File(Tomtiger.WEB_ROOT,uri);
            // �ж��ļ��Ƿ����
            if(resource.exists()) {

                System.out.println("�������Դ�ǣ�" + resource.getName());

                fileinputstream = new FileInputStream(resource);
                int length = 0;

                String responsehead = "HTTP/1.1 200 OK\r\n" +  "\r\n";
                outputstream.write(responsehead.getBytes());

                while((length = fileinputstream.read(resourcetemp)) > 0) {
                    outputstream.write(resourcetemp, 0, length);
                }
            }
            else {
//                Thread.sleep(1000*10);
                for (int i = 0; i <1000*1000*100 ; i++) {

                }
                String errorPage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
                outputstream.write(errorPage.getBytes());
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateFormat.format(new Date()));
            }

            outputstream.close();
        }catch(IOException e){
            e.printStackTrace();
        }  finally {
            outputstream = null;
            fileinputstream = null;
        }
    }
}
