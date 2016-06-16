package com.wordpress.prashantdawarblog.videorecording;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Camera mCamera;
    private CameraView mCameraView = null;
    private InitMediaRecorder mInitMediaRecorder;
    private MediaRecorder mMediaRecoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ToggleButton) findViewById(R.id.btnToggleRecording)).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null){
            mCameraView = new CameraView(this, mCamera);
            FrameLayout camera_view = (FrameLayout) findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);
        }
    }

    @Override
    public void onClick(View v) {
        if(((ToggleButton)v).isChecked()){
            mInitMediaRecorder = new InitMediaRecorder(mCamera);
            mMediaRecoder = mInitMediaRecorder.getPreparedMediaRecorder();

            mMediaRecoder.start();
        } else {
            mMediaRecoder.stop();
            mMediaRecoder.reset();
            mMediaRecoder.release();
            mMediaRecoder = null;
        }
    }
}
