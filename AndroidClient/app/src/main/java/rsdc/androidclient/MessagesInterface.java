package rsdc.androidclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MessagesInterface extends AppCompatActivity {

    ListView listMessages;
    TCPClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnConnect = findViewById(R.id.btnConnect);
        Button btnSend = findViewById(R.id.btnSend);

        final EditText txtPort = findViewById(R.id.txtPort);
        final EditText txtIp = findViewById(R.id.txtIp);
        final EditText txtMessage = findViewById(R.id.txtMessage);

        tcpClient=new TCPClient(this);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String ip = txtIp.getText().toString();
                int port = Integer.parseInt(txtPort.getText().toString());

                tcpClient.connect(ip,port);


            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = txtMessage.getText().toString();
                txtMessage.setText("");
                tcpClient.send(message);
            }
        });

    }

    public void toast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
    }

    public void addMessage(String message){
      //listMessages.
    }
}
