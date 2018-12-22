using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;
using Wellcare.PCS.ClientPortal.Models;

namespace Wellcare.PCS.ClientPortal
{
    public class ClientPortal
    {
        public string Consume(decimal balance)
        {
            var form = new FormConsume();
            form.ConsumeBanlance = balance;
            form.ShowDialog();
            return Utils.Utils.JsonSerialize(form.Result);
        }
    }
}
