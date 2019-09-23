package com.zkhy.fenggang.community.model.bean;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/14 on 17:48
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class NearbyActiveEntity {

    /**
     * id : 523188181164621857
     * name : string1
     * startTime : 2018-12-14 09:22:16
     * endTime : 2018-12-14 09:22:16
     * content : string1
     * addr : string1
     * organizer : string1
     * aroundImg
     */
//    addr	string
//    活动地点
//
//    content	string
//    活动内容
//
//    endTime	string($date-time)
//    活动结束时间
//
//    id	integer($int64)
//    ID
//    name	string
//    活动名称
//
//    organizer	string
//    举办单位
//
//    startTime	string($date-time)
//    活动开始时间

    private String id;
    private String name;
    private String startTime;
    private String endTime;
    private String content;
    private String addr;
    private String organizer;
    private String aroundImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getAroundImg() {
        return aroundImg;
    }

    public void setAroundImg(String aroundImg) {
        this.aroundImg = aroundImg;
    }
}
