package com.jackson.ssrjmvp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.jackson.ssrjmvp.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* CommonMethod  2016-11-10
* Copyright (c) 2016 HYB Co.Ltd. All right reserved.
*/
/*
* 此类存放一般的工具方法
* @author Borje
* @version 1.0.0
* since 2016-11-10
*/
public class CommonMethod {

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 判断email格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获取版本名
     *
     * @return
     */
    public static String checkVersion(Context context) {
        //拿到包的管理器
        PackageManager pm = context.getPackageManager();
        try {
            //封装了所有的功能清单中的数据
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5加密
     *
     * @param info
     * @return
     */
    public static String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * 判断微信是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void showToast(Context context, String text, boolean isLongLength) {
        Toast toast = null;
        int length;
        if (isLongLength) {
            length = Toast.LENGTH_LONG;
        } else {
            length = Toast.LENGTH_SHORT;
        }
        if (toast == null) {
            toast = Toast.makeText(context, text, length);
        } else {
            toast.setText(text);
            toast.setDuration(length);
        }

        View view = toast.getView();
        view.setBackgroundResource(R.drawable.shape_toast_background);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }

    /**
     * 获得当前时间
     */
    public static String getCurrentTime(String format) {
        if (format.equals("")) {
            format = "yyyy年MM月dd日 HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static boolean isPhoneNum(String phoneNum) {
        String regularExpression = "^((13[0-9])|(15[^4])|(18[0,1,2,3,4,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     * lvliheng
     * dp 转换成 px
     * @param context
     * @param dp
     * @return
     */
    public static int convertDpToPixel(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }
}