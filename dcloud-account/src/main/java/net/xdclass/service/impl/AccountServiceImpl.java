package net.xdclass.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.controller.req.*;
import net.xdclass.dto.AccountDTO;
import net.xdclass.mapper.AccountMapper;
import net.xdclass.mapper.SocialAccountMapper;
import net.xdclass.model.AccountDO;
import net.xdclass.model.SocialAccountDO;
import net.xdclass.service.AccountService;
import net.xdclass.util.SpringBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private SocialAccountMapper socialAccountMapper;

    @Override
    public Map<String, Object> page(AccountPageReq req) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        if(req.getUsername()!=null){
            queryWrapper.like(AccountDO::getUsername, req.getUsername());
        }
        queryWrapper.orderByDesc(AccountDO::getId);
        Page<AccountDO> page = new Page<>(req.getPage(), req.getSize());
        Page<AccountDO> accountDOPage = accountMapper.selectPage(page, queryWrapper);

        //获取分页信息
        List<AccountDO> accountDOList = accountDOPage.getRecords();
        List<AccountDTO> accountDTOList = SpringBeanUtil.copyProperties(accountDOList, AccountDTO.class);
        Map<String,Object> pageInfo = new HashMap<>(3);
        pageInfo.put("total_record",accountDOPage.getTotal());
        pageInfo.put("total_page",accountDOPage.getPages());
        pageInfo.put("current_data",accountDTOList);
        return pageInfo;
    }

    @Override
    public int del(AccountDelReq req) {
        int rows = accountMapper.deleteById(req.getId());
        if(rows>0){
            LambdaQueryWrapper<SocialAccountDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SocialAccountDO::getAccountId, req.getId());
            socialAccountMapper.delete(queryWrapper);
        }
        return rows;
    }

    @Override
    public int updateAccountStatus(AccountUpdateReq req) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(req.getId());
        accountDO.setIsDisabled(req.getEnabled());
        return accountMapper.updateById(accountDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(AccountRegisterReq req) {
        //先查询账号子表有没记录，没记录才插入主账号，然后再插入子表，这边不考虑并发，不加锁
        LambdaQueryWrapper<SocialAccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialAccountDO::getIdentifier,req.getIdentifier());
        queryWrapper.eq(SocialAccountDO::getIdentityType,req.getIdentityType()).last("limit 1");
        SocialAccountDO dbSocialAccountDO = socialAccountMapper.selectOne(queryWrapper);
        if(dbSocialAccountDO == null){
            //创建主账号
            AccountDO accountDO = new AccountDO();
            accountDO.setUsername(req.getUsername());
            accountDO.setIsDisabled(true);
            accountMapper.insert(accountDO);

            //创建子账号
            SocialAccountDO socialAccountDO = new SocialAccountDO();
            socialAccountDO.setAccountId(accountDO.getId());
            if(StringUtils.isNotBlank(req.getCredential())){
                socialAccountDO.setCredential(SaSecureUtil.md5(req.getCredential()));
            }
            socialAccountDO.setIdentifier(req.getIdentifier());
            socialAccountDO.setIdentityType(req.getIdentityType());
            return socialAccountMapper.insert(socialAccountDO);
        }else {
            log.error("账号唯一标识已经存在:{}",req);
            return 0;
        }

    }

    /**
     * 查询子账号表，然后查询主表（判断账号是否冻结）
     * @param req
     * @return
     */
    @Override
    public AccountDTO login(AccountLoginReq req) {
        //先查找子表
        LambdaQueryWrapper<SocialAccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SocialAccountDO::getIdentifier,req.getIdentifier());
        queryWrapper.eq(SocialAccountDO::getIdentityType,req.getIdentityType());
        queryWrapper.eq(SocialAccountDO::getCredential,SaSecureUtil.md5(req.getCredential())).last("limit 1");
        SocialAccountDO socialAccountDO = socialAccountMapper.selectOne(queryWrapper);
        if(socialAccountDO!=null){
            //查询主账号表
            AccountDO accountDO = accountMapper.selectById(socialAccountDO.getAccountId());
            //不为空，且不是冻结状态
            if(accountDO!=null && !accountDO.getIsDisabled()){
                return SpringBeanUtil.copyProperties(accountDO, AccountDTO.class);
            }
        }
        return null;
    }
}
