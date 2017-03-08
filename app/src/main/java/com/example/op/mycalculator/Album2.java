package com.example.op.mycalculator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Album2 extends AppCompatActivity {
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 101;
    private static final int REQ_CODE_TAKE_PICTURE = 12345;
    Spinner spinnerNumImages;
    GridView gridAlbum;
    static Uri capturedImageUri=null;
    final int[] drawableIds = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
    };


    private void setViewByOrientation() {
        int orientation = getScreenOrientation();

        if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            changeUILandscape();
        } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            changeUIPortrait();
        }
        //doAnimation();
    }

    private void changeUIPortrait() {
        if (gridAlbum == null)
            gridAlbum = (GridView) findViewById(R.id.gridAlbum);

        int width = ScreenHelper.getInstance().getWidthScreen(Album2.this);

        gridAlbum.setNumColumns(width / 270);
    }

    private void changeUILandscape() {
        if (gridAlbum == null)
            gridAlbum = (GridView) findViewById(R.id.gridAlbum);

        int width = ScreenHelper.getInstance().getWidthScreen(Album2.this);

        gridAlbum.setNumColumns(width / 270);
    }

    private void doAnimation() {
        if (gridAlbum == null)
            return;

        int nView = gridAlbum.getCount();

        for (int i = 0; i < nView; i++)
        {
            //ImageView image = (ImageView) GetViewByPosition(gridAlbum, i);

            //Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shaking);

            //image.startAnimation(anim);
        }
    }

    private int getScreenOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        return ScreenHelper.getInstance().getScreenOrientation(dm, rotation);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        setViewByOrientation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album2);

        gridAlbum = (GridView) findViewById(R.id.gridAlbum);

        setViewByOrientation();

        //Fill data in spinner
        List<String> spinnerArray = new ArrayList<>();
        spinnerNumImages = (Spinner) findViewById(R.id.spinnerNumImages);
        for (int i = 0; i < 10; i++)
            spinnerArray.add(String.valueOf(i + 1));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumImages.setAdapter(adapter);

        //Load images
        spinnerNumImages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(Album2.this, item.toString(),
                            Toast.LENGTH_SHORT).show();
                    loadImages(item.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Event click on item in gridView
        gridAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id){
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), Profile.class);
                int s = (int) gridAlbum.getAdapter().getItem(position);
                // Pass image index
                i.putExtra("id", s);
                startActivity(i);
            }
        });
    }

    private void loadImages(String nRequire) {
        int nResources = drawableIds.length;
        int n;

        try {
            n = Integer.parseInt(nRequire);
        }catch (Exception e) {
            n= 0;
        }

        if (n  > nResources)
            n = nResources;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(drawableIds[i]);
        }

        gridAlbum.setAdapter(new ImageAdapter(Album2.this, list));
    }

    public void onClickButtonTakePicture(View view) {
        askPermissionAndCaptureImage();
        captureImage();
    }

    private void captureImage() {
        /*
        // Tạo một Intent không tường minh,
        // để yêu cầu hệ thống mở Camera chuẩn bị chụp hình.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = Environment.getExternalStorageDirectory();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //DBImagesHelper imagesHelper = new DBImagesHelper(this);
        //int COUNT = imagesHelper.getCountImage();

        // file:///storage/emulated/0/myvideo.mp4
        String savePath = dir.getAbsolutePath() + "/Pictures" + "/anh" + "1" + "" + ".jpg";
        File Image = new File(savePath);
        Uri imageUri = Uri.fromFile(Image);


        // Chỉ định vị trí lưu file
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        // Start Activity chụp hình, và chờ đợi kết quả trả về.
        Album2.this.startActivityForResult(intent, REQ_CODE_TAKE_PICTURE);*/
        Calendar cal = Calendar.getInstance();
        File dir = Environment.getExternalStorageDirectory();
        String savePath = "Pictures/" + cal.getTimeInMillis()+".jpg";
        File file = new File(dir,  (savePath));
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        capturedImageUri = Uri.fromFile(file);
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
        startActivityForResult(i, REQ_CODE_TAKE_PICTURE);
    }

    private void askPermissionAndCaptureImage(){
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Kiểm tra quyền đọc/ghi dữ liệu vào thiết bị lưu trữ ngoài.
            int readPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED) {

                // Nếu không có quyền, cần nhắc người dùng cho phép.
                this.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_ID_READ_WRITE_PERMISSION
                );
            }
        }
    }

    // Khi yêu cầu hỏi người dùng được trả về (Chấp nhận hoặc không chấp nhận).
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case REQUEST_ID_READ_WRITE_PERMISSION: {

                // Chú ý: Nếu yêu cầu bị hủy, mảng kết quả trả về là rỗng.
                // Người dùng đã cấp quyền (đọc/ghi).
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();

                    this.captureImage();

                }
                // Hủy bỏ hoặc bị từ chối.
                else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_TAKE_PICTURE && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap( getApplicationContext().getContentResolver(),  capturedImageUri);
                ImageView tmp = (ImageView) findViewById(R.id.imageViewTest);
                tmp.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(Album2.this, "Catpure done", Toast.LENGTH_SHORT).show();
        }
    }

}
