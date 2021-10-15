package com.efedaniel.ulesson.ulessonapp.screens.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.efedaniel.ulesson.base.BaseViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import javax.inject.Inject

class LessonViewModel @Inject constructor() : BaseViewModel(), VideoPlayerListener {

    val videoPlayer = VideoPlayer(this)

    private val _exoPlayer = MutableLiveData<SimpleExoPlayer>()
    val exoPlayer: LiveData<SimpleExoPlayer>
        get() = _exoPlayer

    override fun onNewExoPlayer(exoPlayer: SimpleExoPlayer) {
        _exoPlayer.value = exoPlayer
    }

    override fun onCleared() {
        videoPlayer.releasePlayer()
        super.onCleared()
    }
}
