package com.jackson.ssrjmvp.utils;


/*
* Constant  2016-11-14
* Copyright (c) 2016 HYB Co.Ltd. All right reserved.
*/
/*
* 保存全工程的常量
* @author Jackson
* @version 1.0.0
* since 2016-11-14
*/
public class Constant {
    public static boolean LOG_DEBUG = true; //控制工程的log信息打印

    public static final String baseUrl = "https://member.hybunion.cn/";
    public static final String baseUrlMoive = "https://api.douban.com/";
    public static final String baseUrlJD = "https://yaoser.jd.com";

    public static final int PAGE_COUNT = 5;  // 每页的个数

    public interface viewType {
        int typeBanner = 1;         //轮播图
        int typeGvidMenu = 2;      //功能菜单
        int typeMarquee = 3;       //跑马灯
        int typeHot = 4;           //七月爆品
        int typePrepare = 5;      // 常备好药
        int typeChoice=6;        // 超值精选
        int typeFix = 7;           //固定布局
        int typeScrollFix = 8;    //可选固定
        int typeFloat = 9;         //Float布局
        int typeColumn = 10;         //栏格布局
        int typeOnePlusN = 11;         //栏格布局
        int typeSticky = 12;         //吸边布局
        int typeStaggered = 13;         //瀑布流布局


    }

}