package io.kenxue.cicd.domain.common;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午11:33
 */
public abstract class CommonEntity extends UUIDEntity {

    private Date gmtCreate;
    private Date gmtModified;
    private String creator;
    private String modifier;
    private Boolean deleted;

    public CommonEntity() {
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void create(String operator){
        this.setCreator(operator);
        this.setModifier(operator);
        Date date = new Date();
        this.setGmtCreate(date);
        this.setGmtModified(date);
        this.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
    }
    public void deleted(String operator) {
        this.setDeleted(Boolean.TRUE);
        this.setModifier(operator);
    }
}
