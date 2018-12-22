using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading.Tasks;

namespace Wellcare.PCS.ClientPortal
{
    public partial class FormWaiting : Form
    {
        /// <summary>
        /// 要执行的任务
        /// </summary>
        public Task WaitingTask { get; set; }

        /// <summary>
        /// 提示文本
        /// </summary>
        public string WaitingText
        {
            set
            {
                this.label1.Text = value;
            }
        }

        /// <summary>
        /// 是否显示进度
        /// </summary>
        public bool ShowProcess
        {
            get;
            set;
        }

        /// <summary>
        /// 执行进度值。最大值100，最小值0
        /// </summary>
        private int processValue;
        public void SetProcess(int process)
        {
            processValue = (process >= 0 ? process : 0);
        }

        public FormWaiting()
        {
            InitializeComponent();
            this.panel2.Visible = false;
        }

        private void FormWaiting_Load(object sender, EventArgs e)
        {
            this.panel2.Visible = ShowProcess;
            this.panel1.Visible = !ShowProcess;
        }

        private void FormWaiting_Shown(object sender, EventArgs e)
        {
            WaitingTask.ContinueWith((t) => { this.DialogResult = DialogResult.OK; }
                , System.Threading.CancellationToken.None, TaskContinuationOptions.OnlyOnRanToCompletion, TaskScheduler.FromCurrentSynchronizationContext());

            WaitingTask.ContinueWith((t) => { this.DialogResult = DialogResult.Cancel; }
                , System.Threading.CancellationToken.None, TaskContinuationOptions.OnlyOnFaulted, TaskScheduler.FromCurrentSynchronizationContext());
            WaitingTask.Start();
        }

        int count = 0;
        private void timer1_Tick(object sender, EventArgs e)
        {
            if (!ShowProcess)
            {
                if (count == 4)
                {
                    count = 0;
                }
                switch (count % 4)
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
                count++;
            }
            else
            {
                this.progressBar1.Value = processValue;
                this.label3.Text = processValue + "%";
            }
        }
    }
}
