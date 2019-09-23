package com.zkhy.community.view.main.bm

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.widget.GridLayoutManager
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import com.jiangyy.easydialog.CommonDialog
import com.sinothk.comm.utils.IntentUtil
import com.sinothk.comm.utils.StringUtil
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.helper.image.compress.ImageCompress
import com.sinothk.image.selector.PhotoPreviewActivity
import com.sinothk.rxretrofit.RxRetrofit
import com.sinothk.rxretrofit.param.RetrofitParam
import com.zkhy.community.R
import com.zkhy.community.model.api.AllApi
import com.zkhy.community.model.api.BaseData
import com.zkhy.community.model.api.ServerConfig
import com.zkhy.community.model.bean.AttachmentEntity
import com.zkhy.community.model.bean.BizType
import com.zkhy.community.model.bean.BmFileEntity
import com.zkhy.community.model.bean.ImgSelectEntity
import com.zkhy.community.view.photos.adapter.AppImageSelectedShowAdapter
import com.zkhy.library.base.activity.TitleBarBaseActivity
import kotlinx.android.synthetic.main.activity_file_upload_comm.*
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

open class FileUploadCommActivity : TitleBarBaseActivity() {

    private var fileEntity: BmFileEntity? = null
    var imgSelectList: ArrayList<ImgSelectEntity> = ArrayList()

    override fun getLayout(): Int {
        return R.layout.activity_file_upload_comm
    }

    private var mAlbumSelectedShowAdapter: AppImageSelectedShowAdapter? = null

    private fun closeActivity() {
        if (imgSelectList.size != 0) {
            CommonDialog.Builder(this@FileUploadCommActivity)
                .setTitle("操作提醒")
                .setCanceledOnTouchOutside(false)
                .setMessage("资料未保存，是否保存？")
                .setPositiveButton("保存") {
                    finish()
                }.setNegativeButton("丢弃") {
                    delAllFiles()
                }.show()
        } else {
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_BACK) {
            closeActivity()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fileEntity = intent.getSerializableExtra("fileEntity") as BmFileEntity?
        if (fileEntity == null) {
            ToastUtil.show("传入参数有误")
            finish()
            return
        }

        setTitleBar(StringUtil.getNotNullValue(fileEntity!!.titleStr, "上传资料"), {
            closeActivity()
        }, "保存", {
            finish()
        })

        initView()
    }

    override fun finish() {
        val bundle = Bundle()
        bundle.putString("id", fileEntity!!.bizId)
        bundle.putSerializable("fileList", imgSelectList)
        intent.putExtras(bundle)
        setResult(200, intent)

        super.finish()
    }

    private fun initView() {
        tipTv.text = StringUtil.getNotNullValue(fileEntity!!.tip)

        // 图片选择
        mAlbumSelectedShowAdapter = AppImageSelectedShowAdapter(this, 8)

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAlbumSelectedShowAdapter

        mAlbumSelectedShowAdapter!!.setOnItemClickListener { _, position ->
            if (imgSelectList.size == position) {
                openSysCamera()
            } else {
                //图片展示界面
                val imgList: ArrayList<String> = ArrayList()
                for (imgEntity: ImgSelectEntity in imgSelectList) {
                    imgList.add(imgEntity.filePath)
                }
                PhotoPreviewActivity.start(this@FileUploadCommActivity, position, imgList)
            }
        }

        mAlbumSelectedShowAdapter!!.setItemDelClick { position ->
            dialog(position)
        }

        if (fileEntity!!.fileList != null && fileEntity!!.fileList.size > 0) {
            imgSelectList = fileEntity!!.fileList
            mAlbumSelectedShowAdapter?.setData(imgSelectList)
        }
    }

    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected fun dialog(position: Int) {
        CommonDialog.Builder(this)
            .setTitle("提示")
            .setMessage("确认移除已添加的图片吗？")
            .setPositiveButton("确认") {
                if (mAlbumSelectedShowAdapter!!.itemCount > 0) {

                    // 删除服务器文件
                    val ids: Array<String> = arrayOf(imgSelectList[position].id)
                    delFileByIds(ids, false)

                    // 移除展示数据
                    imgSelectList.removeAt(position)

                    // 刷新界面
                    mAlbumSelectedShowAdapter?.setData(imgSelectList)
                    mAlbumSelectedShowAdapter?.notifyDataSetChanged()
                }
            }.setNegativeButton("取消") {

            }.show()
    }

    // ===============================================================================

    var uploadSuccess = true

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
            startActivityForResult(cameraIntent, 11)
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

        if (requestCode == 11 && resultCode == -1) {

            ImageCompress.execute(imgPathOri) { obj ->
                if (obj != null) {

                    val newPath: String = obj as String
                    val tempFile = File(newPath)
                    if (tempFile.length() > 0) {
                        upLoad(fileEntity!!.bizId, fileEntity!!.bizType, newPath)
                    } else {
                        ToastUtil.show("压缩图片为空")
                    }
                } else {
                    ToastUtil.show("图片压缩失败")
                }
            }
        } else {
            ToastUtil.show("已取消操作")
        }
    }

