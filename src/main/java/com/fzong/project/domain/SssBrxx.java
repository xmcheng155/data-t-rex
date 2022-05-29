package com.fzong.project.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("SSS_Brxx")
public class SssBrxx {

    @TableId(value = "ID",type = IdType.INPUT)
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

    private Timestamp inTime;

    private Timestamp registTime;

    private int sickClass;

    private Timestamp operTime;

    private Timestamp upTime;
}
