package com.jxkj.fit_5a.conpoment.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/4.
 */

public class StringUtil {

    public static String replaceNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static String replaceNullToZero(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        return str;
    }

    public static String replaceNullToOne(String str) {
        if (TextUtils.isEmpty(str)) {
            return "1";
        }
        return str;
    }

    public static boolean checkIsNotEmpty(TextView edt) {
        if (ObjectUtils.isEmpty(edt.getText())) {
            return false;
        }
        if (TextUtils.isEmpty(edt.getText().toString())) {
            return false;
        }
        return true;
    }

    public static String format(String format, Object v1, Object v2) {
        return String.format(format, v1, v2);
    }

    public static String replaceHtmlImgToAbsolutelyUrl(String baseUrl, String html) {
        Pattern pattern = Pattern.compile("src\\s*=\\s*\"(.+?)\"");
        Matcher matcher = pattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String str = matcher.group(0);
            if (!str.contains("http://")) {
                matcher.appendReplacement(sb, str.substring(0, 5) + baseUrl + str.substring(6));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String replacrPhoneNum2Star(String phoneNum) {
        if (ObjectUtils.isEmpty(phoneNum)) {
            return "";
        }
        if (phoneNum.length() < 7) {
            return phoneNum;
        }
        return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, phoneNum.length());
    }

    /**
     * 银行卡号中间每四位插入一个空格
     *
     * @param str
     * @return
     */
    public static String insertSpacePerFour(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() < 16) {
            return str;
        }
        return str.substring(0, 4) + " **** ****  " + str.substring(12, 16) + " " + str.substring(16, str.length());
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return ("".equals(str) || "null".equals(str) || str == null);
    }

