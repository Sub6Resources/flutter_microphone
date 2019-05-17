#import "FlutterMicrophonePlugin.h"
#import <flutter_microphone/flutter_microphone-Swift.h>

@implementation FlutterMicrophonePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterMicrophonePlugin registerWithRegistrar:registrar];
}
@end
