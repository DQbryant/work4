package com.dq.work4.controller;

import com.dq.work4.entity.User;
import com.dq.work4.entity.Work;
import com.dq.work4.service.ExamService;
import com.dq.work4.service.WorkService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 */
@Controller
public class WorkController {
    private static Logger logger = LoggerFactory.getLogger(WorkController.class);
    @Autowired
    WorkService workServiceImpl;
    @Autowired
    ExamService examServiceImpl;
    @ResponseBody
    @PostMapping("/uploadfile")     //作业上传
    public ResponseEntity upload(MultipartFile file,
                                 int eid,
                                 String sname,
                                 String num){
        String fileName = file.getOriginalFilename();
        File file1 = new File("D:/work/"+fileName);
        if(file1.exists()){
            Work work = workServiceImpl.selWork(fileName);
            if(!num.equals(work.getNum()))throw new RuntimeException(); //如果不同学生提交相同文件名的作业,会被拒绝
            work.setSubTime(new Date());
            workServiceImpl.updWork(work);
        }else{
            Work work = new Work(sname,num,new Date(),fileName,eid);
            workServiceImpl.addWork(work);
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),file1);
            logger.info(sname+"上传了"+fileName);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @RequestMapping("/downloadfile")    //下载单个作业
    public void downloadfile(int wid, HttpServletResponse response,HttpSession session){
        Work work = workServiceImpl.selWorkById(wid);
        String file = "D:/work/"+work.getFilename();
        response.addHeader("Content-Disposition","attachment;filename="+work.getFilename());
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(file));
            ServletOutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
            User user = (User) session.getAttribute("user");
            logger.info(user.getUsername()+"下载了"+work.getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/downloadfiles")   //批量下载作业,会返回压缩文件
    public void downloadfiles(String wids,HttpServletResponse response,HttpSession session){
        String[] wid = wids.split(",");
        String resourceName = "D:/work/"+"works.zip";
        Work work = workServiceImpl.selWorkById(Integer.parseInt(wid[0]));
        try {
            File destFile = new File(resourceName);
            ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(destFile));
            User user = (User) session.getAttribute("user");
            String msg = user.getUsername()+"下载了";
            for(String id:wid){
                work = workServiceImpl.selWorkById(Integer.parseInt(id));
                msg = msg+work.getFilename()+",";
                outputStream.putNextEntry(new ZipEntry(work.getFilename()));
                outputStream.write(FileUtils.readFileToByteArray(new File("D:/work/"+work.getFilename())));
            }
            outputStream.flush();
            outputStream.close();
            response.addHeader("Content-Disposition","attachment;filename=works.zip");
            ServletOutputStream os = response.getOutputStream();
            os.write(FileUtils.readFileToByteArray(destFile));
            os.flush();
            os.close();
            destFile.delete();
            logger.info(user.getUsername()+"批量下载了"+msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @GetMapping("/showfiles")             //查看某考核的所有作业
    public List<Work> showfile(int eid){
        return workServiceImpl.selWorks(eid);
    }
    @ResponseBody
    @GetMapping("/showfile")            //返回单个考核的作业
    public Work showfiles(int wid){
        return workServiceImpl.selWorkById(wid);
    }
    @ResponseBody
    @DeleteMapping("/deletefile")       //删除单个作业
    public ResponseEntity deletefile(int wid,HttpSession session){
        Work work = workServiceImpl.selWorkById(wid);
        String file = "D:/work/"+work.getFilename();
        if(!new File(file).delete())return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        int result = workServiceImpl.delWork(wid);
        if(result>0){
            User user = (User) session.getAttribute("user");
            logger.info(user.getUsername()+"删除了"+work.getFilename());
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    @ResponseBody
    @DeleteMapping("/deletefiles")      //批量删除作业
    public ResponseEntity deletefiles(String wids,HttpSession session){
        String[] wid = wids.split(",");
        String msg = "";
        int result = 0;
        Work work = null;
        for(String id:wid){
            work = workServiceImpl.selWorkById(Integer.parseInt(id));
            msg+=work.getFilename()+",";
            if(!new File("D:/work/"+work.getFilename()).delete())return new  ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            result += workServiceImpl.delWork(Integer.parseInt(id));
        }
        if(result>0){
            User user = (User) session.getAttribute("user");
            logger.info(user.getUsername()+"批量删除了"+msg);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    @ResponseBody
    @RequestMapping("/examine")     //添加审核人
    public ResponseEntity examine(int wid, int status, HttpSession session){
        User user = (User) session.getAttribute("user");
        Work work = workServiceImpl.selWorkById(wid);
        work.setStatus(status);
        work.setTname(user.getUsername());
        int result = workServiceImpl.updWork(work);
        if(result>0){
            logger.info(user.getUsername()+"审核了"+work.getFilename()+",结果为"+(status==1?"通过":"不通过"));
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
