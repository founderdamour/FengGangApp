package com.zkhy.community

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v4.view.ViewPager
import android.util.Log
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.NetUtil
import com.sinothk.comm.utils.StatusBarUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.helper.image.compress.ImageCompress
import com.sinothk.map.amap.location.MapLocationHelper
import com.sinothk.permission.PermissionManager
import com.sinothk.permission.PermissionResultListener
import com.sinothk.permission.lib.PermissionsUtil
import com.sinothk.tab.weiXin.WxTabMenuMainAdapter
import com.zkhy.comm.base.MainBaseActivity
import com.zkhy.comm.util.NoticeUtil
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.bean.LocationEntity
import com.zkhy.community.model.bean.MsgsCenterInfo
import com.zkhy.community.model.bean.NoticeMsgEntity
import com.zkhy.community.model.bean.NoticeTipEntity
import com.zkhy.community.model.cache.DataCache
import com.zkhy.community.model.cache.KeyWord
import com.zkhy.community.presenter.MainPresenter
import com.zkhy.community.view.CommunityFragment
import com.zkhy.community.view.HomeFragment
import com.zkhy.community.view.MineFragment
import com.zkhy.community.view.NearbyFragment
import com.zkhy.community.view.comm.LoginActivity
import com.zkhy.community.view.comm.NoticeMsgDetailActivity
import com.zkhy.community.view.comm.NoticeMsgListActivity
import com.zkhy.community.view.mine.UserInfoSimpleEditActivity
import com.zkhy.community.view.photos.PhotosCreatorActivity
import com.zkhy.library.mvp.AndroidExt2View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

open class MainActivity : MainBaseActivity(), AndroidExt2View {

    companion object {
        var FROM_TYPE = "whereFromType"
        var FROM_WELCOME = 0
        var FROM_LOGIN = 1

//        var fromType = FROM_WELCOME

        fun getRoles(): List<String> {
            val rolesList: List<String>? = DataCache.getUserRoles()
            return if (rolesList != null && rolesList.isNotEmpty()) {
                rolesList
            } else {
                ArrayList()
            }
        }
    }

    var presenter: MainPresenter? = null

    override fun onResume() {
        super.onResume()
        // 检测版本
        checkNewVersion(this, false)

        // 获取未读消息
        doTimerEvent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.transparencyBar(this)
//        fromType = intent.getIntExtra(FROM_TYPE, 0)

        requestAppPermission()

        presenter = MainPresenter(this)
        initDictionary()
        initTab()
        checkUserInfo()
    }

    private fun initTab() {
//        chatListFragment = ChatListFragment()
//        contactsListFragment = ContactsMainFragment()
//        myMainFragment = MyMainFragment()

        val fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(CommunityFragment())
        fragments.add(NearbyFragment())
        fragments.add(MineFragment())

        val mainAdapter = WxTabMenuMainAdapter(supportFragmentManager, alphaIndicator, fragments)
        mViewPager!!.adapter = mainAdapter
        mViewPager!!.offscreenPageLimit = 4

        // 监听滑动，动态显示TitleBar内容
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })

        alphaIndicator.setViewPager(mViewPager)

