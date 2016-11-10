package com.example.daksha.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static android.R.attr.data;
import static android.R.attr.targetId;

/**
 * Created by Daksha on 10/25/2016.
 */

public class ImageEditTaskActivity extends AppCompatActivity {

    private ImageView imageview;
    private Uri selectedImage;
    private TextView tvStatus;

    private Bitmap bmp;
    private Bitmap operation;

    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_edit_layout);
        imageview = (ImageView) findViewById(R.id.ivGoku);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        BitmapDrawable abmp = (BitmapDrawable) imageview.getDrawable();
        bmp = abmp.getBitmap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.Camera) {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    startActivityForResult(cameraIntent, 0);
                }
            }

            /*Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 0);*/

            //do something
            return true;
        }
        if (id == R.id.Gallery) {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , 1);//one can be replaced with any action code

            //do something
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == AppCompatActivity.RESULT_OK){
                    try {
                        Bitmap mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                        imageview.setImageBitmap(mImageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);*/
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    imageview.setImageURI(selectedImage);
                }
                break;
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    public void imageEdit(View view) {

        switch (view.getId()) {

            case R.id.ibGrayScale:
                imageview.setImageURI(selectedImage);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    imageview.setDrawingCacheEnabled(true);
                    imageview.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    imageview.layout(0, 0, imageview.getMeasuredWidth(), imageview.getMeasuredHeight());

                    imageview.buildDrawingCache(true);


                    Bitmap originalImage = Bitmap.createBitmap(imageview.getDrawingCache());
                    imageview.setDrawingCacheEnabled(false);

                    GrayScaleTask grayScaleTask = new GrayScaleTask(this);
                    grayScaleTask.execute(originalImage);
                    /*Bitmap grayImage = toGrayScale(originalImage);
                    imageview.setImageBitmap(grayImage);*/
                }
                break;

            case R.id.ibNoise:
                imageview.setImageURI(selectedImage);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    imageview.setDrawingCacheEnabled(true);
                    imageview.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    imageview.layout(0, 0, imageview.getMeasuredWidth(), imageview.getMeasuredHeight());

                    imageview.buildDrawingCache(true);


                    Bitmap originalImage = Bitmap.createBitmap(imageview.getDrawingCache());
                    imageview.setDrawingCacheEnabled(false);

                    NoiseTask noiseTask = new NoiseTask(this);
                    noiseTask.execute(originalImage);
                    /*Bitmap noiseImage = toNoise(originalImage);
                    imageview.setImageBitmap(noiseImage);*/
                }
                break;

            case R.id.ibSepia:
                imageview.setImageURI(selectedImage);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    imageview.setDrawingCacheEnabled(true);
                    imageview.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    imageview.layout(0, 0, imageview.getMeasuredWidth(), imageview.getMeasuredHeight());

                    imageview.buildDrawingCache(true);


                    Bitmap originalImage = Bitmap.createBitmap(imageview.getDrawingCache());
                    imageview.setDrawingCacheEnabled(false);

                    SepiaTask sepiaTask = new SepiaTask(this);
                    sepiaTask.execute(originalImage);
                    /*Bitmap sepiaImage = toSepia(originalImage);
                    imageview.setImageBitmap(sepiaImage);*/
                }
                break;

            case R.id.ibSketch:
                imageview.setImageURI(selectedImage);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    imageview.setDrawingCacheEnabled(true);
                    imageview.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    imageview.layout(0, 0, imageview.getMeasuredWidth(), imageview.getMeasuredHeight());

                    imageview.buildDrawingCache(true);


                    Bitmap originalImage = Bitmap.createBitmap(imageview.getDrawingCache());
                    imageview.setDrawingCacheEnabled(false);

                    SketchTask sketchTask = new SketchTask(this);
                    sketchTask.execute(originalImage);
                    /*Bitmap sketchImage = toSketch(originalImage);
                    imageview.setImageBitmap(sketchImage);*/
                }
                break;

            case R.id.ibVignette:
                imageview.setImageURI(selectedImage);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                    imageview.setDrawingCacheEnabled(true);
                    imageview.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    imageview.layout(0, 0, imageview.getMeasuredWidth(), imageview.getMeasuredHeight());

                    imageview.buildDrawingCache(true);


                    Bitmap originalImage = Bitmap.createBitmap(imageview.getDrawingCache());
                    imageview.setDrawingCacheEnabled(false);

                    VignetteTask vignetteTask = new VignetteTask(this);
                    vignetteTask.execute(originalImage);
                    /*Bitmap vignetteImage = toVignette(originalImage);
                    imageview.setImageBitmap(vignetteImage);*/
                }
                break;
        }

    }

    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    public static Bitmap toGrayScale(Bitmap originalImage) {
        //Array to generate Gray-Scale image
        float[] GrayArray = {
                0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
                0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
                0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
        };

        ColorMatrix colorMatrixGray = new ColorMatrix(GrayArray);

        int w = originalImage.getWidth();
        int h = originalImage.getHeight();

        Bitmap bmpGrayScale = Bitmap
                .createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvasResult = new Canvas(bmpGrayScale);
        Paint paint = new Paint();

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrixGray);
        paint.setColorFilter(filter);
        canvasResult.drawBitmap(originalImage, 0, 0, paint);

        originalImage.recycle();
        originalImage = null;

        return bmpGrayScale;

        /*int width, height;
        height = originalImage.getHeight();
        width = originalImage.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(originalImage, 0, 0, paint);
        return bmpGrayscale;*/
    }

    public static Bitmap toNoise(Bitmap originalImage){
        final int COLOR_MAX = 0xFF;

        // get image size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int[] pixels = new int[width * height];
        // get pixel array from source
        originalImage.getPixels(pixels, 0, width, 0, 0, width, height);
        // a random object
        Random random = new Random();

        int index = 0;
        // iteration through pixels
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                // get current index in 2D-matrix
                index = y * width + x;
                // get random color
                int randColor = Color.rgb(random.nextInt(COLOR_MAX),
                        random.nextInt(COLOR_MAX), random.nextInt(COLOR_MAX));
                // OR
                pixels[index] |= randColor;
            }
        }
        // output bitmap
        Bitmap bmpNoise = Bitmap.createBitmap(width, height, originalImage.getConfig());
        bmpNoise.setPixels(pixels, 0, width, 0, 0, width, height);

        originalImage.recycle();
        originalImage = null;

        return bmpNoise;
    }

    public static Bitmap toSepia(Bitmap originalImage){
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        // create output bitmap
        Bitmap bmpSepia = Bitmap.createBitmap(width, height, originalImage.getConfig());
        // constant grayscale
        final double GS_RED = 0.3;
        final double GS_GREEN = 0.59;
        final double GS_BLUE = 0.11;
        // color information
        int A, R, G, B;
        int pixel;

        // scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = originalImage.getPixel(x, y);
                // get color on each channel
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // apply grayscale sample
                B = G = R = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);

                // apply intensity level for sepid-toning on each channel
                R += 110;
                if(R > 255) { R = 255; }

                G += 65;
                if(G > 255) { G = 255; }

                B += 20;
                if(B > 255) { B = 255; }

                // set new pixel color to output image
                bmpSepia.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        originalImage.recycle();
        originalImage = null;

        return bmpSepia;
    }

    public static Bitmap toSketch(Bitmap originalImage){
        int type = 6;
        int threshold = 130;

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        Bitmap bmpSketch = Bitmap.createBitmap(width, height, originalImage.getConfig());

        int A, R, G, B;
        int sumR, sumG, sumB;
        int[][] pixels = new int[3][3];
        for(int y = 0; y < height - 2; ++y) {
            for(int x = 0; x < width - 2; ++x) {
                //      get pixel matrix
                for(int i = 0; i < 3; ++i) {
                    for(int j = 0; j < 3; ++j) {
                        pixels[i][j] = originalImage.getPixel(x + i, y + j);
                    }
                }
                // get alpha of center pixel
                A = Color.alpha(pixels[1][1]);
                // init color sum
                sumR = sumG = sumB = 0;
                sumR = (type*Color.red(pixels[1][1])) - Color.red(pixels[0][0]) - Color.red(pixels[0][2]) - Color.red(pixels[2][0]) - Color.red(pixels[2][2]);
                sumG = (type*Color.green(pixels[1][1])) - Color.green(pixels[0][0]) - Color.green(pixels[0][2]) - Color.green(pixels[2][0]) - Color.green(pixels[2][2]);
                sumB = (type*Color.blue(pixels[1][1])) - Color.blue(pixels[0][0]) - Color.blue(pixels[0][2]) - Color.blue(pixels[2][0]) - Color.blue(pixels[2][2]);
                // get final Red
                R = (int)(sumR  + threshold);
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }
                // get final Green
                G = (int)(sumG  + threshold);
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }
                // get final Blue
                B = (int)(sumB  + threshold);
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                bmpSketch.setPixel(x + 1, y + 1, Color.argb(A, R, G, B));
            }
        }

        originalImage.recycle();
        originalImage = null;

        return bmpSketch;
    }

    public static Bitmap toVignette(Bitmap originalImage){
        final int width = originalImage.getWidth();
        final int height = originalImage.getHeight();

        float radius = (float) (width/1.2);
        int[] colors = new int[] { 0, 0x55000000, 0xff000000 };
        float[] positions = new float[] { 0.0f, 0.5f, 1.0f };

        RadialGradient gradient = new RadialGradient(width / 2, height / 2, radius, colors, positions, Shader.TileMode.CLAMP);

        //RadialGradient gradient = new RadialGradient(width / 2, height / 2, radius, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP);

        Canvas canvas = new Canvas(originalImage);
        canvas.drawARGB(1, 0, 0, 0);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setShader(gradient);

        final Rect rect = new Rect(0, 0, originalImage.getWidth(), originalImage.getHeight());
        final RectF rectf = new RectF(rect);

        canvas.drawRect(rectf, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(originalImage, rect, rect, paint);

        return originalImage;
    }
}
