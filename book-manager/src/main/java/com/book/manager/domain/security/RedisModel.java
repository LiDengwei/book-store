package com.book.manager.domain.security;

/**
 * Created by $_Mai on 2018/5/24.
 */
public class RedisModel {
    private String key ; // 键值
    private String value ; // 值
    private Long time ;  // 超时时间
    private Integer type ; // 修改类型 1 value 2 时间 3 value和时间

    public enum TypeEnum {
        VALUE(1,"修改value") , TIME(2,"修改时间") , ALL(3,"修改全部");
        Integer value ;
        String remark ;
        private TypeEnum(Integer value,String remark){
            this.value = value ;
            this.remark = remark ;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
