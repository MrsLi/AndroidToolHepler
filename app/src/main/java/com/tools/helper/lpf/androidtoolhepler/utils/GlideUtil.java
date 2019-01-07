package com.tools.helper.lpf.androidtoolhepler.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class GlideUtil {
    public static void LoadImg(Context context, ImageView view, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    public static void LoadImg(Context context, ImageView view, String imgUrl, int defImg) {
        Glide.with(context)
                .load(imgUrl)
                .placeholder(defImg)
                .error(defImg)
                .fallback(defImg)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    public static void LoadCircleImg(Context context, ImageView view, String imgUrl, int defImg) {

        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(defImg)
                .error(defImg)
                .fallback(defImg)
                .transform(new CircleTransform(context))
//                .transition(DrawableTransitionOptions.withCrossFade())//交叉淡入
                .into(view);
    }

    public static void LoadRoundImg(Context context, ImageView view, String imgUrl, int defImg, float radius) {

        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new RoundTransform(context, radius))
                .placeholder(defImg)
                .error(defImg)
                .fallback(defImg)
                .into(view);
    }

    public static void LoadRoundImg(Context context, ImageView view, String imgUrl, float radius) {

        Glide.with(context)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new RoundTransform(context, radius))
                .into(view);
    }


    /**
     * 圆角图片
     */
    public static class RoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public RoundTransform(Context context) {
            this(context, 4);
        }

        public RoundTransform(Context context, float dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

    /**
     * 圆形图片
     */
    public static class CircleTransform extends BitmapTransformation {

        public CircleTransform(Context context) {
            super(context);
        }

        /**
         * 重写 生成圆角图片
         *
         * @param pool
         * @param toTransform
         * @param outWidth
         * @param outHeight
         * @return
         */
        @Override
        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            //画布中背景图片与绘制图片交集部分
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }
}
