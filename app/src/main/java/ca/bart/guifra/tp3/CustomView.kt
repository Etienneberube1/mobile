package ca.bart.guifra.tp3

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

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