package com.dq.work4.mapper;

import com.dq.work4.entity.Work;
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
public interface WorkMapper {
    @Select("select * from work where eid=#{0}")
    List<Work> selWorks(int eid);
    @Insert("insert into work value(default,#{sname},#{num},#{subTime},#{status},#{filename},#{eid},#{tname})")
    int addWork(Work work);

    @Select("select * from work where filename=#{0}")
    Work selWork(String filename);

    @Delete("delete from work where wid=#{0}")
    int delWork(int wid);
    @Update("update work set status=#{status},tname=#{tname} where wid=#{wid}")
    int updWork(Work work);
}