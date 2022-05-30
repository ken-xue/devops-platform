package io.kenxue.devops.domain.common;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午11:35
 */
public abstract class IdentityEntity extends Entity {

    private Long id;

    public IdentityEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
