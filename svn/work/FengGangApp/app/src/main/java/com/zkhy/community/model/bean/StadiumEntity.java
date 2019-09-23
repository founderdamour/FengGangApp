package com.zkhy.community.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  创建:  梁玉涛 2018/12/14 on 14:51
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class StadiumEntity {

    /**
     * id : 523145654248144929
     * name : 测试场馆1
     * addr : 地址1
     * lon : 0
     * lat : 0
     * gallery : 100
     * freeStatus : true
     * chargeDetail : 地址1
     * palceStatus : 0 场所状态(1:正常,异常)
     * useStatus : 0
     * cpSprotsList : ["wm_hap_pro_ping","wm_hap_pro_bad","wm_hap_pro_foot"]
     */

    private String id;
    private String name;
    private String addr;
    private int lon;
    private int lat;
    private int gallery;
    private boolean freeStatus;
    private String chargeDetail;
    private int palceStatus;
    private int useStatus;
    private ArrayList<Dict> cpSprotsList;

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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public boolean isFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(boolean freeStatus) {
        this.freeStatus = freeStatus;
    }

    public String getChargeDetail() {
        return chargeDetail;
    }

    public void setChargeDetail(String chargeDetail) {
        this.chargeDetail = chargeDetail;
    }

    public int getPalceStatus() {
        return palceStatus;
    }

    public void setPalceStatus(int palceStatus) {
        this.palceStatus = palceStatus;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public ArrayList<Dict> getCpSprotsList() {
        return cpSprotsList;
    }

    public void setCpSprotsList(ArrayList<Dict> cpSprotsList) {
        this.cpSprotsList = cpSprotsList;
    }

    public class Dict {

        /**
         * name : 乒乓球场
         * code : wm_hap_pro_ping
         */

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
