package ca.bart.guifra.tp3

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import ca.bart.guifra.tp3.databinding.CustomViewBinding



class CustomActivity : Activity(), Updatable {

    companion object {

        const val TAG = "CustomActivity"

        const val FRAME_RATE = 60
        const val SECS_PER_FRAME = 1f / 60
        const val MILLIS_PER_FRAME = 1000L / FRAME_RATE
        const val NANOS_PER_FRAME = MILLIS_PER_FRAME * 1e6.toLong()
    }

    val handler = Handler(Looper.getMainLooper())
    val updateTask = Runnable { update() }

    var previousTime = 0L
    var lag = 0L

    val binding by lazy { CustomViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }


    override fun onStart() {
        super.onStart()

        previousTime = System.nanoTime()
        requestUpdate()
    }

    override fun onStop() {
        super.onStop()

        cancelUpdate()
    }

    override fun update() {



        val currentTime = System.nanoTime()
        val elapsedTime = currentTime - previousTime
        previousTime = currentTime
        lag += elapsedTime

        while (lag >= NANOS_PER_FRAME) {

            Log.d(TAG, "update()")
            binding.custom.update()
            binding.custom.invalidate()
            lag -= NANOS_PER_FRAME
        }

        requestUpdate()
    }

    fun requestUpdate() {
        handler.postDelayed(updateTask, MILLIS_PER_FRAME)
    }

    fun cancelUpdate() {
        handler.removeCallbacks(updateTask)
    }


}