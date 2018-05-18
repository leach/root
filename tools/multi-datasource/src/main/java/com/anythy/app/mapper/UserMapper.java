package com.anythy.app.mapper;

import com.anythy.app.annotation.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<Map<String, Object>> findAll();

    @Select("select * from user")
    @DataSource("test1")
    List<Map<String, Object>> findAll2();
}
