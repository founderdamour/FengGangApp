package com.zkhy.community.model.bean;

import java.util.Date;

/**
 * 煤矿检测系统
 */
public class MkMonitoringSystem2Entity {

    private String Kname;

    private String Kbh;

    private String timer;

    private String zdflag;

    public String getKname() {
        return Kname;
    }

    public void setKname(String kname) {
        Kname = kname;
    }

    public String getKbh() {
        return Kbh;
    }

    public void setKbh(String kbh) {
        Kbh = kbh;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getZdflag() {
        return zdflag;
    }

    public void setZdflag(String zdflag) {
        this.zdflag = zdflag;
    }
}
