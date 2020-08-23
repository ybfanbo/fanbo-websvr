package com.fanbo.dao;

import com.fanbo.config.DataSourceExtraDb;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@DataSourceExtraDb
@CacheNamespace(blocking = true)  //开启二级缓存
public interface PhoneDao {

    @Select("select phone from tab_phones")
    public List<String> getPhones();

    @Select("SELECT id, phone, NOW() AS 'now' FROM tab_phones")
    public List<Map<String, Object>> getAllPhones();  //可以直接返回Map


    @Select("SELECT id, phone, NOW() AS 'now' FROM tab_phones")
    @ResultType(Map.class)
    public List<Map<String, Object>> getAllPhones2();  //和上面getAllPhones()返回一样，所以@ResultType(Map.class)可以省略不写


}
