package com.zeeyeh.probsolve.entity.data;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 第三方登录表 实体类。
 *
 * @author Qinloren
 * @since 1.0.0
 */
//@Table("pb_third_party_logins")
public class ThirdPartyLogins implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一Id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户Id
     */
    @Column(value = "user_id")
    private Long userId;

    /**
     * 第三方平台
     */
    private String platform;

    /**
     * 第三方平台用户标识
     */
    @Column(value = "open_id")
    private String openId;

    /**
     * 绑定时间
     */
    @Column(value = "create_time")
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
