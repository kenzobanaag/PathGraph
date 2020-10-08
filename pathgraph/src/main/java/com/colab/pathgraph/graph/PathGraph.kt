package com.colab.pathgraph.graph

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.colab.pathgraph.R
import com.colab.pathgraph.location.CoordinateUtil
import com.colab.pathgraph.model.PathPoint
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.abs

/*
* fixme: make path and paint properties so the values can be changed.
* */
class PathGraph(context : Context, attrs : AttributeSet) : View(context, attrs){
    lateinit var points : List<PathPoint>
    private val path = Path()
    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        isAntiAlias = true
        strokeWidth = STROKE_WIDTH
        style = Paint.Style.STROKE
    }

    private var midX = 0f
    private var midY = 0f
    private var graphHeight = 0f
    private var graphWidth = 0f

    companion object {
        //we dont want to hit borders so we want to subtract the stroke width to the width and height of the view.
        private const val STROKE_WIDTH = 50f
        private const val OFFSET = STROKE_WIDTH * 3 //increase multiplier to increase box clearance
    }

    init {
        val cornerPathEffect = CornerPathEffect(STROKE_WIDTH)
        val composePathEffect = ComposePathEffect(cornerPathEffect, cornerPathEffect)
        paint.pathEffect = composePathEffect
    }

    fun setPathColor(color : Int) { paint.color = color }

    fun setPathColor(gradient : Shader) { paint.shader = gradient }

    fun setBackground(color : Int) { this.setBackgroundColor(color) }

    /*
    * Description: This will scale the picture to fit the box perfectly
    *              If the path is too small, scale to become bigger, if the path is too big, scale to get it smaller
    * */
    private fun scale() {
        calculateBounds()

        val scalerX = (width - OFFSET) / graphWidth
        val scalerY = (height - OFFSET) / graphHeight

        for(index in points.indices) {
            points[index].x = (scalerX * points[index].x)
            points[index].y = (scalerY * points[index].y)
        }
    }

    /*
    * Description: Move the path to the center of our screen.
    *
    * note: We need the center of the dimensions of our screen.
    * */
    private fun translate() {
        calculateBounds()

        val desiredPoint = PathPoint((width / 2).toFloat(), (height / 2).toFloat())
        val point = PathPoint(midX,midY)
        val translationPoint = CoordinateUtil.computeTranslationPoint(point, desiredPoint)

        for(index in points.indices) {
            val locationPoint = PathPoint(points[index].x, points[index].y)
            val translatedPoint = CoordinateUtil.translation(locationPoint, translationPoint)
            points[index].x = translatedPoint.x
            points[index].y = translatedPoint.y
        }
    }

    /*
    * Needs to be called before a translation or scale call is made.
    * */
    private fun calculateBounds() {
        var maxX = points[0].x
        var maxY = points[0].y
        var minX = points[0].x
        var minY = points[0].y

        for(index in points.indices) {
            maxX = max(points[index].x, maxX)
            maxY = max(points[index].y, maxY)
            minX = min(points[index].x, minX)
            minY = min(points[index].y, minY)
        }

        graphWidth = abs(maxX) + abs(minX)
        graphHeight = abs(maxY) + abs(minY)

        midX = maxX - (graphWidth/2)
        midY = maxY - (graphHeight/2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            if(::points.isInitialized && points.size > 1) {//we need at least 2 points to create a path
                scale()
                translate()
                drawPath(canvas)
            }
        }
    }

    private fun drawPath(canvas : Canvas) {
        path.moveTo(points[0].x, points[0].y)

        for(index in 1 until points.size) {
            path.lineTo(points[index].x, points[index].y)
        }

        canvas.drawPath(path, paint)
    }
}