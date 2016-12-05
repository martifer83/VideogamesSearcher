package marti.com.example.exampleapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import marti.com.example.exampleapp.R;

/**
 * Created by mferrando on 14/06/16.
 */
public class UtilsImage {

    public static void displayImage(ImageView imageView, String uri, int defaultDrawableRes) {
        ImageLoader.getInstance().displayImage(uri, imageView, imageLoaderOptions(defaultDrawableRes));
    }

    private static DisplayImageOptions imageLoaderOptions(int defaultDrawableRes) {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultDrawableRes)
                .showImageForEmptyUri(defaultDrawableRes)
                .showImageOnFail(defaultDrawableRes)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static void displayProfileImage(ImageView imageView, String encoding64, String url) {
        if (!TextUtils.isEmpty(encoding64)) {
            imageView.setImageBitmap(decodeBase64(encoding64));
        } else {
            displayImage(imageView, url, R.drawable.dummy_event_im);
        }
    }


    // Returns the URI path to the Bitmap displayed in specified ImageView
    public static Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
