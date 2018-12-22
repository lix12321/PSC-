package cn.wellcare.service.transaction.payment.integrationpay.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 聚合支付订单信息查询返回VO类
 */
@XmlRootElement(name = "DOCUMENT")
public class AggregatePayOrderOutVo {
    private String curPage;//当前页
    private String pageCount;//总页数
    private String total;//总计
    private String returnCode;//响应码
    private String returnMsg; //响应信息
    private String payAmount; //订单金额
    private String refundAmount;//退款金额
    private List<AggregatePayOrderResult> aggregatePayOrderResult;//订单信息
    @XmlElement(name = "CURPAGE")
    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }
    @XmlElement(name = "PAGECOUNT")
    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }
    @XmlElement(name = "TOTAL")
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    @XmlElement(name = "RETURN_CODE")
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    @XmlElement(name = "RETURN_MSG")
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
    @XmlElement(name = "PAYAMOUNT")
    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
    @XmlElement(name = "REFUNDAMOUNT")
    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }
    @XmlElementWrapper(name = "List")
    @XmlElement(name = "QUERYORDER")
    public List<AggregatePayOrderResult> getAggregatePayOrderResult() {
        return aggregatePayOrderResult;
    }
    public void setAggregatePayOrderResult(List<AggregatePayOrderResult> aggregatePayOrderResult) {
        this.aggregatePayOrderResult = aggregatePayOrderResult;
    }
}
