package com.xiaokun.xiusou.demo6.Bean;

import com.xiaokun.xiusou.demo6.Utils.CustomHashMap;
import com.xiaokun.xiusou.demo6.Utils.CustomJSONArray;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class NewsData implements Serializable {

    List<NewsDetails> list;
    OutNews outNews;
    CustomJSONArray customJSONArray;

    public CustomJSONArray getCustomJSONArray() {
        return customJSONArray;
    }

    public void setCustomJSONArray(CustomJSONArray customJSONArray) {
        this.customJSONArray = customJSONArray;
    }

    public List<NewsDetails> getList() {
        return list;
    }

    public void setList(List<NewsDetails> list) {
        this.list = list;
    }

    public class OutNews implements Serializable{

    }

    public class NewsDetails implements Serializable {
        String id;
        String img;
        String url;
        String title;
        String content;
        int num;
        CustomHashMap<String, Integer> hashMap;




        public CustomHashMap<String, Integer> getHashMap() {
            return hashMap;
        }

        public void setHashMap(CustomHashMap<String, Integer> hashMap) {
            this.hashMap = hashMap;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
