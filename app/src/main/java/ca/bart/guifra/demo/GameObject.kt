package ca.bart.guifra.demo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint

abstract class GameObject : Drawable {

    val debugPaint = Paint()

    init {

        debugPaint.strokeWidth = 3f
        debugPaint.style = Paint.Style.STROKE
    }

    var x = 0f
        set(value) {
            field = value
            onTransformUpdate()
        }

    var y = 0f
        set(value) {
            field = value
            onTransformUpdate()
        }

    var rotation = 0f
        set(value) {
            field = value
            onTransformUpdate()
        }

    var scaleX = 1f
        set(value) {
            field = value
            onTransformUpdate()
        }

    var scaleY = 1f
        set(value) {
            field = value
            onTransformUpdate()
        }

    val inverseMatrix = Matrix()

    protected open fun onTransformUpdate():Unit { }

    override fun draw(canvas: Canvas) {

        canvas.save()

        canvas.translate(x, y)
        canvas.rotate(rotation)
        canvas.scale(scaleX, scaleY)

        canvas.matrix.invert(inverseMatrix)

        internalDraw(canvas)

        canvas.restore()
    }

    protected abstract fun internalDraw(canvas: Canvas)

    protected fun debugDraw(canvas: Canvas) {

        debugPaint.color = Color.GREEN
        canvas.drawLine(0f, 0f, 10f, 0f, debugPaint)
        debugPaint.color = Color.RED
        canvas.drawLine(0f, 0f, 0f, 10f, debugPaint)
    }

    open fun onTouchBegin(touchId:Int, x:Float, y:Float):Unit { }
    open fun onTouchMove(touchId:Int, x:Float, y:Float):Unit { }
    open fun onTouchEnd(touchId:Int, x:Float, y:Float):Unit { }

}
