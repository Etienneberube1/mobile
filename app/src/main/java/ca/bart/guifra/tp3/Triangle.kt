package ca.bart.guifra.tp3

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

class Triangle : GameObject(){

    private val paint = Paint()

    var color: Int
        get() = paint.color
        set(value) {
            paint.color = value
        }

    var size: Float = 50f  //R.attr.size

    init {

        paint.color = R.attr.color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1.5f / scaleY
    }


    private val sides = 5  //R.attr.sides

    override fun internalDraw(canvas: Canvas) {
        drawNewPolygon(canvas, sides, size)
    }

    private fun drawNewPolygon(canvas: Canvas, sides: Int, size: Float) {

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

            if(sides > 3)
            {
                drawNewPolygon(canvas, sides - 1, size / 2 )
            }


            canvas.restore()
            canvas.rotate(cornersAngle)
        }

        canvas.restore()

    }

    private var rotationSpeed = 30
    private  var currentRot = 0f
    override fun update() {
        val rot = rotationSpeed * CustomActivity.SECS_PER_FRAME
        currentRot = (currentRot + rot ) % 360
        rotation = currentRot
    }


}