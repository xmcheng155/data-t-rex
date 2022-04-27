package com.fzong.databasetest;

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
}
