package com.example.video_player_surface;

import android.content.Context;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

// 创建SurfaceViewFactory
class SurfaceViewFactory extends PlatformViewFactory {
  SurfaceViewFactory() {
    super(StandardMessageCodec.INSTANCE);
  }

  @Override
  public PlatformView create(Context context, int id, Object args) {
    // 创建SurfaceView
    FrameLayout surfaceView = new FrameLayout(context);

    // 返回SurfaceView作为PlatformView
    return new SurfaceViewPlatformView(surfaceView);
  }
}
