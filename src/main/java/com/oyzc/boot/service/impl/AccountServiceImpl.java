package com.oyzc.boot.service.impl;

import com.oyzc.boot.bean.Account;
import com.oyzc.boot.mapper.AccountMapper;
import com.oyzc.boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;

    @Autowired(required = false)
    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public Account getById(Integer id) {
        return accountMapper.getAccount(id);
    }
}
