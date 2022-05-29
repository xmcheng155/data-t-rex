package com.fzong.controller;

import com.fzong.project.service.SssBrxxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CssdRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CssdRestController.class);
    @Autowired
    private SssBrxxService brxxService;

    @PostMapping("/synByDate")
    public String synByDate(@RequestParam String start, @RequestParam String end){
        LOG.info("入院日期:{} ~ {},数据同步中...",start,end);
        String ret = brxxService.synDataByRegisterTime(start,end);
        LOG.info(ret);
        return ret;
    }

    @PostMapping("/synByzyh")
    public String synByzyh(@RequestParam String zyh){
        LOG.info("住院号/id : {} ,数据同步中...",zyh);
        String ret = brxxService.synDataByKey(zyh);
        LOG.info(ret);
        return ret;
    }
}
