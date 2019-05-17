package com.sub6resources.fluttermicrophone

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

const val AUDIO_SOURCE = MediaRecorder.AudioSource.MIC
const val SAMPLE_RATE = 44100
const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
const val ENCODING = AudioFormat.ENCODING_PCM_16BIT
@SuppressLint("NewApi")
val BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, ENCODING)



@TargetApi(16)
class FlutterMicrophonePlugin : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_microphone")
            channel.setMethodCallHandler(FlutterMicrophonePlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
            "getAudioStream" -> result.success(getAudioStream())
            else -> result.notImplemented()
        }
    }

    private fun getAudioStream(): ShortArray {


        val audioRecord = AudioRecord(AUDIO_SOURCE, SAMPLE_RATE, CHANNEL_CONFIG, ENCODING, BUFFER_SIZE)

        val shortArray = ShortArray(BUFFER_SIZE)
        audioRecord.read(shortArray, 0, BUFFER_SIZE)

        return shortArray
    }
}
