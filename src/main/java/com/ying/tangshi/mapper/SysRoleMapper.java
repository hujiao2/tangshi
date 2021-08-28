package com.ying.tangshi.mapper;

import com.ying.tangshi.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ying
 * @since 2021-08-27
 */
@Service
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}
