import 'package:flutter_test/flutter_test.dart';
import 'package:video_player_surface/video_player_surface.dart';
import 'package:video_player_surface/video_player_surface_platform_interface.dart';
import 'package:video_player_surface/video_player_surface_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockVideoPlayerSurfacePlatform
    with MockPlatformInterfaceMixin
    implements VideoPlayerSurfacePlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final VideoPlayerSurfacePlatform initialPlatform = VideoPlayerSurfacePlatform.instance;

  test('$MethodChannelVideoPlayerSurface is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelVideoPlayerSurface>());
  });

  test('getPlatformVersion', () async {
    VideoPlayerSurface videoPlayerSurfacePlugin = VideoPlayerSurface();
    MockVideoPlayerSurfacePlatform fakePlatform = MockVideoPlayerSurfacePlatform();
    VideoPlayerSurfacePlatform.instance = fakePlatform;

    expect(await videoPlayerSurfacePlugin.getPlatformVersion(), '42');
  });
}
