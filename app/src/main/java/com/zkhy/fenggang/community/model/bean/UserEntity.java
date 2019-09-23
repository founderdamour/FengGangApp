package com.zkhy.fenggang.community.model.bean;

public class UserEntity {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    /**
//     * 打开系统相机
//     */
//    File file = null;
//    Uri imgUriOri = null;
//
//    private void openSysCamera() {
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
////                new File(Environment.getExternalStorageDirectory(), imgName)));
////        File file = new File(Environment.getExternalStorageDirectory(), imgName);
//        try {
//            file = createOriImageFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (file != null) {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//                imgUriOri = Uri.fromFile(file);
//            } else {
//                imgUriOri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
//            }
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
//            startActivityForResult(cameraIntent, CAMERA_RESULT_CODE);
//        }
//    }
//
//    /**
//     * 创建原图像保存的文件
//     *
//     * @return
//     * @throws IOException
//     */
//    String imgPathOri = "";
//    private File createOriImageFile() throws IOException {
//        String imgNameOri = "HomePic_" + new SimpleDateFormat(
//                "yyyyMMdd_HHmmss").format(new Date());
//        File pictureDirOri = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
//        if (!pictureDirOri.exists()) {
//            pictureDirOri.mkdirs();
//        }
//        File image = File.createTempFile(
//                imgNameOri,         /* prefix */
//                ".jpg",             /* suffix */
//                pictureDirOri       /* directory */
//        );
//        imgPathOri = image.getAbsolutePath();
//        return image;
//    }
}
