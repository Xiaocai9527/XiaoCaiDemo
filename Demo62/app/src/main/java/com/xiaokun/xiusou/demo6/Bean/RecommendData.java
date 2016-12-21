package com.xiaokun.xiusou.demo6.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class RecommendData {
    List<data> list;

    public List<data> getList() {
        return list;
    }

    public void setList(List<data> list) {
        this.list = list;
    }

    public static class data {
        String id;
        String title;
        String jianjie;
        String url;
        String img;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
