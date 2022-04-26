package com.fzong.other.service;

import com.fzong.other.domain.SponsorDict;
import com.fzong.other.mapper.SponsorDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申办方字典Service业务层处理
 * 
 * @author ming
 * @date 2021-09-11
 */
@Service
public class ProjectSponsorDictServiceImpl {

    @Autowired
    private SponsorDictMapper sponsorDictMapper;

    public List<SponsorDict> getAll(){
        return sponsorDictMapper.selectList(null);
    }
}
