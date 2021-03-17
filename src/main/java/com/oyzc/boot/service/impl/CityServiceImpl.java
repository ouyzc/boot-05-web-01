package com.oyzc.boot.service.impl;

import com.oyzc.boot.bean.City;
import com.oyzc.boot.mapper.CityMapper;
import com.oyzc.boot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private CityMapper cityMapper;

    @Autowired(required = false)
    public void setCityMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public City getById(Long id) {
        return cityMapper.getById(id);
    }

    public void saveCity(City city) {
        cityMapper.insert(city);
    }
}
