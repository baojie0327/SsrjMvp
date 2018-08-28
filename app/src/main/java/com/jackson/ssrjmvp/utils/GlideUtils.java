package com.jackson.ssrjmvp.utils; /**
 * GlideUtils  2018-07-13
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jackson.ssrjmvp.R;

/**
 * glide架子啊图片工具类
 * @author Jackson
 * @version 1.0.0
 * since 2018 07 13
 */
public class GlideUtils {

    public static void loadUrlImage(Context context, String url, ImageView imageView){
        // options
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.default_square_four)
                //  .transform(new GlideCircleTransform(mContext))
                .dontTransform()
                .skipMemoryCache(true)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        // 加载图片
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public static void loadUrlImage(Context context, int url, ImageView imageView){
        // options
        RequestOptions options = new RequestOptions()
             //   .placeholder(R.drawable.default_square_four)
                //  .transform(new GlideCircleTransform(mContext))
               .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

      /*  RequestBuilder<Bitmap> requestBuilder = Glide.with(context).as;
        requestBuilder.apply(options);*/

        // 加载图片
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

}

