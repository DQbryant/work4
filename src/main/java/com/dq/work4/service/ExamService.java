package com.dq.work4.service;

import com.dq.work4.entity.Exam;
import com.dq.work4.entity.User;

import java.util.List;

/**
 *
 */
public interface ExamService {
    int delExam(int eid);
    List<Exam> selExam(User user);
    int addExam(Exam exam);
    int updExam(Exam exam);
    Exam getExamById(int eid);
}