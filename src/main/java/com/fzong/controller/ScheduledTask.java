package com.fzong.controller;

import com.fzong.project.domain.SssBrxx;
import com.fzong.project.service.SssBrxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Component
public class ScheduledTask {

    @Autowired
    private SssBrxxService brxxService;

    @Scheduled(fixedRateString = "${job.fixedRate:60000}")
    public void scheduledTaskRun(){
        List<SssBrxx> brxxList = brxxService.getAutoList();
        System.out.println("-------->"+ brxxList.size());
    }
}
