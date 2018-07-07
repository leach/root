package com.anythy.app.mapper;

import com.anythy.app.annotation.DataSource;
import com.anythy.app.entity.DltVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DltMapper {
    @Insert("insert into dlt (sn, a, b, c, d, e, f, g) values (#{sn}, #{a}, #{b}, #{c}, #{d}, #{e}, #{f}, #{g})")
    int insertData(DltVO dltVO);


    @Select("select id, sn, a, b, c, d, e, f, g from dlt")
    List<DltVO> queryAll();
}
