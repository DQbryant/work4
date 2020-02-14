package com.dq.work4.entity;

import java.util.Date;

/**
 *
 */
public class Exam {
    private int eid;

    private String name;
    private Date deadline;
    private int tid;
    private String content;

    public Exam() {
    }

    public Exam(String name, Date deadline, int tid, String content) {
        this.name = name;
        this.deadline = deadline;
        this.tid = tid;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
