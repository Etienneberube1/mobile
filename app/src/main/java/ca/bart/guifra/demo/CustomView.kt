package ca.bart.guifra.demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle), Updatable {

    companion object {

        const val TAG = "CustomView"
    }

    var cx = 0f
    var cy = 0f
    var radius = 0f
    var scale = 1f
    //val screenToView = Matrix()

    val triangle = Triangle()
    var gameObjects: List<GameObject> = emptyList()

    init {

        //gameObjects += chrono
        gameObjects += triangle

        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyle, 0)
        try {
            //val color = attributes.getColor(R.styleable.CustomView_color, Color.RED)
            //val time = attributes.getFloat(R.styleable.CustomView_time, 2f)
        } finally {
            attributes.recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        cx = w * .5f
        cy = h * .5f

        radius = min(cx, cy)

        scale = radius / 100f

        triangle.x = cx
        triangle.y = cy
        triangle.rotation = -45f
        triangle.scaleX = radius / 100f
        triangle.scaleY = radius / 100f


    }

    override fun update() {

        gameObjects.filterIsInstance<Updatable>().forEach(Updatable::update)
    }

    override fun onDraw(canvas: Canvas) {

        gameObjects.forEach { it.draw(canvas) }

    }


}