package com.xiaokun.xiusou.demo6.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class FangZakerData {
    String stat;
    String msg;
    Data data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        List<Gallery> gallery;
        List<article> articles;
        Menu column_menu;
        Info info;

        public List<Gallery> getGallery() {
            return gallery;
        }

        public void setGallery(List<Gallery> gallery) {
            this.gallery = gallery;
        }

        public List<article> getArticles() {
            return articles;
        }

        public void setArticles(List<article> articles) {
            this.articles = articles;
        }

        public Menu getColumn_menu() {
            return column_menu;
        }

        public void setColumn_menu(Menu column_menu) {
            this.column_menu = column_menu;
        }

        public class Info {
            String comment_list_url;
            String comment_url;
            String comment_reply_url;
            String comment_count_url;
            String comment_hot_url;
            String next_url;

            public String getComment_list_url() {
                return comment_list_url;
            }

            public void setComment_list_url(String comment_list_url) {
                this.comment_list_url = comment_list_url;
            }

            public String getComment_url() {
                return comment_url;
            }

            public void setComment_url(String comment_url) {
                this.comment_url = comment_url;
            }

            public String getComment_reply_url() {
                return comment_reply_url;
            }

            public void setComment_reply_url(String comment_reply_url) {
                this.comment_reply_url = comment_reply_url;
            }

            public String getComment_count_url() {
                return comment_count_url;
            }

            public void setComment_count_url(String comment_count_url) {
                this.comment_count_url = comment_count_url;
            }

            public String getComment_hot_url() {
                return comment_hot_url;
            }

            public void setComment_hot_url(String comment_hot_url) {
                this.comment_hot_url = comment_hot_url;
            }

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public class Gallery {
            String pk;
            String promotion_img;
            String img_height;
            String img_width;
            String title;
            String end_time;
            String type;
            Article article;

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public String getPromotion_img() {
                return promotion_img;
            }

            public void setPromotion_img(String promotion_img) {
                this.promotion_img = promotion_img;
            }

            public String getImg_height() {
                return img_height;
            }

            public void setImg_height(String img_height) {
                this.img_height = img_height;
            }

            public String getImg_width() {
                return img_width;
            }

            public void setImg_width(String img_width) {
                this.img_width = img_width;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Article getArticle() {
                return article;
            }

            public void setArticle(Article article) {
                this.article = article;
            }

            public class Article {
                String pk;
                String title;
                String date;
                String auther_name;
                String weburl;
                String full_url;

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

                public String getAuther_name() {
                    return auther_name;
                }

                public void setAuther_name(String auther_name) {
                    this.auther_name = auther_name;
                }

                public String getWeburl() {
                    return weburl;
                }

                public void setWeburl(String weburl) {
                    this.weburl = weburl;
                }

                public String getFull_url() {
                    return full_url;
                }

                public void setFull_url(String full_url) {
                    this.full_url = full_url;
                }
            }
        }

        public class article {
            String pk;
            String app_ids;
            String title;
            String date;
            String auther_name;
            String weburl;
            String thumbnail_pic;
            String full_url;
            String list_dtime;

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public String getApp_ids() {
                return app_ids;
            }

            public void setApp_ids(String app_ids) {
                this.app_ids = app_ids;
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

            public String getAuther_name() {
                return auther_name;
            }

            public void setAuther_name(String auther_name) {
                this.auther_name = auther_name;
            }

            public String getWeburl() {
                return weburl;
            }

            public void setWeburl(String weburl) {
                this.weburl = weburl;
            }

            public String getThumbnail_pic() {
                return thumbnail_pic;
            }

            public void setThumbnail_pic(String thumbnail_pic) {
                this.thumbnail_pic = thumbnail_pic;
            }

            public String getFull_url() {
                return full_url;
            }

            public void setFull_url(String full_url) {
                this.full_url = full_url;
            }

            public String getList_dtime() {
                return list_dtime;
            }

            public void setList_dtime(String list_dtime) {
                this.list_dtime = list_dtime;
            }
        }

        public class Menu {
            List<MenuList> list;

            public List<MenuList> getList() {
                return list;
            }

            public void setList(List<MenuList> list) {
                this.list = list;
            }

            public class MenuList {
                String title;
                String end_time;
                String type;
                Web web;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Web getWeb() {
                    return web;
                }

                public void setWeb(Web web) {
                    this.web = web;
                }

                public class Web {
                    String url;

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }
    }
}
