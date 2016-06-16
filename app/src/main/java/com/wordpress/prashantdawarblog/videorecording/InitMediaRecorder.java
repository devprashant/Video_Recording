package com.wordpress.prashantdawarblog.videorecording;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by mau on 6/16/2016.
 */
public class InitMediaRecorder {

    private File mOutputFile;
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;

    public InitMediaRecorder(Camera camera) {
        mCamera = camera;
        if(prepare(mCamera)){
            Log.d("Done", "Media Reocrder prepared");
        } else {
            Log.d("Error", "Media Recorder not prepared");
        }
    }

    public MediaRecorder getPreparedMediaRecorder(){

        return mMediaRecorder;
    }

    public boolean prepare(Camera camera) {

        mCamera = camera;

        mMediaRecorder = new MediaRecorder();

        // Step 1.
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Sterp 2.
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3.
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        // Step 4.
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

        mOutputFile = new File(Environment.getExternalStorageDirectory(),"/video_test4.3gp");
        mMediaRecorder.setOutputFile(mOutputFile.getAbsolutePath());

        // Step 5.
        try {
            mMediaRecorder.prepare();
        } catch(IllegalStateException e) {
            Log.d("ERROR", "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d("ERROR", "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    private void releaseMediaRecorder() {
        if(mMediaRecorder != null){
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;

            mCamera.lock();
        }
    }
}
