package com.xiaokun.xiusou.demo6.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24 0024.
 */
public class WebViewData implements Serializable {
    String title;
    String content;
    String urlid;
    List<recomends> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlid() {
        return urlid;
    }

    public void setUrlid(String urlid) {
        this.urlid = urlid;
    }

    public List<recomends> getList() {
        return list;
    }

    public void setList(List<recomends> list) {
        this.list = list;
    }

    public static class recomends implements Serializable{
        String id;
        String title;
        String jianjie;
        String img;
        String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getJianjie() {
            return jianjie;
        }

        public void setJianjie(String jianjie) {
            this.jianjie = jianjie;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
