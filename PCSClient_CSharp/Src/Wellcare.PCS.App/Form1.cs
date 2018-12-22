using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Wellcare.PCS.ClientPortal;

namespace Wellcare.PCS.App
{
    public partial class Form1 : Form
    {
        ClientPortal.ClientPortal portal = new ClientPortal.ClientPortal();

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            decimal banlance;
            if (!string.IsNullOrEmpty(this.textBox1.Text) && decimal.TryParse(this.textBox1.Text, out banlance))
            {
                var str = portal.Consume(banlance);
            }
        }
    }
}
