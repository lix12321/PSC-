using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Wellcare.PCS.ClientPortal.Utils
{
    public static class TaskExtension
    {
        /// <summary>
        /// 异步执行任务并弹出等待窗口保持响应，显示进度
        /// </summary>
        /// <typeparam name="Result"></typeparam>
        /// <param name="task"></param>
        /// <param name="ProcessNotify">进度通知委托</param>
        /// <returns></returns>
        public static Result StartAndShowNotice<Result>(this Task<Result> task, ref Action<int> ProcessNotify)
        {
            Result ret = default(Result);
            var waitform = new FormWaiting() { WaitingTask = task };
            ProcessNotify += waitform.SetProcess;
            waitform.ShowProcess = true;
            if (waitform.ShowDialog() == DialogResult.OK)
            {
                ret = task.Result;
            }
            ProcessNotify -= waitform.SetProcess;
            return ret;
        }

        /// <summary>
        /// 异步执行任务并弹出等待窗口保持响应，不显示进度
        /// </summary>
        /// <typeparam name="Result"></typeparam>
        /// <param name="task"></param>
        /// <returns></returns>
        public static Result StartAndShowNotice<Result>(this Task<Result> task)
        {
            Result ret = default(Result);
            var waitform = new FormWaiting() { WaitingTask = task };
            if (waitform.ShowDialog() == DialogResult.OK)
            {
                ret = task.Result;
            }
            return ret;
        }
    }
}
