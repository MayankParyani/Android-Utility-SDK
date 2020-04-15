package sdk.utils.wd.image;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

import sdk.utils.wd.exception.StoragePermissionMissing;
import sdk.utils.wd.log.CustomLogUtility;

public class ImageUtility {

    private static final String TAG = ImageUtility.class.getName();

    /**
     * converts sdk.utils.wd.image of the form "drawable" to "bitmap"
     *
     * @param drawable drawable resource
     * @return bitmap obtained from drawable
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static boolean isStoragePermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                CustomLogUtility.logD(TAG, "Permission is granted by User");
                return true;
            } else {
                CustomLogUtility.logD(TAG, "Permission is revoked");
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            CustomLogUtility.logD(TAG, "Permission is granted automatically by system");
            return true;
        }
    }


    public static String SaveImage(Context context, Bitmap bitmap, String path, String filename, int quality, String format) throws StoragePermissionMissing {
        if (isStoragePermissionGranted(context)) {
            String root = Environment.getExternalStorageDirectory().toString();

            File myDir = new File(root + path); //Creates a new File instance by converting the given pathname string into an abstract pathname.
            myDir.mkdirs();
            Random generator = new Random();
            int mRandonNumber = 10000;
            mRandonNumber = generator.nextInt(mRandonNumber);
            String fname = "";
            if (format.equalsIgnoreCase("jpg")) {
                fname = filename + mRandonNumber + ".jpg"; //child path name String
            } else if (format.equalsIgnoreCase("png")) {
                fname = filename + mRandonNumber + ".png"; //child path name String
            }

            File file = new File(myDir, fname); //Creates a new File instance from a parent abstract pathname and a child pathname string.

            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                if (format.equalsIgnoreCase("jpg")) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                } else if (format.equalsIgnoreCase("png")) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, quality, out);
                }
                out.flush();
                out.close();
                return root + path;
                //Toast.makeText(context, "Image Stored in: Device Storage/" + path, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new StoragePermissionMissing("Storage Permission is not available");
        }
        return "";
    }


    /**
     * Gives the device independent constant which can be used for scaling images, manipulating view
     * sizes and changing dimension and display pixels etc.
     **/
    public static float getDensityMultiplier(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A int value to represent dp equivalent to px value
     */
    public static int getDip(int px, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    /**
     * Scales the image depending upon the display density of the device. Maintains image aspect
     * ratio.
     * <p>
     * When dealing with the bitmaps of bigger size, this method must be called from a non-UI
     * thread.
     **/
    public static Bitmap scaleDownBitmap(Context ctx, Bitmap source, int newHeight) {
        final float densityMultiplier = getDensityMultiplier(ctx);

        CustomLogUtility.logV(TAG, "#scaleDownBitmap Original w: " + source.getWidth() + " h: " + source.getHeight());

        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * source.getWidth() / ((double) source.getHeight()));

        CustomLogUtility.logV(TAG, "#scaleDownBitmap Computed w: " + w + " h: " + h);

        Bitmap photo = Bitmap.createScaledBitmap(source, w, h, true);

        CustomLogUtility.logV(TAG, "#scaleDownBitmap Final w: " + w + " h: " + h);

        return photo;
    }

    /**
     * Get the size of parameter {@link Bitmap}. This maybe a heavy operation.
     * Prefer not calling from main thread of the activity.
     *
     * @param data
     * @return size in bytes in string form
     */
    public static String sizeOf(Bitmap data) {
        String size = "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            size = getFileSize(data.getRowBytes() * data.getHeight());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            size = getFileSize(data.getByteCount());
        } else {
            size = getFileSize(data.getAllocationByteCount());
        }
        return size;
    }

    /**
     * converts bytes into kb,mb,gb or tb
     *
     * @param size integer format size
     * @return string in appropriate format
     */
    public static String getFileSize(long size) {
        if (size <= 0)
            return "0";

        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    @Nullable
    /**
     * Creates external content:// scheme uri to save the images at. The image saved at this
     * {@link android.net.Uri} will be visible via the gallery application on the device.
     **/
    public static Uri createImageUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    /**
     * converts a bitmap into blurred bitmap
     *
     * @param sentBitmap original bitmap
     * @param scale
     * @param radius
     * @return blurred bitmap
     * sample getBlurBitmap(bitmap, 0.2f, 2);
     */
    public static Bitmap getBlurBitmap(Bitmap sentBitmap, float scale, int radius) {

        int width = Math.round(sentBitmap.getWidth() * scale);
        int height = Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    /**
     * downloads an bitmap image from a remote url
     * Note: should not be called from Network Thread, call it from UI Thread
     * to use it form network thread use ImageDownload class
     *
     * @param url Image url from bitmap to be downloaded
     * @return bitmap
     */
    public static Bitmap downloadBitmap(String url) {
        try {
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();

            conn.connect();
            InputStream in = conn.getInputStream();

            Bitmap poster = BitmapFactory.decodeStream(in);
            in.close();

            return poster;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Class to download bitmap from an url using AsyncTask to download the image from Network thread as well
     * You need to register call back to BitmapDownloadedListener's OnBitmapDownloaded Method to receive bitmap
     * <p>
     * Sample: ImageUtility.ImageDownload image = new ImageUtility.ImageDownload(StartingActivity.this, this);
     * image.execute("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
     */
    public static class DownloadImageTask extends AsyncTask<String, Integer, Bitmap> {

        Context context;
        BitmapDownloadedListener bitmapDownloadedListener;

        public DownloadImageTask(Context context, BitmapDownloadedListener bitmapDownloadedListener) {
            this.context = context;
            this.bitmapDownloadedListener = bitmapDownloadedListener;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Bitmap doInBackground(String... params) {

            URL url = null;
            Bitmap bitmap = null;
            try {
                url = new URL(params[0]);
                bitmap = downloadBitmap(String.valueOf(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap data) {
            bitmapDownloadedListener.OnBitmapDownloaded(data);
        }
    }

}

