package com.example.cwang.smartbutler.entity;

/**
 * 项目名: SmartButler
 * 包名: com.example.cwang.smartbutler.entity
 * 文件名: WeChatData
 * 创建者: cwang
 * 创建时间: 2018/8/18 下午10:02
 */

public class WeChatData
{
    private String title;
    private String source;
    private String imgUrl;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }



}
