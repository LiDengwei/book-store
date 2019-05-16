package com.book.manager.domain.security;

/**
 * Created by Nyx on 2018/1/26.
 */
public class ResourceTreeEntity {
    // 资源 ID
    private Integer id ;
    //父级资源 id
    private Integer pid ;
    //资源名
    private String text ;
    //资源级别
    private Integer resourceLevel;
    //是否被选中
    private Boolean ischecked;

    //非数据库字段
    private Boolean isExpand;//是否在tree中展开


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIschecked() {
        return ischecked;
    }

    public void setIschecked(Boolean ischecked) {
        this.ischecked = ischecked;
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public Boolean getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(Boolean isExpand) {
        this.isExpand = isExpand;
    }
}
