/*
 * UILRoundedTransformer
 *
 * Created by Roman Rodriguez on 20/05/15.
 *
 * Copyright (c) 2015 Axa Group Solutions. All rights reserved.
 *
 */

package com.axa.amfcore.utils.imageloader.transformer;

import com.axa.amfcore.utils.imageloader.ImageDownloader;
import com.axa.amfcore.utils.imageloader.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Makes a rounded image.
 */
class UILRoundedTransformer extends RoundedBitmapDisplayer
        implements ImageDownloader.Transformer { // Must have 'Package' visibility

    /**
     * Builder.
     * @param cornerRadiusPixels
     */
    public UILRoundedTransformer(int cornerRadiusPixels) {
        super(cornerRadiusPixels);
    }

    /**
     * Builder.
     * @param cornerRadiusPixels
     * @param marginPixels
     */
    public UILRoundedTransformer(int cornerRadiusPixels, int marginPixels) {
        super(cornerRadiusPixels, marginPixels);
    }

    @Override
    public ImageDownloader.Transformer getTransformer(ImageLoader.Provider type) {
        return this;
    }

}
