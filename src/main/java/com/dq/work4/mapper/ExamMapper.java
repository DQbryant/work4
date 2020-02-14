package com.dq.work4.mapper;

import com.dq.work4.entity.Exam;
import com.dq.work4.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface ExamMapper {
    @Select("select * from exam where tid=#{uid}")
    List<Exam> selExam(User user);

    @Delete("delete from exam where eid=#{eid}")
    int delExam(int eid);

    @Insert("insert into exam value (default,#{name},#{deadline},#{content},#{tid})")
    int insExam(Exam exam);

    @Update("update exam set name=#{name},deadline=#{deadline},content=#{content} where eid=#{eid}")
    int updExam(Exam exam);

    @Select("select * from exam where eid=#{0}")
    Exam getExamById(int eid);
}
