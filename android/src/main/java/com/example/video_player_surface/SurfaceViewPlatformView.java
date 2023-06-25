package com.example.video_player_surface;

import static com.manager.db.Define.LOGIN_BY_ACCOUNT;
import static com.manager.db.Define.MEDIA_DATA_TYPE_YUV_NOT_DISPLAY;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Message;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;


import com.lib.MsgContent;
import com.lib.sdk.struct.SDK_FishEyeFrame;
import com.manager.XMFunSDKManager;
import com.manager.account.BaseAccountManager;
import com.manager.account.XMAccountManager;
import com.manager.db.XMDevInfo;
import com.manager.device.DeviceManager;
import com.manager.device.media.MediaManager;
import com.manager.device.media.attribute.PlayerAttribute;
import com.manager.device.media.monitor.MonitorManager;

import java.io.IOException;

import io.flutter.plugin.platform.PlatformView;

// create SurfaceViewPlatformView
class SurfaceViewPlatformView implements PlatformView {
    private final ViewGroup surfaceView;
    private MediaPlayer mediaPlayer;

    SurfaceViewPlatformView(ViewGroup surfaceView) {
        this.surfaceView = surfaceView;
        //simple delay play the video
        XMFunSDKManager.getInstance().initXMCloudPlatform(surfaceView.getContext());
        XMFunSDKManager.getInstance().initLog();
        MonitorManager monitorManager = DeviceManager.getInstance().createMonitorPlayer(surfaceView,"8b3085483be4c546");
        monitorManager.setPlayMode(MEDIA_DATA_TYPE_YUV_NOT_DISPLAY);
        monitorManager.setOnMediaManagerListener(new MediaManager.OnMediaManagerYUVListener() {
            @Override
            public void onResultYUVData(PlayerAttribute attribute, int width, int height, byte[] data) {
                System.out.println("onResultYUVData:" + data);
            }

            @Override
            public void onMediaPlayState(PlayerAttribute attribute, int state) {

            }

            @Override
            public void onFailed(PlayerAttribute attribute, int msgId, int errorId) {

            }

            @Override
            public void onShowRateAndTime(PlayerAttribute attribute, boolean isShowTime, String time, String rate) {

            }

            @Override
            public void onVideoBufferEnd(PlayerAttribute attribute, MsgContent ex) {

            }
        });
        monitorManager.startMonitor();
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