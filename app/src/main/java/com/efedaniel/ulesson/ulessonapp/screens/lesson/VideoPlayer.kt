package com.efedaniel.ulesson.ulessonapp.screens.lesson

import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

class VideoPlayer(
    private val listener: VideoPlayerListener
) {

    private var exoPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var playbackPosition: Long = 0

    fun initializePlayer(context: Context, videoLink: String) {
        if (exoPlayer != null) return

        val trackSelector = DefaultTrackSelector(context)
        trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd())
        exoPlayer = SimpleExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
        exoPlayer?.run {
            listener.onNewExoPlayer(this)
            playWhenReady = this@VideoPlayer.playWhenReady
            addMediaItem(MediaItem.fromUri(videoLink))
            seekTo(playbackPosition)
            prepare()
        }
    }

    fun releasePlayer() {
        exoPlayer?.let {
            playWhenReady = it.playWhenReady
            playbackPosition = it.currentPosition
            it.release()
        }
        exoPlayer = null
    }
}

interface VideoPlayerListener {
    fun onNewExoPlayer(exoPlayer: SimpleExoPlayer)
}
