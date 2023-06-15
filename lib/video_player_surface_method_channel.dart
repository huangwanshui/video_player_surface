import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'video_player_surface_platform_interface.dart';

/// An implementation of [VideoPlayerSurfacePlatform] that uses method channels.
class MethodChannelVideoPlayerSurface extends VideoPlayerSurfacePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('video_player_surface');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
