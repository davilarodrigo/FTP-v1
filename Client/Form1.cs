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

namespace Client
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnConnect_Click(object sender, EventArgs e)
        {
            Thread mThread = new Thread(new ThreadStart(ConnectAsClient));
            mThread.Start();
        }
    private void ConnectAsClient()
    {
        TcpClient client = new TcpClient();
        client.Connect(IPAddress.Parse("192.168.1.5"), 5004);

            updateUI("Connected!");

        NetworkStream stream = client.GetStream();
        string s = "hola";
        byte[] message = Encoding.ASCII.GetBytes(s);
        stream.Write(message, 0, message.Length);
        updateUI("Message Sent!");       
        stream.Close();
        client.Close();
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