//        alphaIndicator.getTabView(0).showNumber(1)

        tabView2.setOnClickListener {
            photosCreate()
        }
    }

    private fun checkUserInfo() { // 验证信息
        if (!DataCache.isAutoLogin()) {
            // 游客跳过
            return
        }

        if (!NetUtil.isConnected(this)) {
            showTip("当前网络不可用")
            return
        }
        presenter?.checkUserInfo(DataCache.getUserId())
    }

    override fun loadingDismiss() {
    }

    override fun loadingShow() {
    }

    override fun showTip(msg: String?) {
    }

    override fun loadComplete(resultData: Any?) {
        val result = resultData as BaseData<String>
        if (result.errcode == 0) {
            if ("false" == result.data) {
                DataCache.setNeedTipInputUserInfo(true)

                CommonDialog.Builder(this)
                    .setTitle("重要提示")
                    .setCanceledOnTouchOutside(false)
                    .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                    .setPositiveButton("确定") {
                        IntentUtil.openActivity(this@MainActivity, UserInfoSimpleEditActivity::class.java)
                            .putIntExtra("fromType", 0)
                            .start()
                    }.setNegativeButton("取消") {
                    }.show()
            } else {
                DataCache.setNeedTipInputUserInfo(false)
            }
        }
    }

    private fun initDictionary() {
        val typeCode: Array<String> = arrayOf(
            "wm_dj_meeting_type", // 三会一课会议类型
            "orgcode", // 单位
            "wumin_convenient_certificate_type", // 证件类型
            "wumin_convenient_handling_objects" //  办理对象
            , "NATION" // 民族
            , "party_member_education" // 学历
        )
        presenter?.loadDictionary(typeCode)
    }

    override fun load2Complete(resultData: Any?) {

    }

    private fun requestAppPermission() {
        val tip = PermissionsUtil.TipInfo("授权提示", "获取必须的手机权限不全！", "放弃使用", "授权")

        if (PermissionManager.haveAllPermission(this, permissions)) {
        } else {
            PermissionManager.requestAllPermission(this, object : PermissionResultListener {
                override fun permissionFail(p0: Array<out String>?) {
                    ToastUtil.show("放弃使用")
                    finish()
                }

                override fun permissionSuccess(p0: Array<out String>?) {
                    requestAppPermission()
                }
            }, permissions, true, tip)
        }
    }

    private val permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.CAMERA,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS
    )

    /**
     * =====================================  随手拍部分
     */
    private fun photosCreate() {
        if (!DataCache.isAutoLogin()) {
            CommonDialog.Builder(this)
                .setTitle("登录提示")
                .setCanceledOnTouchOutside(false)
                .setMessage(resources.getString(R.string.login_tip))
                .setPositiveButton("登录") {
                    // 前往登录
                    IntentUtil.openActivity(this, LoginActivity::class.java).start()
                }.setNegativeButton("取消") {
                }.show()
            return
        }

        val needTip: Boolean = DataCache.getNeedTipInputUserInfo()
        if (needTip) {
            CommonDialog.Builder(this)
                .setTitle("重要提示")
                .setCanceledOnTouchOutside(false)
                .setMessage("\n继续使用应用前，需要完善\n\n用户的基本信息...\n")
                .setPositiveButton("确定") {

                    IntentUtil.openActivity(this, UserInfoSimpleEditActivity::class.java)
                        .putIntExtra("fromType", 0)
                        .start()

                }.setNegativeButton("取消") {
                }.show()
        } else {
            openSysCamera()
        }
    }

    /**
     * 打开系统相机
     */
    internal var file: File? = null
    private var imgUriOri: Uri? = null

    private fun openSysCamera() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            file = createOriImageFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (file != null) {
            imgUriOri = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Uri.fromFile(file)
            } else {
                FileProvider.getUriForFile(this, "$packageName.fileProvider", file!!)
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri)
            startActivityForResult(cameraIntent, 101)
        }
    }

    /**
     * 创建原图像保存的文件
     *
     * @return
     * @throws IOException
     */
    private var imgPathOri = ""

    @Throws(IOException::class)
    private fun createOriImageFile(): File {
        val imgNameOri = "HomePic_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val pictureDirOri = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).absolutePath + "/OriPicture")
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs()
        }
        val image = File.createTempFile(
            imgNameOri, /* prefix */
            ".jpg", /* suffix */
            pictureDirOri       /* directory */
        )
        imgPathOri = image.absolutePath
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {

            ImageCompress.execute(imgPathOri) { obj ->
                if (obj != null) {
                    val newPath: String = obj as String
                    IntentUtil.openActivity(this@MainActivity, PhotosCreatorActivity::class.java)
                        .putStringExtra("no1ImgPath", newPath)
                        .start()
                } else {
                    Log.d("ImageCompress", "压缩图片为空(obj != null)")
                }
            }
        }
    }


    /**
     * ====================================================== 位置上报 ==================================================
     */
    override fun locationCheck() {
        if (getRoles().contains(KeyWord.ROLE_SHOT_WORK_MAN)) {
            MapLocationHelper.with(this).locateContinue(10 * 60) { locEntity ->
                run {
                    if (locEntity != null) {
                        val locInfo = LocationEntity()

                        locInfo.lat = locEntity.latitude
                        locInfo.lon = locEntity.longitude
                        locInfo.addr = locEntity.address

                        presenter?.uploadLocationInfo(locInfo)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MapLocationHelper.with(this).locateStop()
    }

    /**
     * =====    定时任务    ============================================================================================
     */
    override fun doTimerEvent() {
        val userId: String = DataCache.getUserId()
        presenter?.loadSysNotice(this, userId)
    }

    fun showMsgTip(noticeTip: NoticeTipEntity?) {

        if (noticeTip == null) {
            return
        }

        // 未读消息
        if (noticeTip.noReadNum > 0) {
            HomeFragment.instance?.showNoticeTip(true)
        } else {
            HomeFragment.instance?.showNoticeTip(false)
        }

        // 新消息
        if (noticeTip.msgsCenterInfos != null && noticeTip.msgsCenterInfos.size > 0) {

            for (index: Int in noticeTip.msgsCenterInfos.indices) {
                val centerInfo: NoticeMsgEntity = noticeTip.msgsCenterInfos[index]
                NoticeUtil.showTip(
                    this,
                    centerInfo.title,
                    centerInfo.content,
                    R.mipmap.app_icon,
                    NoticeMsgDetailActivity::class.java, index, centerInfo
                )
            }
        }


    }
}
