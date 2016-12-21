package com.xiaokun.xiusou.demo6.Bean;


import java.util.List;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class CommentData {
    List<CommentDetail> list;

    public List<CommentDetail> getList() {
        return list;
    }

    public void setList(List<CommentDetail> list) {
        this.list = list;
    }

    public class CommentDetail {
        String id;
        String uid;
        String articleid;
        String content;
        String addtime;
        String nicheng;
        String touxiang;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getArticleid() {
            return articleid;
        }

        public void setArticleid(String articleid) {
            this.articleid = articleid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getNicheng() {
            return nicheng;
        }

        public void setNicheng(String nicheng) {
            this.nicheng = nicheng;
        }

        public String getTouxiang() {
            return touxiang;
        }

        public void setTouxiang(String touxiang) {
            this.touxiang = touxiang;
        }
    }
}
