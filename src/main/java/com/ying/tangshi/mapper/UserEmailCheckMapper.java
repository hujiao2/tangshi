package com.ying.tangshi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ying.tangshi.entity.UserEmailCheck;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ying
 * @since 2021-08-26
 */
@Service
@Mapper
public interface UserEmailCheckMapper extends BaseMapper<UserEmailCheck> {
    UserEmailCheck selectByUserNumber(String UserNumber);
    int deletedByEmailId(int emailId);

}
