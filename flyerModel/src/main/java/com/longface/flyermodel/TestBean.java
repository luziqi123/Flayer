package com.longface.flyermodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestBean {

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public TestBean setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public TestBean setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * data
     */
    @SerializedName("data")
    private DataBean data;
    /**
     * status
     */
    private Integer status;

    /**
     * DataBean
     */
    public static class DataBean {
        /**
         * id
         */
        @SerializedName("_id")
        private String id;
        /**
         * author
         */
        @SerializedName("author")
        private String author;
        /**
         * category
         */
        @SerializedName("category")
        private String category;
        /**
         * content
         */
        @SerializedName("content")
        private String content;
        /**
         * createdAt
         */
        @SerializedName("createdAt")
        private String createdAt;
        /**
         * desc
         */
        @SerializedName("desc")
        private String desc;
        /**
         * images
         */
        @SerializedName("images")
        private List<String> images;
        /**
         * index
         */
        @SerializedName("index")
        private Integer index;
        /**
         * isOriginal
         */
        @SerializedName("isOriginal")
        private Boolean isOriginal;
        /**
         * license
         */
        @SerializedName("license")
        private String license;
        /**
         * likeCount
         */
        @SerializedName("likeCount")
        private Integer likeCount;
        /**
         * likeCounts
         */
        @SerializedName("likeCounts")
        private Integer likeCounts;
        /**
         * likes
         */
        @SerializedName("likes")
        private List<String> likes;
        /**
         * markdown
         */
        @SerializedName("markdown")
        private String markdown;
        /**
         * originalAuthor
         */
        @SerializedName("originalAuthor")
        private String originalAuthor;
        /**
         * publishedAt
         */
        @SerializedName("publishedAt")
        private String publishedAt;
        /**
         * stars
         */
        @SerializedName("stars")
        private Integer stars;
        /**
         * status
         */
        @SerializedName("status")
        private Integer status;
        /**
         * tags
         */
        @SerializedName("tags")
        private List<?> tags;
        /**
         * title
         */
        @SerializedName("title")
        private String title;
        /**
         * type
         */
        @SerializedName("type")
        private String type;
        /**
         * updatedAt
         */
        @SerializedName("updatedAt")
        private String updatedAt;
        /**
         * url
         */
        @SerializedName("url")
        private String url;
        /**
         * views
         */
        @SerializedName("views")
        private Integer views;
    }
}
