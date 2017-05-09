package com.project.bbtools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    public static final String CAMERA_IMAGE_BUCKET_NAME =
            Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera";

    public static final String CAMERA_IMAGE_BUCKET_ID =
            getBucketId(CAMERA_IMAGE_BUCKET_NAME);
    private Button btn_firebase;
    private DatabaseReference mDatabase;
    private Button text_to_speach;

    private static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }

    private GridView gridview;
    private Button button1;
    List<String> images = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        button1 = (Button) this.findViewById(R.id.xml);

        text_to_speach = (Button) this.findViewById(R.id.text_to_speach);
        text_to_speach.setOnClickListener(this);
        btn_firebase = (Button) this.findViewById(R.id.firebase);
        btn_firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("belgacem", "email@email.com");

                mDatabase.child("users").child(String.valueOf(user.hashCode())).setValue(user);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   images = getCameraImages(MainActivity.this);
                //   gridview.setAdapter(new ImageAdapter(MainActivity.this, images));
            }
        });

    }

    public static List<String> getCameraImages(Context context) {
        ArrayList<String> result = new ArrayList<String>();
        final String[] projection = {MediaStore.Images.Media.DATA};
        final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
        final String[] selectionArgs = {getBucketId(CAMERA_IMAGE_BUCKET_NAME)};
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Speach.class);
        MainActivity.this.startActivityForResult(intent,100);
    }
}

