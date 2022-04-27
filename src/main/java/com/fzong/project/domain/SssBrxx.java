package com.fzong.project.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("SSS_Brxx")
public class SssBrxx {

    @TableId
    private String id;

    private String pid;

    private String sickId;

    private String sick;

    private String sex;

    private String age;

    private String cardId;

    private String sickArea;

    private String bed;

    private String nurse;

    private String doctor;

    private String illness;

    private String inDept;

    private String tendDept;

    private String inClass;

    private Date inTime;

    private int sickClass;

    private Date operTime;

    private Date upTime;
}
