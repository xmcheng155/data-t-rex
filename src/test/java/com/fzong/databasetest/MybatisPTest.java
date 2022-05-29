package com.fzong.databasetest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fzong.DataTrexApplication;
import com.fzong.project.domain.SssBrxx;
import com.fzong.project.domain.SysConfig;

import com.fzong.project.mapper.SssBrxxMapper;
import com.fzong.project.mapper.SysConfigMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataTrexApplication.class)
public class MybatisPTest {

    @Autowired
    private SssBrxxMapper configMapper;

    @Test
    public void testSelect(){
        List<SssBrxx> sysConfigs = configMapper.getTop(5);
        sysConfigs.forEach(System.out::println);
    }

    @Test
    public void testSelect1(){
        //3分钟
        Calendar calendar = Calendar.getInstance();
        Timestamp bf = new Timestamp(calendar.getTime().getTime()-(3*60*1000));
        Timestamp now = new Timestamp(calendar.getTime().getTime());
        System.out.println(now.after(bf));
        QueryWrapper<SssBrxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SssBrxx::getUpTime,now).lt(SssBrxx::getUpTime,bf);
        List<SssBrxx> sysConfigs = configMapper.selectList(queryWrapper);
        sysConfigs.forEach(System.out::println);
    }

    @Test
    public void testSelect2(){
        SssBrxx sssBrxx = configMapper.selectById("000ab9013f2f4e428673a8e728871d08");
        System.out.println(sssBrxx);
    }

    @Test
    public void testSelect3(){
        LambdaUpdateWrapper<SssBrxx> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(true,SssBrxx::getPid,"ZY010001373767");
        updateWrapper.set(SssBrxx::getId,"000ab9013f2f4e428673a8e728871d08");
        updateWrapper.eq(SssBrxx::getId,"000ab9013f2f4e428673a8e728871d08");
        configMapper.update(null,updateWrapper);
    }

    @Test
    public void testSelect4(){
        LocalDate sDate = LocalDate.parse("2020-08-10");
        Timestamp stimestamp = new Timestamp(sDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        SssBrxx sssBrxx = new SssBrxx();
        sssBrxx.setUpTime(stimestamp);
        System.out.println(sssBrxx);
        QueryWrapper<SssBrxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(SssBrxx::getUpTime,stimestamp);
        List<SssBrxx> sysConfigs = configMapper.selectList(queryWrapper);
        sysConfigs.forEach(System.out::println);

    }

}
