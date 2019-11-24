using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Server
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }


        private void TcpServerRun()
        {
            TcpListener tcpListener = new TcpListener(IPAddress.Any, 5004);
            tcpListener.Start();
            updateUI("Listening");

            while (true)
            {//es un loop para que se puedan hacer varias conexiones, cuando una conexion termina empieza a buscar otra
                TcpClient client = tcpListener.AcceptTcpClient();
                Thread tcpHandlerThread = new Thread(new ParameterizedThreadStart(tcpHandler));
                updateUI("Connected!");
                tcpHandlerThread.Start(client);
            }

        }

        private void tcpHandler(object client)
        {
            TcpClient mCLient = (TcpClient)client;
            NetworkStream stream;
            string messageString="";
            byte[] message;

            while (true)
            {
                message = new byte[1024];

                stream = mCLient.GetStream();
                stream.Read(message, 0, message.Length);

                messageString = Encoding.ASCII.GetString(message);
                updateUI("New Message => " + messageString);


                if (messageString.Length == 4)
                {
                    break;

                }
            }

            updateUI("Conexion Finalizada");
            stream.Close();
            mCLient.Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            Thread tcpServerRunThread = new Thread(new ThreadStart(TcpServerRun));

            tcpServerRunThread.Start();
        }

               
        private void updateUI(string s)
        {
            Func<int> del = delegate ()
            {
                textBox1.AppendText(s + System.Environment.NewLine);
                return 0;
            };
            Invoke(del);

        }
    }
}
