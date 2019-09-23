package com.zkhy.community.comm.plugin.version;

/**
 * <pre>
 *  创建:  梁玉涛 2019/1/18 on 11:31
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
public class VersionEntity {
    /**
     * 主键
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 版本名称
     *
     * @mbggenerated
     */
    private String verName;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private Integer verNumber;

    /**
     * 状态(1,未发布,2已发布)
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * 下载二维码图片路径
     *
     * @mbggenerated
     */
    private String downQrCode;

    /**
     * 是否强制更新(1不强制,2强制)
     *
     * @mbggenerated
     */
    private Integer pwUpdate;

    /**
     * 发布日期
     *
     * @mbggenerated
     */
    private String publishTime;

    /**
     * 文件id
     *
     * @mbggenerated
     */
    private String apkFileId;

    /**
     * 文件名称
     *
     * @mbggenerated
     */
    private String apkFileName;

    /**
     * 文件路径
     *
     * @mbggenerated
     */
    private String apkFilePath;

    /**
     * 文件大小
     *
     * @mbggenerated
     */
    private String apkFileSize;

    /**
     * 升级内容
     *
     * @mbggenerated
     */
    private String updateContent;

    /**
     * 升级数量
     *
     * @mbggenerated
     */
    private Integer updateNum;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private Long createUser;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private Long updateUser;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 版本名称
     *
     * @mbggenerated
     */
    public String getVerName() {
        return verName;
    }

    /**
     * 版本名称
     *
     * @mbggenerated
     */
    public void setVerName(String verName) {
        this.verName = verName == null ? null : verName.trim();
    }

    /**
     * 版本号
     *
     * @mbggenerated
     */
    public Integer getVerNumber() {
        return verNumber;
    }

    /**
     * 版本号
     *
     * @mbggenerated
     */
    public void setVerNumber(Integer verNumber) {
        this.verNumber = verNumber;
    }

    /**
     * 状态(1,未发布,2已发布)
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * 状态(1,未发布,2已发布)
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 下载二维码图片路径
     *
     * @mbggenerated
     */
    public String getDownQrCode() {
        return downQrCode;
    }

    /**
     * 下载二维码图片路径
     *
     * @mbggenerated
     */
    public void setDownQrCode(String downQrCode) {
        this.downQrCode = downQrCode == null ? null : downQrCode.trim();
    }

    /**
     * 是否强制更新(1不强制,2强制)
     *
     * @mbggenerated
     */
    public Integer getPwUpdate() {
        return pwUpdate;
    }

    /**
     * 是否强制更新(1不强制,2强制)
     *
     * @mbggenerated
     */
    public void setPwUpdate(Integer pwUpdate) {
        this.pwUpdate = pwUpdate;
    }

    /**
     * 发布日期
     *
     * @mbggenerated
     */
    public String getPublishTime() {
        return publishTime;
    }

    /**
     * 发布日期
     *
     * @mbggenerated
     */
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 文件id
     *
     * @mbggenerated
     */
    public String getApkFileId() {
        return apkFileId;
    }

    /**
     * 文件id
     *
     * @mbggenerated
     */
    public void setApkFileId(String apkFileId) {
        this.apkFileId = apkFileId == null ? null : apkFileId.trim();
    }

    /**
     * 文件名称
     *
     * @mbggenerated
     */
    public String getApkFileName() {
        return apkFileName;
    }

    /**
     * 文件名称
     *
     * @mbggenerated
     */
    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName == null ? null : apkFileName.trim();
    }

    /**
     * 文件路径
     *
     * @mbggenerated
     */
    public String getApkFilePath() {
        return apkFilePath;
    }

    /**
     * 文件路径
     *
     * @mbggenerated
     */
    public void setApkFilePath(String apkFilePath) {
        this.apkFilePath = apkFilePath == null ? null : apkFilePath.trim();
    }

    /**
     * 文件大小
     *
     * @mbggenerated
     */
    public String getApkFileSize() {
        return apkFileSize;
    }

    /**
     * 文件大小
     *
     * @mbggenerated
     */
    public void setApkFileSize(String apkFileSize) {
        this.apkFileSize = apkFileSize == null ? null : apkFileSize.trim();
    }

    /**
     * 升级内容
     *
     * @mbggenerated
     */
    public String getUpdateContent() {
        return updateContent;
    }

    /**
     * 升级内容
     *
     * @mbggenerated
     */
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
    }

    /**
     * 升级数量
     *
     * @mbggenerated
     */
    public Integer getUpdateNum() {
        return updateNum;
    }

    /**
     * 升级数量
     *
     * @mbggenerated
     */
    public void setUpdateNum(Integer updateNum) {
        this.updateNum = updateNum;
    }

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     *
     * @mbggenerated
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     *
     * @mbggenerated
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     *
     * @mbggenerated
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     *
     * @mbggenerated
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getAppId() {
        return null;
    }

    public void setAppId(String appId) {

    }
}
