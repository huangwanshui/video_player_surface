package com.example.video_player_surface;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

import io.flutter.plugin.platform.PlatformView;

// create SurfaceViewPlatformView
class SurfaceViewPlatformView implements PlatformView {
    private final SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;

    SurfaceViewPlatformView(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
        //simple delay play the video
        surfaceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 创建MediaPlayer并设置数据源
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDisplay(surfaceView.getHolder());

                AssetFileDescriptor assetFileDescriptor = null;
                try {
                    // 获取MP4文件的AssetFileDescriptor
                    assetFileDescriptor = surfaceView.getContext().getAssets().openFd("big_buck_bunny.mp4");
                    // 设置MediaPlayer的数据源为AssetFileDescriptor
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    // 准备MediaPlayer
                    mediaPlayer.prepare();
                    // 开始播放
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 释放AssetFileDescriptor资源
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, 1000);
    }

    @Override
    public View getView() {
        return surfaceView;
    }

    @Override
    public void dispose() {
        // 清理资源
        mediaPlayer.release();
    }
}