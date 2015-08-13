package com.scxh.android1502.dataparse.json.bean.arounds;

import java.util.List;

public class AroundInfo {
    public AroundPageInfo pageInfo;
    public List<AroundMerchantBean> merchantKey;

	public List<AroundMerchantBean> getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(List<AroundMerchantBean> merchantKey) {
        this.merchantKey = merchantKey;
    }

    public AroundPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(AroundPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
