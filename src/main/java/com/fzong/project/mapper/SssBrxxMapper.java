package com.fzong.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzong.project.domain.SssBrxx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SssBrxxMapper extends BaseMapper<SssBrxx> {

    @Select("select top ${top} * from SSS_Brxx")
    List<SssBrxx> getTop(@Param("top") int top);
}
