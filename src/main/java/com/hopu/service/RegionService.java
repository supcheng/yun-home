package com.hopu.service;

import com.hopu.domain.Region;

import java.util.List;

public interface RegionService {
    List<Region> findAll();
    Region findById(Integer id);
}
