package com.dq.work4.service.impl;

import com.dq.work4.entity.Work;
import com.dq.work4.mapper.WorkMapper;
import com.dq.work4.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;
    @Override
    public int addWork(Work work) {
        return workMapper.addWork(work);
    }


    @Override
    public List<Work> selWorks(int eid){
        return workMapper.selWorks(eid);
    }

    @Override
    public int delWork(int wid) {
        return workMapper.delWork(wid);
    }

    @Override
    public Work selWork(String filename) {
        return workMapper.selWork(filename);
    }

    @Override
    public int updWork(Work work) {
        return workMapper.updWork(work);
    }

    @Override
    public Work selWorkById(int wid) {
        return workMapper.selWorkById(wid);
    }
}