    /**
     * 替换非utf8字符，慎用  会出现死循环
     *
     * @param text
     * @return
     */
    public static String filterOffUtf8Mb4(String text) {
        try {
            byte[] bytes = text.getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;
                if ((b ^ 0xC0) >> 4 == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if ((b ^ 0xE0) >> 4 == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if ((b ^ 0xF0) >> 4 == 0) {
                    i += 4;
                }
            }
            buffer.flip();
            return new String(buffer.array(), "utf-8");
        } catch (Exception e) {
            Log.e("Exception", e.getMessage().toString());
        }
        return text;
    }

    public static boolean isUTF8(String key) {
        try {
            key.getBytes("utf-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return FALSE，包含图片
     */
    public static boolean containsEmoji(String source) {
        if (source.equals("")) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;//如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {
                //这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }

    }

    /**
     * 监听输入框输的变化
     */
    public static void etSearchChangedListener(final EditText et, final View btn, final EtChange etChange) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    etChange.etYes();
                }
                if (et.getText().toString().trim().length() == 0) {
                    if (btn != null) {
                        btn.setSelected(false);
                    }
                    etChange.etNo();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static String getTimeToYMD(long seconds, String layout) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat(layout);
        return sdf.format(d).toString();
    }

    /**
     * 描述: 将字符串转成毫秒数 格式年月日
     */
    public static long getMsToTime(String time, String layout) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(layout).parse(time));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public abstract static class EtChange {
        //有内容
        public abstract void etYes();

        //无内容
        public void etNo() {
        }
    }

    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext) {
        try {
            if (!htmltext.contains("&nbsp;") && htmltext.contains("&nbsp")) {
                htmltext = htmltext.replaceAll("&nbsp", "&nbsp;");
            }
            Document doc = Jsoup.parse(htmltext);

            Elements elementsAll = doc.getAllElements();
            for (Element span : elementsAll) {
//                Elements p = span.getElementsByTag("p");
////                p.attr("style","font-size:16px;width:100%;margin:1rem 0px");
////                Elements h1 = span.getElementsByTag("h1");
                if (!span.toString().contains("<html") &&
                        !span.toString().contains("<head") &&
                        !span.toString().contains("<body") &&
                        !span.toString().contains("<div")) {//.p:last-child{margin-bottom!important}
                    span.attr("style", "font-size:16px;width:100%;margin-bottom:1rem;line-height:26px;letter-spacing:1px;");
                }
            }
            Elements head = doc.getElementsByTag("head");
            head.get(0).html("<style>*{border:0;margin:0;padding:0;};p:last-child{margin-bottom:0px;!important}</style>");
            Elements elements = doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
                String str = element.attr("src");
                if (str.contains("image/png;base64")) {
                    element.attr("src", "");
                }
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }

    /**
     * 得到网页中图片的地址
     */
    public static ArrayList<String> getImgStr(String htmlStr) {
        ArrayList<String> pics = new ArrayList<>();
        String img = "";
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public static int getTotalPage(int totalCount, int pageSize) {
        return (totalCount + pageSize - 1) / pageSize;
    }

    public static List<Double> getAverageV(double maxV) {
        List<Double> lists = new ArrayList<>();
        double b = Math.ceil(maxV / 5);
        double c = b;
        lists.add(0d);
        for (int i = 0; i < 5; i++) {
            c = c + b;
            lists.add(c);
        }
        return lists;
    }

    public static void loginNo(Context mContext) {
        ToastUtil.showLongStrToast(mContext, "请先登录");
    }

    public static void showKf(Context mContext) {
        ToastUtil.showLongStrToast(mContext, "努力开发中....");
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public static int getPos(Date currTime) {
//        Date currTime = new Date(); // 当前日期baidu
        GregorianCalendar cale = new GregorianCalendar(); // 格里高利日历
        cale.setTime(currTime); // 绑定当前日期
        cale.set(Calendar.DAY_OF_MONTH, 1); // 变为本月第一天
        if (cale.getFirstDayOfWeek() == Calendar.SUNDAY) { // 每周以星zhi期日开始dao
            return cale.get(Calendar.DAY_OF_WEEK) - 1;
        } else { // 每周以星期一开始（一般不会再有其它情况）
            return cale.get(Calendar.DAY_OF_WEEK);
        }
    }

    /**
     * 功能描述：返回日期
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getNewContent1(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }


    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


    public static String getLocalVideoDuration(String videoPath) {
        //除以 1000 返回是秒
        int duration;
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(videoPath);
            duration = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)) / 1000;

            //时长(毫秒)
//            String duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
            //宽
            String width = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
            //高
            String height = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return String.valueOf(duration);
    }

    /**
     * 获取上个月的天数
     * @return
     */
    public static int getMonthLastDay() {
        Calendar cal = Calendar.getInstance();
//        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
//        int dow = cal.get(Calendar.DAY_OF_WEEK);
//        int dom = cal.get(Calendar.DAY_OF_MONTH);
//        int doy = cal.get(Calendar.DAY_OF_YEAR);
//
//        System.out.println("当期时间: " + cal.getTime());
//        System.out.println("日期: " + day);
//        System.out.println("月份: " + month);
//        System.out.println("年份: " + year);
//        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
//        System.out.println("一月中的第几天: " + dom);
//        System.out.println("一年的第几天: " + doy);
        Date day = new Date(year, month, 0);
        return day.getDate();
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static ArrayList<String> getDays(int intervals, String type) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals - 1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i, type));
        }
        return pastDaysList;
    }

    public static String getTimeGeShiYw(long date) {
        if (date < 60) {
            return date + "s";
        } else if (date > 60 && date < 3600) {
            long m = date / 60;
            long s = date % 60;
            return m + "m" + s + "s";
        } else {
            long h = date / 3600;
            long m = (date % 3600) / 60;
            long s = (date % 3600) % 60;
            return h + "h" + m + "m" + s + "s";
        }
    }

    public static String getTimeGeShi(long date) {
        if (date < 60) {
            return date + "秒";
        } else if (date > 60 && date < 3600) {
            long m = date / 60;
            long s = date % 60;
            return m + "分" + s + "秒";
        } else {
            long h = date / 3600;
            long m = (date % 3600) / 60;
            long s = (date % 3600) % 60;
            return h + "小时" + m + "分" + s + "秒";
        }
    }

    public static String getTimeGeShiH(long date) {
        if (date < 60) {
            return date + "'";
        } else if (date > 60 && date < 3600) {
            long m = date / 60;
            long s = date % 60;
            return m + "'" + s + "'";
        } else {
            long h = date / 3600;
            long m = (date % 3600) / 60;
            long s = (date % 3600) % 60;
            return h + "'" + m + "'" + s + "'";
        }
    }

    public static Double getTimeFenMiao(long date) {
        if (date < 60) {
            return Double.valueOf("0." + date);
        } else {
            long m = date / 60;
            long s = date % 60;
            return Double.valueOf(m + "." + s);
        }
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @param type
     * @return
     */
    public static String getPastDate(int past, String type) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(type);
        String result = format.format(today);
        return result;
    }


    public static ArrayList<String> getDayMonth7() {
        ArrayList<String> lists = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int flag = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1 - flag);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        cal.add(Calendar.DATE, 1);
        lists.add(cal.get(Calendar.YEAR) + getDayMonth7(cal.get(Calendar.MONTH) + 1) + getDayMonth7(cal.get(Calendar.DAY_OF_MONTH)));
        return lists;
    }

    public static String getDayMonth7(int day) {
        String str = String.valueOf(day);
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * int double 两位小时
     *
     * @param obj
     * @return
     */
    public static String getValue(String obj) {
        Log.w("getValue", "obj:" + obj);
        if (isIntegerForDouble(Double.valueOf(obj))) {
            return getValue(Double.valueOf(obj));
        }
        return String.format("%.2f", Double.valueOf(obj));
    }

    /**
     * int double 两位小时
     *
     * @param obj
     * @return
     */
    public static String getValue(double obj) {
        Log.w("getValue", "obj:" + obj);
        if (isIntegerForDouble(obj)) {
            return String.valueOf((int) obj);
        }
        return String.format("%.2f", obj);
    }

    /**
     * 判断double是否是整数
     *
     * @param obj
     * @return
     */
    public static boolean isIntegerForDouble(double obj) {
        double eps = 1e-10;  // 精度范围
        return obj - Math.floor(obj) < eps;
    }


    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }
    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }
}
