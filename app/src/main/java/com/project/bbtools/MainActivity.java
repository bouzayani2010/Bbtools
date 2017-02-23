package com.project.bbtools;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static final String CAMERA_IMAGE_BUCKET_NAME =
            Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera";

    public static final String CAMERA_IMAGE_BUCKET_ID =
            getBucketId(CAMERA_IMAGE_BUCKET_NAME);

    private static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }

    private GridView gridview;
    private Button button1;
    List<String> images =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        button1 = (Button) this.findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 images = getCameraImages(MainActivity.this);
                gridview.setAdapter(new ImageAdapter(MainActivity.this,images));
            }
        });
   /*     button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //1
                gridview.setVisibility(View.INVISIBLE);
                Rect myViewRect = new Rect();
                button1.getGlobalVisibleRect(myViewRect);

                float x = myViewRect.left;
                float y = myViewRect.top;
                y = button1.getTop();
                x = button1.getLeft();
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(height - y + button1.getHeight(), 0);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //3
                        float value = (float) animation.getAnimatedValue();
                        //4
                        gridview.setTranslationY(value);
                        button1.setTranslationY(value);
                    }
                });
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setDuration(8000);
                valueAnimator.start();
                gridview.setVisibility(View.VISIBLE);
                return false;
            }
        });*/
    }

    public static List<String> getCameraImages(Context context) {
        ArrayList<String> result = new ArrayList<String>();
        final String[] projection = {MediaStore.Images.Media.DATA};
        final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
        final String[] selectionArgs = { getBucketId(CAMERA_IMAGE_BUCKET_NAME)};
        final Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);
        result = new ArrayList<String>(cursor.getCount());
        if (cursor.moveToFirst()) {
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            do {
                final String data = cursor.getString(dataColumn);
                result.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}

