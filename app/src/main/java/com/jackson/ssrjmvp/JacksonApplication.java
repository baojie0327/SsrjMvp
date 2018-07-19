package com.jackson.ssrjmvp; /**
 * JacksonApplication  2017-09-07
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.app.Application;

/**
 * Application,全局初始化配置在此
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 09 07
 */
public class JacksonApplication extends Application {

    private static JacksonApplication jsApp;

    @Override
    public void onCreate() {
        super.onCreate();
        jsApp = this;
     //   ClassicsFooter.REFRESH_FOOTER_FINISH="";
    }

    public static JacksonApplication getInstance() {
        return jsApp;
    }
}

