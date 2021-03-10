package com.gustavo.service.impl;

import com.gustavo.base.BaseDao;
import com.gustavo.base.BaseServiceImpl;
import com.gustavo.mapper.ItemMapper;
import com.gustavo.po.Item;
import com.gustavo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public BaseDao<Item> getBaseDao() {
        return itemMapper;
    }
}
