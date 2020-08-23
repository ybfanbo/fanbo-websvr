package com.fanbo.domain;

public class IocInitBean2 {

    private IocInitBean iocInitBean;
    private String content;

    public IocInitBean2(IocInitBean iocInitBean, String content) {
        this.iocInitBean = iocInitBean;
        this.content = content;
    }

    public IocInitBean getIocInitBean() {
        return iocInitBean;
    }

    public void setIocInitBean(IocInitBean iocInitBean) {
        this.iocInitBean = iocInitBean;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
