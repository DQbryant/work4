package com.dq.work4.entity;

import java.util.Date;

/**
 *
 */
public class Work {
    private int wid;
    private String sname;
    private String num;
    private Date subTime;
    private int status;
    private String filename;
    private int eid;
    private String tname;
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getTname() {
        return tname;
    }

    public Work() {
    }

    public Work(String sname, String num, Date subTime, String filename, int eid) {
        this.sname = sname;
        this.num = num;
        this.subTime = subTime;
        this.filename = filename;
        this.eid = eid;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getSubTime() {
        return subTime;
    }

    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
