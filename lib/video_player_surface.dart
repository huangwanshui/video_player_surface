
import 'video_player_surface_platform_interface.dart';

class VideoPlayerSurface {
  Future<String?> getPlatformVersion() {
    return VideoPlayerSurfacePlatform.instance.getPlatformVersion();
  }
}
