package me.dm7.barcodescanner.core;

import android.hardware.Camera;
import android.support.annotation.NonNull;

public class CameraWrapper {
    private Camera mCamera;
    public final int mCameraId;

    private CameraWrapper(@NonNull Camera camera, int cameraId) {
        if (camera == null) {
            throw new NullPointerException("Camera cannot be null");
        }
        this.mCamera = camera;
        this.mCameraId = cameraId;
    }

    public synchronized void releaseCamera() {
        mCamera.setPreviewCallback(null);
        mCamera.release();
        mCamera = null;
    }

    public synchronized void stopPreView() {
        mCamera.cancelAutoFocus();
        mCamera.setOneShotPreviewCallback(null);
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
    }

    public static CameraWrapper getWrapper(Camera camera, int cameraId) {
        if (camera == null) {
            return null;
        } else {
            return new CameraWrapper(camera, cameraId);
        }
    }

    public Camera getCamera() {
        return mCamera;
    }
}
