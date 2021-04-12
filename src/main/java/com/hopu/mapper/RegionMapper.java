package com.hopu.mapper;

import com.hopu.domain.Region;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RegionMapper {
    @Select("select * from t_region")
    List<Region> findAll();

    @Select("select * from t_region where id=#{id}")
    Region findById(Integer id);
}
