using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Zebone.Services;
using Wellcare.PCS.MisPOSPayer.Models;

namespace Wellcare.PCS.MisPOSPayer.Services
{
    public interface IPCSService : IServiceBase
    {
        [TransactionCode(Constant.OutSysName.PCS, "4444444")]
        ServiceResult<string> SavePOSOrder(POSReturn order);
    }
}
