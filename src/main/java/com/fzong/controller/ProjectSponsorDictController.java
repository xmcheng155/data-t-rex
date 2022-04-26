package com.fzong.controller;

import com.fzong.other.domain.SponsorDict;
import com.fzong.other.service.ProjectSponsorDictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 申办方字典Controller
 * 
 * @author ming
 * @date 2021-09-11
 */
@RestController
@RequestMapping("/project/sponsordict")
public class ProjectSponsorDictController
{
    @Autowired
    private ProjectSponsorDictServiceImpl projectSponsorDictService;

    /**
     * 查询申办方字典列表
     */
    @GetMapping("/list")
    public String list()
    {
        List<SponsorDict> list = projectSponsorDictService.getAll();
        list.forEach(System.out::println);
        return list.toString();
    }


}
