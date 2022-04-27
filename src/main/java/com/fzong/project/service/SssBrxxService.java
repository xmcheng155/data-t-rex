package com.fzong.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzong.other.mapper.ZyCssdMapper;
import com.fzong.project.domain.SssBrxx;
import com.fzong.project.mapper.SssBrxxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class SssBrxxService {

    @Autowired
    private SssBrxxMapper sssBrxxMapper;

    @Autowired
    private ZyCssdMapper zyCssdMapper;

    public List<SssBrxx> getAutoList(){
        //3分钟
        Calendar calendar = Calendar.getInstance();
        Timestamp bf = new Timestamp(calendar.getTime().getTime()-(3*60*1000));
        Timestamp now = new Timestamp(calendar.getTime().getTime());
        QueryWrapper<SssBrxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().gt(SssBrxx::getUpTime,bf).lt(SssBrxx::getUpTime,now);
        sssBrxxMapper.selectList(queryWrapper);

        zyCssdMapper.selectById("123");
        return null;
    }
}
