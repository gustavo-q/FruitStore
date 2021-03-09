package com.gustavo.service.impl;

import com.gustavo.base.BaseDao;
import com.gustavo.base.BaseServiceImpl;
import com.gustavo.mapper.ManageMapper;
import com.gustavo.po.Manage;
import com.gustavo.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {

    @Autowired
     ManageMapper manageMapper;

    @Override
    public BaseDao<Manage> getBaseDao() {
        return manageMapper;
    }
}
