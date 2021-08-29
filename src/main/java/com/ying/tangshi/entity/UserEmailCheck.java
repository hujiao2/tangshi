package com.ying.tangshi.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.ying.tangshi.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ts
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserEmailCheck extends BaseEntity {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userNumber;

    private String userEmail;

    private String checkNumber;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer logicDel;


}
