package com.oyzc.boot.service;

import com.oyzc.boot.bean.City;

public interface CityService {

    City getById(Long id);

    void saveCity(City city);
}
