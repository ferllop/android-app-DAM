package com.example.pac_desarrollo.activity3

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.pac_desarrollo.R

class MusicPlayerService(): Service() {
    private var thread: MyThread? = MyThread()
    private val myBinder = MyLocalBinder()
    private val TAG = "ilerna: MusicPlayerService"

    fun play() {
        Log.d(TAG, "play: ")
        thread?.setToPlay()
    }

    fun pause() {
        Log.d(TAG, "pause: ")
        thread?.setToPause()
    }

    fun sleep(){
        Log.d(TAG, "sleep: ")
        thread?.setToSleep()
    }


    override fun onCreate() {
        super.onCreate()

        thread?.start()
        Log.d(TAG, android.os.Process.getThreadPriority(android.os.Process.myTid()).toString());

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "service onStartCommand")
        // If we get killed, after returning from here, restart
        return START_NOT_STICKY
    }



    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    override fun onDestroy() {
        thread?.setToDestroy()
        thread?.interrupt()
        thread = null
        super.onDestroy()
    }

    inner class MyLocalBinder : Binder() {
        fun getService() : MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    enum class PlayingState{
        NULL,
        PLAY,
        PAUSE,
        SLEEP,
        DESTROY
    }

    inner class MyThread:Thread("Player") {
        private val TAG = "ilerna: THREAD"
        public var state = PlayingState.NULL
        private lateinit var mediaPlayer: MediaPlayer
        override fun run(){
            mediaPlayer = MediaPlayer.create(this@MusicPlayerService, R.raw.decline)
            Log.d(
                TAG,
                "run: " + android.os.Process.getThreadPriority(android.os.Process.myTid()).toString()
            );

            while (state != PlayingState.DESTROY) {
                when(state){
                    PlayingState.PLAY -> play()
                    PlayingState.PAUSE -> pause()
                    PlayingState.SLEEP -> sleep()
                    else -> {}
                }
            }

            mediaPlayer.stop()
            mediaPlayer.release()


        }

        fun setToDestroy(){
            Log.d(TAG, "setToDestroy:")
            state = PlayingState.DESTROY
        }

        fun setToPlay(){
            Log.d(TAG, "setToPlay: ")
            state = PlayingState.PLAY
        }

        fun setToPause(){
            Log.d(TAG, "setToPause:")
            state = PlayingState.PAUSE
        }

        fun setToSleep() {
            Log.d(TAG, "setToSleep: ")
            state = PlayingState.SLEEP
        }

        fun play() {
            Log.d(TAG, "play: ")
            state = PlayingState.NULL
            if ( mediaPlayer.isPlaying){
                mediaPlayer.seekTo(0)
            }
            mediaPlayer?.start()
            if (mediaPlayer.isPlaying){
                Log.d(TAG, "play: isPlaying")
            } else {
                Log.d(TAG, "play: isNotPlaying")
            }
            Log.d("fer", "play")
        }

        fun pause() {
            Log.d(TAG, "pause: ")
            state = PlayingState.NULL
            mediaPlayer.pause()
        }

        fun sleep(){
            Log.d(TAG, "sleep: START")
            pause()
            Thread.sleep(15000)
            play()
            Log.d(TAG, "sleep: ENDS")
            state = PlayingState.NULL
        }

    }
}


