package com.popwindow.bean;


/**
 * popwindow实体bean
 *
 * @author nova
 */
public class PopDataBean {
    //对应key值
    private String index;
    //对应名字
    private String name;

    public PopDataBean(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
