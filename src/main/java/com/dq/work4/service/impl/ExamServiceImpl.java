package com.dq.work4.service.impl;

import com.dq.work4.entity.Exam;
import com.dq.work4.entity.User;
import com.dq.work4.mapper.ExamMapper;
import com.dq.work4.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamMapper examMapper;

    @Override
    public int delExam(int eid) {
        return examMapper.delExam(eid);
    }

    @Override
    public List<Exam> selExam(User user) {
        return examMapper.selExam(user);
    }

    @Override
    public int addExam(Exam exam) {
       return examMapper.insExam(exam);
    }

    @Override
    public int updExam(Exam exam) {
        return examMapper.updExam(exam);
    }

    @Override
    public Exam getExamById(int eid) {
        return examMapper.getExamById(eid);
    }


}
