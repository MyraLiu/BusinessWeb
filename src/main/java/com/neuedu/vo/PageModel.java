package com.neuedu.vo;

import java.io.Serializable;
import java.util.List;

public class PageModel<T> implements Serializable {
    //每页的数据
    private List<T> data;
    //总共页数
    private Long totalPage;
//    是否为首页
    private  Boolean isFirst;
//    是否为最后一页
    private Boolean isLast;
    // 当前页
    private Integer currentPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "data=" + data +
                ", totalPage=" + totalPage +
                ", isFirst=" + isFirst +
                ", isLast=" + isLast +
                '}';
    }
}
