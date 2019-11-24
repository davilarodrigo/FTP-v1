package rsdc.androidclient;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient extends AsyncTask<Void,Void,Void> {

    String dstAddress;
    int dstPort;
    String response="";
    MessagesInterface msg;
    ByteArrayOutputStream byteArrayOutputStream;

    public TCPClient(String addr, int port, MessagesInterface messagesInterface){
        dstAddress=addr;
        dstPort=port;
        msg=messagesInterface;

        ///

    }

    @Override
    protected Void doInBackground(Void... arg0){
        Socket socket = null;

        try {
            socket=new Socket(dstAddress,dstPort);

            byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer,0,bytesRead);

            }

            //codigo para el input... completar mas tarde
        }catch (UnknownHostException e){
            msg.toast("UnknownHostException"+e.toString());
        }catch (IOException e){
            msg.toast("IOException"+e.toString());
        }finally {
            if (socket!=null){
                try{
                    socket.close();
                }catch (IOException e){
                    msg.toast("???");
                }
            }
        }
    return null;}

    public void send(String str){


    }

    //////////////////////////////////////
/*
    private String sentence;
    private String Modifiedsentence;

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private DataOutputStream outputStream;

    private Socket clientSocket;
    private MessagesInterface msgUI;

    public void connect(String _ip, int _port)  {

    }

    public void send (String message) {

    }

*/
}
