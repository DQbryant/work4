package com.dq.work4.controller;

import com.dq.work4.entity.Exam;
import com.dq.work4.entity.User;
import com.dq.work4.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
public class ExamController {
    private static Logger logger = LoggerFactory.getLogger(ExamController.class);
    @Autowired
    ExamService examServiceImpl;
    @ResponseBody
    @PostMapping("/exam/add")
    public ResponseEntity addExam(String name,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date deadline,String content, HttpSession session){
        User user = (User)session.getAttribute("user");
        Exam exam = new Exam(name,deadline,user.getUid(),content);
        int result = examServiceImpl.addExam(exam);
        if (result>0){
            logger.info(user.getUsername()+"上传了"+name);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    @ResponseBody
    @DeleteMapping("/exam/delete")
    public ResponseEntity delExam(int eid,HttpSession session){
        int result = examServiceImpl.delExam(eid);
        User user = (User) session.getAttribute("user");
        if (result>0){
            logger.info(user.getUsername()+"删除了一个考核");
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    @ResponseBody
    @GetMapping("/exam/select")
    public Exam selExam(int eid){
        return examServiceImpl.getExamById(eid);
    }
    @ResponseBody
    @GetMapping("/exams")
    public List<Exam> selExams(HttpSession session){
        User user = (User) session.getAttribute("user");
        return examServiceImpl.selExam(user);
    }
    @ResponseBody
    @PutMapping("/exam/update")
    public ResponseEntity updExam(int eid,String name,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date deadline,String content,HttpSession session){
        Exam exam = examServiceImpl.getExamById(eid);
        exam.setName(name);
        exam.setDeadline(deadline);
        exam.setContent(content);
        int result = examServiceImpl.updExam(exam);
        if (result>0){
            User user = (User) session.getAttribute("user");
            logger.info(user.getUsername()+"将"+eid+"考核名更改为"+name+",截止日期更改为"+deadline+",内容更改为"+content);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
