import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'video_player_surface_method_channel.dart';

abstract class VideoPlayerSurfacePlatform extends PlatformInterface {
  /// Constructs a VideoPlayerSurfacePlatform.
  VideoPlayerSurfacePlatform() : super(token: _token);

  static final Object _token = Object();

  static VideoPlayerSurfacePlatform _instance = MethodChannelVideoPlayerSurface();

  /// The default instance of [VideoPlayerSurfacePlatform] to use.
  ///
  /// Defaults to [MethodChannelVideoPlayerSurface].
  static VideoPlayerSurfacePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [VideoPlayerSurfacePlatform] when
  /// they register themselves.
  static set instance(VideoPlayerSurfacePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
