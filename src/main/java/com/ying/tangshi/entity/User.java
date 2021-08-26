package com.ying.tangshi.entity;

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
public class User extends BaseEntity {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userNumber;

    private String userName;

    private String userPhone;

    private String userEmail;

    private String userClass;

    private String userState;

    private String userStateClass;

    private String userPassword;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer logicDel;


}
