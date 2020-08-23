package com.fanbo.domain;

public class IocInitBean3 {

    private IocInitBean iocInitBean;
    private String text;

    public IocInitBean3(IocInitBean iocInitBean, String text) {
        this.iocInitBean = iocInitBean;
        this.text = text;
    }

    public IocInitBean getIocInitBean() {
        return iocInitBean;
    }

    public void setIocInitBean(IocInitBean iocInitBean) {
        this.iocInitBean = iocInitBean;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
