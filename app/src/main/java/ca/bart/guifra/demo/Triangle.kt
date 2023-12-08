package ca.bart.guifra.demo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Triangle : GameObject() {

    private val paint = Paint()

    var color: Int
        get() = paint.color
        set(value) {
            paint.color = value
        }

    var size: Float = 50f

    init {

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1.5f / scaleY
    }


    private val sides = 4
    private val maxSides = 7 // The maximum number of sides for the largest polygon

    override fun internalDraw(canvas: Canvas) {
        drawNewPolygon(canvas, sides, size)
    }

    private fun drawNewPolygon(canvas: Canvas, sides: Int, size: Float) {
        if (sides < 3 ) return // Condition d'arrêt de la récursion

        canvas.save()
        val angle1 = 180f - (((sides - 2f) * 180f) / (2*sides))
        val angle2 = ((sides - 2f) * 180f) / sides
        val cornersAngle = 360f / sides
        val radAngle = angle2 * (Math.PI / 180f)
        val length = sqrt(size.pow(2f) + size.pow(2f) - (2f * size * size) * cos(radAngle))

        repeat(sides) {
            canvas.save()
            canvas.translate(length.toFloat(), 0f)
            canvas.rotate(angle1)
            canvas.drawLine(0f, 0f, size, 0f, paint)
            canvas.rotate(angle2)
            canvas.drawLine(0f, 0f, size, 0f, paint)

            drawNewPolygon(canvas, sides - 1, size / 2 )

            canvas.restore()
            canvas.rotate(cornersAngle)
        }

        canvas.restore()

    }


}