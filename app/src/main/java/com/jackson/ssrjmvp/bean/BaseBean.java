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
    //  HYB
    protected String page;
    protected String status;
    protected String message;
    protected boolean hasNextPage;
    protected boolean hasData;//因为有的接口有下一页返回的字段不一样
    protected List<T> data;
    private boolean success;

    // Moive
    protected String code;
    protected String msg;
    private String request;
    protected int count;
    protected int start;
    protected int total;
    protected String title;
    protected List<T> subjects;

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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<T> subjects) {
        this.subjects = subjects;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

