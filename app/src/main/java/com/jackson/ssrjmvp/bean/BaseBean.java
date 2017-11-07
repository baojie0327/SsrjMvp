package com.jackson.ssrjmvp.bean; /**
 * BaseBean  2017-10-09
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 所有实体类的基类
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 09
 */
public class BaseBean<T> implements Serializable {
    protected String page;
    protected String status;
    protected String message;
    protected boolean hasNextPage;
    protected boolean hasData;//因为有的接口有下一页返回的字段不一样
    protected List<T> data;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

