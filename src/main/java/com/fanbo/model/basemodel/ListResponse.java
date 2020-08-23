package com.fanbo.model.basemodel;


import java.util.List;

public class ListResponse<T> extends Response {

    private List<T> dataList;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
