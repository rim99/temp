package net.rim99.demo.account.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {

    String getNameById(@Param("id") int id);

    void add(@Param("id") int id,
             @Param("name") String name);
}
