using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Wellcare.PCS.ClientPortal
{
    public partial class FormSweepCode : Form
    {
        private int countWaitingSymbol = 0;
        public string Data
        {
            private set;
            get;
        }

        public FormSweepCode()
        {
            InitializeComponent();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (countWaitingSymbol == 4)
            {
                countWaitingSymbol = 0;
            }
            switch (countWaitingSymbol % 4)
            {
                case 0:
                    this.label2.Text = "";
                    break;
                case 1:
                    this.label2.Text = ".";
                    break;
                case 2:
                    this.label2.Text = "..";
                    break;
                case 3:
                    this.label2.Text = "...";
                    break;
            }
            countWaitingSymbol++;
        }

        //获取扫码信息
        private void FormSweepCode_KeyPress(object sender, KeyPressEventArgs e)
        {
            //扫码结束符
            if (e.KeyChar == '\r')
            {
                this.DialogResult = DialogResult.OK;
            }
            else
            {
                Data += e.KeyChar;
            }
        }
    }
}
