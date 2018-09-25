package com.neuedu.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ShippingListVO<T> implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private String orderBy;
    private Integer startRow;
    private Integer endRow;
    private Integer total;
    private Integer pages;
    private List<T>  list;
    private Integer firstPage;
    private Integer prePage;
    private Integer nextPage;
    private Integer lastPage;
    private Boolean isFirstPage;
    private Boolean isLastPage;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private Integer navigatePages;
    private Integer[] navigatepageNums;

    @Override
    public String toString() {
        return "ShippingListVO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", orderBy='" + orderBy + '\'' +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                ", firstPage=" + firstPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", lastPage=" + lastPage +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                ", navigatePages=" + navigatePages +
                ", navigatepageNums=" + Arrays.toString(navigatepageNums) +
                '}';
    }

    public ShippingListVO() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Boolean firstPage) {
        isFirstPage = firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public Integer getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(Integer navigatePages) {
        this.navigatePages = navigatePages;
    }

    public Integer[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(Integer[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }
}
