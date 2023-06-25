import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

class VideoPlayPage extends StatefulWidget {
  const VideoPlayPage({Key? key}) : super(key: key);

  @override
  State<VideoPlayPage> createState() => _VideoPlayPageState();
}

class _VideoPlayPageState extends State<VideoPlayPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('play'),
        actions: [
          InkWell(
            child: Icon(Icons.abc),
            onTap: () {

              Navigator.of(context).push(MaterialPageRoute(builder: (context) {
                return Scaffold(
                  body: Container(color: Colors.blue,),
                );
              }));
            },
          )
        ],
      ),
      body: Container(
        color: Colors.black,
        child: Center(
          child: OverflowBox(
            maxWidth: MediaQuery.of(context).size.width + 1,
            maxHeight: 200,
            child: _vd(),
          ),
        ),
      ),
    );
  }

  Widget _surface() {
    return PlatformViewLink(
        surfaceFactory: (context, controller) {
          return AndroidViewSurface(
            controller: controller as AndroidViewController,
            gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
            hitTestBehavior: PlatformViewHitTestBehavior.opaque,
          );
        },
        onCreatePlatformView: (params) {
          return PlatformViewsService.initSurfaceAndroidView(
            id: params.id,
            viewType: 'video_player',
            layoutDirection: TextDirection.ltr,
            creationParams: {},
            creationParamsCodec: const StandardMessageCodec(),
            onFocus: () {
              params.onFocusChanged(true);
            },
          )
            ..addOnPlatformViewCreatedListener(params.onPlatformViewCreated)
            ..create();
        },
        viewType: 'video_player');
  }

  Widget _vd() {
    return const AndroidView(viewType: 'video_player');
  }
}
