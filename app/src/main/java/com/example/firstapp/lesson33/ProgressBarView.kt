package com.example.firstapp.lesson33

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min

class ProgressBarView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
    private var progress = 0
    private val cornerRadius = 10f

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    val minWidth = 500f
    val minHeight = 100f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthWithPadding = (minWidth + paddingLeft + paddingRight).toInt()
        val heightWithPadding = (minHeight + paddingTop + paddingBottom).toInt()

        val widthMeasureSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthGetSize = MeasureSpec.getSize(widthMeasureSpec)
        val finalWidth =
            when(widthMeasureSpecMode) {
                MeasureSpec.AT_MOST -> {
                    min(widthWithPadding, widthGetSize)
                }

                MeasureSpec.EXACTLY -> {
                    widthGetSize
                }

                else -> widthWithPadding
            }


        val heightMeasureSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightGetSize = MeasureSpec.getSize(heightMeasureSpec)
        val finalHeight =
            when(heightMeasureSpecMode) {
                MeasureSpec.AT_MOST -> {
                    min(heightWithPadding, heightGetSize)
                }

                MeasureSpec.EXACTLY -> {
                    heightGetSize
                }

                else -> heightWithPadding
            }

        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(1f,1f, width.toFloat(), height.toFloat(),cornerRadius+2f,cornerRadius+2f,borderPaint)
        canvas.drawRoundRect(0f,0f, width.toFloat()*(progress/100f), height.toFloat(),cornerRadius,cornerRadius, progressPaint)
    }

    fun changeProgress(newProgress: Int) {
        progress = newProgress
        invalidate()
    }
}