package com.xiaokun.xiusou.demo6.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class ZakerData {
    String stat;
    Data data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        List<innerData> list;
        String open_app_url;

        public String getOpen_app_url() {
            return open_app_url;
        }

        public void setOpen_app_url(String open_app_url) {
            this.open_app_url = open_app_url;
        }

        public List<innerData> getList() {
            return list;
        }

        public void setList(List<innerData> list) {
            this.list = list;
        }


        public static class innerData {
            String pk;
            String title;
            String date;
            String category;
            String author_name;
            String thumbnail_pic;
            String thumbnail_pic_m;
            String thumbnail_pic_s;
            String thumbnail_pic_w;
            String thumbnail_pic_h;
            String url;

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getThumbnail_pic() {
                return thumbnail_pic;
            }

            public void setThumbnail_pic(String thumbnail_pic) {
                this.thumbnail_pic = thumbnail_pic;
            }

            public String getThumbnail_pic_m() {
                return thumbnail_pic_m;
            }

            public void setThumbnail_pic_m(String thumbnail_pic_m) {
                this.thumbnail_pic_m = thumbnail_pic_m;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_w() {
                return thumbnail_pic_w;
            }

            public void setThumbnail_pic_w(String thumbnail_pic_w) {
                this.thumbnail_pic_w = thumbnail_pic_w;
            }

            public String getThumbnail_pic_h() {
                return thumbnail_pic_h;
            }

            public void setThumbnail_pic_h(String thumbnail_pic_h) {
                this.thumbnail_pic_h = thumbnail_pic_h;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
