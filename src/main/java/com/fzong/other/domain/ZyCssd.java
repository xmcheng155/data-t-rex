package com.fzong.other.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "zy_cssd",schema = "sa")
public class ZyCssd {
    private String pid;

    @TableId
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

    private Timestamp cyrq;

}
