package com.gustavo.service.impl;

import com.gustavo.base.BaseDao;
import com.gustavo.base.BaseServiceImpl;
import com.gustavo.mapper.ItemCategoryMapper;
import com.gustavo.mapper.ManageMapper;
import com.gustavo.po.ItemCategory;
import com.gustavo.po.Manage;
import com.gustavo.service.ItemCategoryService;
import com.gustavo.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl extends BaseServiceImpl<ItemCategory> implements ItemCategoryService {

    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    @Override
    public BaseDao<ItemCategory> getBaseDao() {
        return itemCategoryMapper;
    }
}
