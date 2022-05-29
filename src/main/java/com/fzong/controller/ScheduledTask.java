package com.fzong.controller;

import com.fzong.project.service.SssBrxxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class ScheduledTask {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);
    @Autowired
    private SssBrxxService brxxService;

    @Scheduled(fixedRateString = "${job.fixedRate:300000}")
    public void scheduledTaskRun(){
        LOG.info("启动定时任务，同步数据中...");
        String ret =brxxService.synDataAuto();
        LOG.info(ret);
    }
}
