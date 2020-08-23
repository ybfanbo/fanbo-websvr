package com.fanbo.dao;

import com.fanbo.config.DataSourceWebsvrDb;
import com.fanbo.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@DataSourceWebsvrDb
public interface UserDao {

    @Select("select user_name from tab_users")
    public List<String> getUserNames();

    @Select("select * from tab_users where username = #{username}")
    @Results({@Result(column = "update_date", property = "updateDate"), @Result(column = "create_date", property = "createDate")})
    public UserInfo getUserInfoByUsername(@Param("username") String username);

}
