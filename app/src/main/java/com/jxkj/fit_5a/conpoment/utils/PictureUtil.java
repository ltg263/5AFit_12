package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PictureUtil {

    /**
     * 15      * 把bitmap转换成String
     * 16      *
     * 17      * @param filePath
     * 18      * @return
     * 19
     */


    public static String bitmapToString(String filePath) {
        Bitmap bm = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        int x = b.length;
        System.out.println("" + x);
        return Base64.encodeToString(b, Base64.DEFAULT);


    }

    /**
     * 32      * 计算图片的缩放值
     * 33      *
     * 34      * @param options
     * 35      * @param reqWidth
     * 36      * @param reqHeight
     * 37      * @return
     * 38
     */


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

        }

        return inSampleSize;

    }

    /**
     * 65      * 根据路径获得突破并压缩返回bitmap用于显示
     * 66      *
     * 67      * @param imagesrc
     * 68      * @return
     * 69
     */


    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);

    }

    /**
     * 85      根据路径删除图片
     * 86      *
     * 87      * @param path
     * 88
     */


    public static void deleteTempFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();

        }

    }

    /**
     * 97      * 添加到图库
     * 98
     */


    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);

    }

    /**
     * 109      * 获取保存图片的目录
     * 110      *
     * 111      * @return
     * 112
     */


    public static File getAlbumDir() {
        File dir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                getAlbumName());
        if (!dir.exists()) {
            dir.mkdirs();

        }
        return dir;

    }

    /**
     * 获取保存 隐患检查的图片文件夹名称
     *
     * @return
     */


    public static String getAlbumName() {
        return "sheguantong";

    }

    /**
     * 压缩图片到目标大小以下
     *
     * @param file
     * @param targetSize
     */
    public static File compressBmpFileToTargetSize(File file, long targetSize) {
        Log.d("ltg_263", String.format("compressBmpFileToTargetSize start file.length():%d", file.length()));
        if (file.length() > targetSize) {
            // 每次宽高各缩小一半
            int ratio = 2;
            // 获取图片原始宽高
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            int targetWidth = options.outWidth / ratio;
            int targetHeight = options.outHeight / ratio;

            // 压缩图片到对应尺寸
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int quality = 100;
            Bitmap result = generateScaledBmp(bitmap, targetWidth, targetHeight, baos, quality);

            // 计数保护，防止次数太多太耗时。
            int count = 0;
            while (baos.size() > targetSize && count <= 10) {
                targetWidth /= ratio;
                targetHeight /= ratio;
                count++;

                // 重置，不然会累加
                baos.reset();
                result = generateScaledBmp(result, targetWidth, targetHeight, baos, quality);
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d("ltg_263", String.format("compressBmpFileToTargetSize end file.length():%d", file.length()));
        return file;
    }

    /**
     * 图片缩小一半
     *
     * @param srcBmp
     * @param targetWidth
     * @param targetHeight
     * @param baos
     * @param quality
     * @return
     */
    private static Bitmap generateScaledBmp(Bitmap srcBmp, int targetWidth, int targetHeight, ByteArrayOutputStream baos, int quality) {
        Bitmap result = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, result.getWidth(), result.getHeight());
        canvas.drawBitmap(srcBmp, null, rect, null);
        if (!srcBmp.isRecycled()) {
            srcBmp.recycle();
        }
        result.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        return result;
    }

    /**
     * 获取视频文件截图
     *
     * @param path 视频文件的路径
     * @return Bitmap 返回获取的Bitmap
     */

    public static String getVideoThumb(Context mContext,String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(mContext, Uri.parse(path));
//        media.setDataSource(path);
        String pathF = saveBitmap(media.getFrameAtTime(),100);
        return pathF;
    }
    /**
     * 保存bitmap到本地
     *
     * @param bitmap
     * @return
     */
    public static String saveBitmap(Bitmap bitmap,int position) {
        String savePath;
        File filePic;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            savePath = "/sdcard/dskqxt/pic/";
        } else {
            Log.d("xxx", "saveBitmap: 1return");
            return null;
        }
        try {
            filePic = new File(savePath + position + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("xxx", "saveBitmap: 2return");
            return null;
        }
        Log.d("xxx", "saveBitmap: " + filePic.getAbsolutePath());
        return filePic.getAbsolutePath();
    }
}