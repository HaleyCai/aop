package com.example.aop.mapper;

import com.example.aop.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    String getRole(String account);

    String login(String account);

    int insert(Student student);

    int update(Student student);

    int delete(String name);

    List<Student> select(String item, String value);

    List<Student> getAll();
}
