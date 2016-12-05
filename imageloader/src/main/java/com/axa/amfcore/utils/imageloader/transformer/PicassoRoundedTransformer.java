/*
 * PicassoRoundedTransformer
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader.transformer;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.axa.amfcore.utils.imageloader.ImageDownloader;
import com.axa.amfcore.utils.imageloader.ImageLoader;

// enables hardware accelerated rounded corners
// original idea here :
// http://www.curious-creature.org/2012/12/11/android-recipe-1-image-with-rounded-corners/
// Must have 'Package' visibility
class PicassoRoundedTransformer implements com.squareup.picasso.Transformation,
        ImageDownloader.Transformer { // Must have 'Package' visibility

    private static String key;
    private final int radius;
    private final int margin;  // dp

    // radius is corner radii in dp
    // margin is the board in dp
    public PicassoRoundedTransformer(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
        key = "rounded(radius=" + radius + ", margin=" + margin + ")";
    }

    @Override
    public Bitmap transform(final Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

        if (source != output) {
            source.recycle();
        }

        return output;
    }

    @Override
    public String key() {
        return "rounded(radius=" + radius + ", margin=" + margin + ")";
    }

    @Override
    public ImageDownloader.Transformer getTransformer(ImageLoader.Provider type) {
        return this;
    }
}
