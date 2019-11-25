package rsdc.androidclient;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    private String ip="192.168.1.5";
    private int port=5004;
    private MessagesInterface messagesInterface;
    private Socket socket;

    private PrintWriter out;

    public TCPClient( MessagesInterface messagesInterface){
        this.messagesInterface=messagesInterface;
    }

    public void connect(String ip, int port){
        this.ip=ip;
        this.port=port;
        new Thread(new ClientThread()).start();
    }


    public void send(String message){

        out.println(message);
        out.flush();

    }

    class ClientThread implements Runnable{
        @Override
        public void run(){
            try {
                socket=new Socket(ip,port);

                try {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

                }catch (IOException e){messagesInterface.toast("IOException (TCPClient) " + e.toString());}

            }catch (IOException e){messagesInterface.toast("IOException (ClientThread) " + e.toString());}

        }
    }
}
