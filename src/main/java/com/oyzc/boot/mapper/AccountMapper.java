package com.oyzc.boot.mapper;

import com.oyzc.boot.bean.Account;
import org.apache.ibatis.annotations.Mapper;

// @Mapper
public interface AccountMapper {

    Account getAccount(Integer id);
}
