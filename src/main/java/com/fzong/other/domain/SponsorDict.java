package com.fzong.other.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "project_sponsor_dict")
public class SponsorDict {
    /** 编号 */
    @TableId(value="column_id")
    private Long columnId;

    /** 申办者名称 */
    private String sponsorName;

    /** 社会信用代码 */
    private String socialCreditCode;

    /** 电话 */
    private String telephone;

    /** 邮箱 */
    private String mailAddress;

    /** 地址 */
    private String address;

    /** 排序 */
    private Long sort;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


}
