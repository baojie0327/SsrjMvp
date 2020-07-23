package com.jackson.ssrjmvp;

/*
 * Test  2019-10-30
 * Copyright (c) 2019 KL Co.Ltd. All right reserved.
 *
 */
/*
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2019 10 30
 */


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public  void append(String str1, String str2) {
        str1 += str2;
    }

    public static void main(String[] args){
        String str1="Shui";
        String str2="Di";
        Test test=new Test();
        test.append(str1,str2);
        System.out.println(str1);
        System.out.println("hbj"+getDaysInterval("2019-10-10","2092-10-10"));
    }

    public static int getDaysInterval(String startDate, String endDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0;
        try {
            Date date1 = format.parse(startDate);
            Date date2 = format.parse(endDate);
            days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;

    }

}


