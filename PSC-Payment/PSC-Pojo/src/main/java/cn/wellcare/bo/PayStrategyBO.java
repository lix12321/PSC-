package cn.wellcare.bo;

import cn.wellcare.entity.payset.PayStrategy;

public class PayStrategyBO extends PayStrategy {

    /**
     * 机构名称
     */
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
