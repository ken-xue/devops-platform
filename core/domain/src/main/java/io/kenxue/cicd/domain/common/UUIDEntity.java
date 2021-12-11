package io.kenxue.cicd.domain.common;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午11:34
 */
public abstract class UUIDEntity extends IdentityEntity {

    private String uuid;

    public UUIDEntity() {
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
