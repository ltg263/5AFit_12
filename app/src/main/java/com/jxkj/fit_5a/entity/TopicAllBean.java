package com.jxkj.fit_5a.entity;

import java.util.List;

public class TopicAllBean {

    /**
     * articlesCount : 0
     * children : [{"articlesCount":0,"children":[{}],"name":""}]
     * name :
     */

    private int articlesCount;
    private String name;
    private List<ChildrenBeanX> children;

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * articlesCount : 0
         * children : [{}]
         * name :
         */

        private int articlesCount;
        private String name;
        private List<ChildrenBean> children;

        public int getArticlesCount() {
            return articlesCount;
        }

        public void setArticlesCount(int articlesCount) {
            this.articlesCount = articlesCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
        }
    }
}
