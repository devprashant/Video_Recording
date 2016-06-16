package com.wordpress.prashantdawarblog.videorecording;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by mau on 6/15/2016.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceHolder mHolder;

    public CameraView(Context context, Camera camera) {
        super(context);

        mCamera = camera;
        mCamera.setDisplayOrientation(90);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceholder) {
        try {
            mCamera.setPreviewDisplay(surfaceholder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("ERROR","Camera error on surfaceCreated" + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
