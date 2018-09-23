package com.kimremain.apipractice.vo;

public class NaverItemVO {
    private int no;
    private String id;
    private String name;
    private String phone;
    private String urlBasic;
    private String urlMap;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrlBasic() {
        return urlBasic;
    }
    public void setUrlBasic(String urlBasic) {
        this.urlBasic = urlBasic;
    }
    public String getUrlMap() {
        return urlMap;
    }
    public void setUrlMap(String urlMap) {
        this.urlMap = urlMap;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
}
