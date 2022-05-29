package com.fzong.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fzong.controller.ScheduledTask;
import com.fzong.other.domain.ZyCssd;
import com.fzong.other.mapper.ZyCssdMapper;
import com.fzong.project.domain.SssBrxx;
import com.fzong.project.mapper.SssBrxxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class SssBrxxService {
    private static final Logger LOG = LoggerFactory.getLogger(SssBrxxService.class);
    private final String update = "update";
    private final String insert = "insert";

    @Autowired
    private SssBrxxMapper sssBrxxMapper;

    @Autowired
    private ZyCssdMapper zyCssdMapper;

    @Deprecated
    public String getAutoList(){
        //三分钟内的数据
        Calendar calendar = Calendar.getInstance();
        Timestamp bf = new Timestamp(calendar.getTime().getTime()-(3*60*1000));
        Timestamp now = new Timestamp(calendar.getTime().getTime());
        LambdaQueryWrapper<ZyCssd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(ZyCssd::getRegistTime,bf);
        queryWrapper.lt(ZyCssd::getRegistTime,now);
        return zyCssdMapper.selectList(queryWrapper).toString();
    }

    public String synDataAuto(){
        int updateNum = 0;
        int insertNum = 0;
        List<ZyCssd> zyCssds = selectSourceAuto();
        for(ZyCssd zyCssd : zyCssds){
            String ret = doSynData(zyCssd);
            if(insert.equals(ret)){
                insertNum ++;
            }else if(update.equals(ret)){
                updateNum ++;
            }
        }
        return "共 "+zyCssds.size() +" 条; 新增："+insertNum +"条; 更新："+ updateNum +"条";
    }

    public String synDataByRegisterTime(String startDate,String endDate){
        LocalDate sDate = LocalDate.parse(startDate);
        LocalDate eDate = LocalDate.parse(endDate);
        Timestamp stimestamp = new Timestamp(sDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        Timestamp etimestamp = new Timestamp(eDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        int updateNum = 0;
        int insertNum = 0;
        List<ZyCssd> zyCssds = selectByRegisterTime(stimestamp,etimestamp);
        for(ZyCssd zyCssd : zyCssds){
            String ret = doSynData(zyCssd);
            if(insert.equals(ret)){
                insertNum ++;
            }else if(update.equals(ret)){
                updateNum ++;
            }
        }
        return "共 "+zyCssds.size() +" 条; 新增："+insertNum +"条; 更新："+ updateNum +"条";
    }

    public String synDataByKey(String key){
        int updateNum = 0;
        int insertNum = 0;
        List<ZyCssd> zyCssds = selectOneByID(key);
        for(ZyCssd zyCssd : zyCssds){
            String ret = doSynData(zyCssd);
            if(insert.equals(ret)){
                insertNum ++;
            }else if(update.equals(ret)){
                updateNum ++;
            }
        }
        return "共 "+zyCssds.size() +" 条; 新增："+insertNum +"条; 更新："+ updateNum +"条";
    }

    /**
     * 更新和插入主方法
     * @param zyCssd
     * @return
     */
    private String doSynData(ZyCssd zyCssd){
        LambdaQueryWrapper<SssBrxx> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SssBrxx::getSickId,zyCssd.getSickId());
        SssBrxx sssBrxx = sssBrxxMapper.selectOne(queryWrapper);
        if(sssBrxx != null){
            //update
            Calendar calendar = Calendar.getInstance();
            Timestamp now = new Timestamp(calendar.getTime().getTime());
            LambdaUpdateWrapper<SssBrxx> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(isUpdate(zyCssd.getPid(),sssBrxx.getPid()),SssBrxx::getPid,zyCssd.getPid());
            updateWrapper.set(isUpdate(zyCssd.getSick(),sssBrxx.getSick()),SssBrxx::getSick,zyCssd.getSick());
            updateWrapper.set(isUpdate(zyCssd.getSex(),sssBrxx.getSex()),SssBrxx::getSex,zyCssd.getSex());
            updateWrapper.set(isUpdate(zyCssd.getAge(),sssBrxx.getAge()),SssBrxx::getAge,zyCssd.getAge());
            updateWrapper.set(isUpdate(zyCssd.getCardId(),sssBrxx.getCardId()),SssBrxx::getCardId,zyCssd.getCardId());
            updateWrapper.set(isUpdate(zyCssd.getSickArea(),sssBrxx.getSickArea()),SssBrxx::getSickArea,zyCssd.getSickArea());
            updateWrapper.set(isUpdate(zyCssd.getBed(),sssBrxx.getBed()),SssBrxx::getBed,zyCssd.getBed());
            updateWrapper.set(isUpdate(zyCssd.getNurse(),sssBrxx.getNurse()),SssBrxx::getNurse,zyCssd.getNurse());
            updateWrapper.set(isUpdate(zyCssd.getDoctor(),sssBrxx.getDoctor()),SssBrxx::getDoctor,zyCssd.getDoctor());
            updateWrapper.set(isUpdate(zyCssd.getIllness(),sssBrxx.getIllness()),SssBrxx::getIllness,zyCssd.getIllness());
            updateWrapper.set(isUpdate(zyCssd.getInDept(),sssBrxx.getInDept()),SssBrxx::getInDept,zyCssd.getInDept());
            updateWrapper.set(isUpdate(zyCssd.getTendDept(),sssBrxx.getTendDept()),SssBrxx::getTendDept,zyCssd.getTendDept());
            updateWrapper.set(isUpdate(zyCssd.getInClass(),sssBrxx.getInClass()),SssBrxx::getInClass,zyCssd.getInClass());
            updateWrapper.set(isUpdate(zyCssd.getInTime(),sssBrxx.getInTime()),SssBrxx::getInTime,zyCssd.getInTime());
            updateWrapper.set(isUpdate(zyCssd.getRegistTime(),sssBrxx.getRegistTime()),SssBrxx::getRegistTime,zyCssd.getRegistTime());
            updateWrapper.set(SssBrxx::getUpTime,now);
            updateWrapper.eq(SssBrxx::getSickId,zyCssd.getSickId());
            sssBrxxMapper.update(null,updateWrapper);
            LOG.info("insert id:"+zyCssd.getSick());
            return update;
        }else{
            //insert
            SssBrxx insertBrxx = generateSssBrxx(zyCssd);
            LOG.info("insert id:"+insertBrxx.getId());
            sssBrxxMapper.insert(insertBrxx);
            return insert;
        }
    }

    //数据源不是null，且 原来数据是null的情况下才更新
    private boolean isUpdate(Object source,Object targe){
        return !StringUtils.isEmpty(source) && StringUtils.isEmpty(targe);
    }

    /**
     * 转换数据
     * @param zyCssd cssd
     * @return sssbrxx
     */
    private SssBrxx generateSssBrxx(ZyCssd zyCssd){
        SssBrxx sssBrxx = new SssBrxx();
        Calendar calendar = Calendar.getInstance();
        Timestamp now = new Timestamp(calendar.getTime().getTime());
        //住院号作为流水号
        sssBrxx.setId(zyCssd.getSickId());
        sssBrxx.setPid(getStrDefault(zyCssd.getPid()));
        sssBrxx.setSickId(getStrDefault(zyCssd.getSickId()));
        sssBrxx.setSick(getStrDefault(zyCssd.getSick()));
        sssBrxx.setSex(getStrDefault(zyCssd.getSex()));
        sssBrxx.setAge(getStrDefault(zyCssd.getAge()));
        sssBrxx.setCardId(getStrDefault(zyCssd.getCardId()));
        sssBrxx.setSickArea(getStrDefault(zyCssd.getSickArea()));
        sssBrxx.setBed(getStrDefault(zyCssd.getBed()));
        sssBrxx.setNurse(getStrDefault(zyCssd.getNurse()));
        sssBrxx.setDoctor(getStrDefault(zyCssd.getDoctor()));
        sssBrxx.setIllness(getStrDefault(zyCssd.getIllness()));
        sssBrxx.setInDept(getStrDefault(zyCssd.getInDept()));
        sssBrxx.setTendDept(getStrDefault(zyCssd.getTendDept()));
        sssBrxx.setInClass(getStrDefault(zyCssd.getInClass()));
        sssBrxx.setInTime(zyCssd.getInTime());
        sssBrxx.setRegistTime(zyCssd.getRegistTime());
        sssBrxx.setSickClass(0);
        sssBrxx.setOperTime(now);
        sssBrxx.setUpTime(now);
        return sssBrxx;
    }


    /**
     * 自动查询在院病人
     * @return List
     */
    private List<ZyCssd> selectSourceAuto(){
        LambdaQueryWrapper<ZyCssd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNull(ZyCssd::getCyrq);
        return zyCssdMapper.selectList(queryWrapper);
    }

    /**
     * 按照id指定同步病人数据
     * @param id id
     * @return List
     */
    private List<ZyCssd> selectOneByID(String id){
        LambdaQueryWrapper<ZyCssd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ZyCssd::getSickId,id).or().eq(ZyCssd::getPid,id);
        return zyCssdMapper.selectList(queryWrapper);
    }

    /**
     * 按照入院时间段同步历史数据
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return List
     */
    private List<ZyCssd> selectByRegisterTime(Timestamp startTime,Timestamp endTime){
        LambdaQueryWrapper<ZyCssd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(ZyCssd::getRegistTime,startTime);
        queryWrapper.le(ZyCssd::getRegistTime,endTime);
        return zyCssdMapper.selectList(queryWrapper);
    }

    private String getStrDefault(String str){
        if(str ==null){
            return "";
        }else{
            return str;
        }
    }

}
