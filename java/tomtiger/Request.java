package tomtiger;

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Request {

    private InputStream inputstream = null;
    private String uri = null;

    // ����inputstream��ʼ��request
    public Request(InputStream inputstream) {
        this.inputstream = inputstream;
        parseUri();
    }

    // ��ȡinputstream������ת��Ϊ�ַ���
    @SuppressWarnings("finally")
    private String requestToString() {
        String requestString = null;
        // �ֽ���ת�ַ���
        BufferedReader bfreader = new BufferedReader(new InputStreamReader(inputstream));
        StringBuffer buffer = new StringBuffer();
        // �����ҿ�ʼ�õĳ��õ�while���ж�ȡ������һֱ�����������⣩��������
        char[] temp = new char[2048];
        int length = 0;
        try {
            length = bfreader.read(temp);
            buffer.append(temp,0,length);   
            requestString = buffer.toString();

        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            // ���request
//            System.out.println("requestΪ:");
//            System.out.println(requestString);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("request="+dateFormat.format(new Date()));
            return requestString;
        }
    }

    // ���������ĵ��ص㣬������ļ��ڵ�һ���͵ڶ����ո�֮�䡣�����������·���
    public void parseUri() {
        String request = requestToString();
        if(request != null) {
            int space1 = -1;
            int space2 = -1;
            space1 = request.indexOf(' ');
            if(space1 != -1) {
                space2 = request.indexOf(' ',space1 + 1);
            }
            if(space2 > space1) {
                // ��ȡ��һ���͵ڶ����ո�֮����ַ�������������Դ��uri
                uri = request.substring(space1 + 1, space2);
            }
        }
    }

    // ����������Դ��uri
    public String getUri() {
        return uri;
    }
}
