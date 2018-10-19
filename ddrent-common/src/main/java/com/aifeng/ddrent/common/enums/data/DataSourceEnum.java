/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.common.enums.data
 * @author imart·deng
 * @date 创建时间：2018/10/16 18:40
 * @version 1.0
 */
package com.aifeng.ddrent.common.enums.data;

/**
 * @ClassName: DataSourceEnum
 * @Description: 数据来源枚举
 * @author: imart·deng
 * @date: 2018/10/16 18:40
 *
 */
public enum DataSourceEnum {
    HIZHU(1, "嗨住", "m.hizhu.com"),
    ;
    private int code;
    private String name;
    private String domain;

    DataSourceEnum(int code, String name, String domain) {
        this.code = code;
        this.name = name;
        this.domain = domain;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "DataSourceEnum{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
