package cn.wellcare.bo;

import cn.wellcare.entity.notify.PayNotify;

public class PayNotifyBO extends PayNotify {


    private  Short notifyStatus;

    public Short getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Short notifyStatus) {
        this.notifyStatus = notifyStatus;
    }
}
