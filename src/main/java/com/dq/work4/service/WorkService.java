package com.dq.work4.service;

import com.dq.work4.entity.Work;

import java.util.List;

/**
 *
 */
public interface WorkService {
    int addWork(Work work);
    List<Work> selWorks(int eid);
    int delWork(int wid);
    Work selWork(String filename);
    int updWork(Work work);
    Work selWorkById(int wid);
}
