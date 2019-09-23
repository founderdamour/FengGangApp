package com.zkhy.fenggang.community.model.api;

import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/13 on 10:51
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class PageData<T> {

    /**
     * pageNum : 1
     * pageSize : 10
     * size : 6
     * startRow : 1
     * endRow : 6
     * total : 6
     * pages : 1
     * list : [{"id":"269","flowId":"0","flowName":null,"applyCode":"","handleType":true,"applyUserId":"1","applyUserName":null,"applyTime":"2018-12-12 14:04:08","apprStatus":0,"provinceId":null,"cityId":null,"county":null,"town":null,"addrDetail":"遵义市汇川区上海路街道办事处","userName":"管理员","phone":"18819204550","idcard":"52212123452353254325"},{"id":"69","flowId":"0","flowName":null,"applyCode":"","handleType":true,"applyUserId":"1","applyUserName":null,"applyTime":"2018-12-12 14:04:03","apprStatus":0,"provinceId":null,"cityId":null,"county":null,"town":null,"addrDetail":"遵义市汇川区上海路街道办事处","userName":"管理员","phone":"18819204550","idcard":"52212123452353254325"},{"id":"67","flowId":"0","flowName":null,"applyCode":"","handleType":true,"applyUserId":"1","applyUserName":null,"applyTime":"2018-12-12 14:03:57","apprStatus":0,"provinceId":null,"cityId":null,"county":null,"town":null,"addrDetail":"遵义市汇川区上海路街道办事处","userName":"管理员","phone":"18819204550","idcard":"52212123452353254325"},{"id":"66","flowId":"0","flowName":null,"applyCode":"","handleType":true,"applyUserId":"1","applyUserName":null,"applyTime":"2018-12-12 14:03:53","apprStatus":0,"provinceId":null,"cityId":null,"county":null,"town":null,"addrDetail":"遵义市汇川区上海路街道办事处","userName":"管理员","phone":"18819204550","idcard":"52212123452353254325"},{"id":"44","flowId":"0","flowName":null,"applyCode":"","handleType":true,"applyUserId":"1","applyUserName":null,"applyTime":"2018-12-12 14:03:46","apprStatus":0,"provinceId":null,"cityId":null,"county":null,"town":null,"addrDetail":"遵义市汇川区上海路街道办事处","userName":"管理员","phone":"18819204550","idcard":"52212123452353254325"},{"id":"550","flowId":"0","flowName":null,"applyCode":"","handleType":true,"applyUserId":"1","applyUserName":null,"applyTime":"2018-12-12 12:55:40","apprStatus":0,"provinceId":null,"cityId":null,"county":null,"town":null,"addrDetail":"遵义市汇川区上海路街道办事处","userName":"管理员","phone":"18819204550","idcard":"52212123452353254325"}]
     * prePage : 0
     * nextPage : 0
     * isFirstPage : true
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : [1]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * firstPage : 1
     * lastPage : 1
     */

    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private String total;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;

    private List<T> list;

    private List<Integer> navigatepageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
