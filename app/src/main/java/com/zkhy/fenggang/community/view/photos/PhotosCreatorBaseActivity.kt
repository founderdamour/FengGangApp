package com.zkhy.fenggang.community.view.photos

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.sinothk.comm.utils.ToastUtil
import com.sinothk.dialog.loading.LoadingDialog
import com.sinothk.helper.image.compress.ImageCompress
import com.sinothk.rxretrofit.RxRetrofit
import com.sinothk.rxretrofit.param.RetrofitParam
import com.zkhy.fenggang.community.model.api.AllApi
import com.zkhy.fenggang.community.model.api.BaseData
import com.zkhy.fenggang.community.model.api.ServerConfig
import com.zkhy.fenggang.community.model.bean.AttachmentEntity
import com.zkhy.fenggang.community.model.bean.ImgSelectEntity
import com.zkhy.library.base.activity.TitleBarBaseActivity
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *  创建:  梁玉涛 2019/3/24 on 15:55
 *  项目:  WuMinAndroid
 *  描述:
 *  更新:
 * <pre>
 */
abstract class PhotosCreatorBaseActivity : TitleBarBaseActivity() {

    var bizId = ""
    var bizType = ""
    var uploadSuccess = true

    /**
     * 打开系统相机
     */
    internal var file: File? = null
    private var imgUriOri: Uri? = null

    protected fun openSysCamera(bType: String) {
        bizType = bType

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

    var imgSelectList: ArrayList<ImgSelectEntity> = ArrayList()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 11 && resultCode == -1) {

            ImageCompress.execute(imgPathOri) { obj ->
                if (obj != null) {

                    val newPath: String = obj as String
                    val tempFile = File(newPath)
                    if (tempFile.length() > 0) {
//                        img.setImageBitmap(BitmapFactory.decodeFile(tempFile.path))
                        upLoad(bizId, bizType, newPath)
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

    abstract fun refreshImgView()

    protected fun upLoad(bId: String, bizType: String, newPath: String) {

        // 传单文件文件和键值对
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap()).create(AllApi::class.java)
            .uploadFile(bId, bizType, RetrofitParam.createFileParam("file", File(newPath)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<AttachmentEntity>>() {

                override fun onStart() {
                    super.onStart()
                    LoadingDialog.show(this@PhotosCreatorBaseActivity, "正在提交")
                }

                override fun onNext(result: BaseData<AttachmentEntity>?) {
                    if (result != null) {
                        if (result.errcode == 0) {
                            if (result.data != null) {
                                uploadSuccess = true
                                bizId = result.data.bizId

                                val imgSelectEntity: ImgSelectEntity? = ImgSelectEntity()
                                imgSelectEntity!!.id = result.data.id.toString()
                                imgSelectEntity.filePath = newPath
                                imgSelectEntity.url = result.data.url
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

    protected fun delFileById(id: String) {

        var ids: Array<String> = arrayOf(id)

        // 传单文件文件和键值对
        RxRetrofit.init(ServerConfig.baseUrl, ServerConfig.getHeaderMap()).create(AllApi::class.java)
            .delFileSysById(ids)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<BaseData<Boolean>>() {

                override fun onStart() {
                    super.onStart()
                    LoadingDialog.show(this@PhotosCreatorBaseActivity, "正在删除")
                }

                override fun onNext(result: BaseData<Boolean>?) {
                    if (result != null) {
                        if (result.errcode == 0) {
                            if (result.data != null) {
                                return
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
}