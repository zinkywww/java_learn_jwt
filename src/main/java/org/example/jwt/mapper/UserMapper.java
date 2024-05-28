package org.example.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.jwt.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where email=#{email} and password=#{password}")
    public List<User> query(User user);

    @Insert("INSERT into user values (#{id},#{name},#{password},#{email})")
    public int insert(User user);
}
