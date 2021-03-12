package com.gustavo.service.impl;

import com.gustavo.base.BaseDao;
import com.gustavo.base.BaseServiceImpl;
import com.gustavo.mapper.MessageMapper;
import com.gustavo.po.Message;
import com.gustavo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Autowired
    MessageMapper messageMapper;


    @Override
    public BaseDao<Message> getBaseDao() {
        return messageMapper;
    }
}
