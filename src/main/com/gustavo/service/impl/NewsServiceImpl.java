package com.gustavo.service.impl;

import com.gustavo.base.BaseDao;
import com.gustavo.base.BaseServiceImpl;
import com.gustavo.mapper.NewsMapper;
import com.gustavo.po.News;
import com.gustavo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public BaseDao<News> getBaseDao() {
        return newsMapper;
    }
}
