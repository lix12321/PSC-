using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Zebone.Services;
using Wellcare.PCS.ClientPortal.Models;
using Wellcare.PCS.MisPOSPayer;
using Wellcare.PCS.ClientPortal.CommandPay;

namespace Wellcare.PCS.ClientPortal
{
    public partial class FormConsume : Form
    {
        ICommandPay command;
        public decimal ConsumeBanlance { get; set; }
        internal ServiceResult<ConsumeResult> Result { get; set; }

        public FormConsume()
        {
            InitializeComponent();
            this.gvPayway.AutoGenerateColumns = false;
        }

        private void FormConsume_Load(object sender, EventArgs e)
        {
            this.label2.Text = ConsumeBanlance.ToString("0.00") + "元";
            gvPayway.DataSource = new BindingList<KeyValuePair<string, string>>() 
            {
                new  KeyValuePair<string, string>(Constant.PayWays.Cash,"现金"),
                new  KeyValuePair<string, string>(Constant.PayWays.WeChat,"微信"),
                new  KeyValuePair<string, string>(Constant.PayWays.Ali,"支付宝"),
                new  KeyValuePair<string, string>(Constant.PayWays.BankCard,"银行卡"),
            };
        }

        private void gvPayway_SelectionChanged(object sender, EventArgs e)
        {
            foreach (DataGridViewRow row in gvPayway.Rows)
            {
                row.Cells[0].Value = false;
            }
            if (gvPayway.SelectedRows.Count > 0)
            {
                gvPayway.SelectedRows[0].Cells[0].Value = true;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (gvPayway.SelectedRows.Count < 1)
            {
                return;
            }

            var payway = (KeyValuePair<string, string>)gvPayway.SelectedRows[0].DataBoundItem;
            switch (payway.Key)
            {
                case Constant.PayWays.BankCard:
                case Constant.PayWays.Ali:
                case Constant.PayWays.WeChat:
                    command = new CommandPOSPay(payway.Key);
                    break;
                default:
                    MessageBox.Show("支付方式未实现！", "提示");
                    return;
            }

            Result = command.Consume(ConsumeBanlance);
            if (!Result.Success)
            {
                if (Result.StatusCode != Constant.ErrorCode.Cancel)
                {
                    var boxResult = MessageBox.Show("支付失败，是否重试？" + Environment.NewLine + Result.Message, "提示", MessageBoxButtons.RetryCancel);
                    if (boxResult == DialogResult.Retry)
                    {
                        button1.PerformClick();
                    }
                    else
                    {
                        return;
                    }
                }
                else
                {
                    return;
                }
            }
            else
            {
                this.DialogResult = DialogResult.OK;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Result = new ServiceResult<ConsumeResult>(false, Constant.ErrorCode.Cancel, string.Empty);
            this.DialogResult = DialogResult.Cancel;
        }
    }
}
