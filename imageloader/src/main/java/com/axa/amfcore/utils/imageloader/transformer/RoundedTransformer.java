/*
 * RoundedTransformer
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader.transformer;

import com.axa.amfcore.utils.imageloader.ImageDownloader;
import com.axa.amfcore.utils.imageloader.ImageLoader;

/**
 * Transformers wrapper
 */
public class RoundedTransformer implements ImageDownloader.Transformer {

    private final int radius;
    private final int margin;

    public RoundedTransformer(int radius, int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    public ImageDownloader.Transformer getTransformer(ImageLoader.Provider type) {
        switch (type) {
            case PICASSO:
                return new PicassoRoundedTransformer(radius, margin);
            case UIL:
            default:
                return new UILRoundedTransformer(radius, margin);
        }
    }

}