    private fun upLoad(bId: String, bizType: String, newPath: String) {

        // 传单文件文件和键值对
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap()).create(AllApi::class.java)
            .uploadFile(bId, bizType, RetrofitParam.createFileParam("file", File(newPath)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<AttachmentEntity>>() {

                override fun onStart() {
                    super.onStart()
                    LoadingDialog.show(this@FileUploadCommActivity, "正在提交")
                }

                override fun onNext(result: BaseData<AttachmentEntity>?) {
                    if (result != null) {
                        if (result.errcode == 0) {
                            if (result.data != null) {
                                uploadSuccess = true

                                //
                                fileEntity!!.bizId = result.data.bizId

                                //
                                val imgSelectEntity: ImgSelectEntity? = ImgSelectEntity()
                                imgSelectEntity!!.id = result.data.id.toString()
                                imgSelectEntity.filePath = newPath
                                imgSelectEntity.url = result.data.url

                                imgSelectEntity.bizId = result.data.bizId

                                imgSelectList.add(imgSelectEntity)

                                refreshImgView()
                                return
                            } else {
                                ToastUtil.show("无文件信息")
                            }
                        } else {
                            ToastUtil.show(result.errmsg)
                        }
                    } else {
                        ToastUtil.show("提交失败")
                    }
                    uploadSuccess = true
                }

                override fun onCompleted() {
                    LoadingDialog.hidden()
                }

                override fun onError(e: Throwable?) {
                    LoadingDialog.hidden()
                    uploadSuccess = false
                    if (e != null) {
                        ToastUtil.show("文件上传失败")
                    }
                }
            })
    }

    private fun refreshImgView() {
        mAlbumSelectedShowAdapter?.setData(imgSelectList)
    }

    private fun delFileByIds(ids: Array<String>, isNeedFinish: Boolean) {

        // 传单文件文件和键值对
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap()).create(AllApi::class.java)
            .delFileSysById(ids)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onStart() {
                    super.onStart()
                    LoadingDialog.show(this@FileUploadCommActivity, "正在删除")
                }

                override fun onNext(result: BaseData<Boolean>?) {
                    if (result != null) {
                        if (result.errcode == 0) {
                            if (result.data != null) {
                                if (isNeedFinish) {
                                    finish()
                                } else {
                                    return
                                }
                            } else {
                                ToastUtil.show(result.errmsg)
                            }
                        } else {
                            ToastUtil.show(result.errmsg)
                        }
                    } else {
                        ToastUtil.show("删除失败")
                    }
                }

                override fun onCompleted() {
                    LoadingDialog.hidden()
                }

                override fun onError(e: Throwable?) {
                    LoadingDialog.hidden()
                    if (e != null) {
                        ToastUtil.show("文件删除失败")
                    }
                }
            })
    }

    /**
     * 删除所有文件
     */
    private fun delAllFiles() {
        val ids: Array<String> = Array(imgSelectList.size) { "" }

        for (index: Int in imgSelectList.indices) {
            ids[index] = imgSelectList[index].id
        }

        // 清空所有数据
        imgSelectList.clear()

        // 删除文件
        delFileByIds(ids, true)
    }
}
