package com.example.firstapp.lesson34

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.firstapp.R

class ProgressBarCircle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var progress = 0f
    private val maxProgress = 100f
    private var centerX = 0f
    private var centerY = 0f



    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {

        //color = attrs
        strokeWidth = 20f
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.ProgressBarCircle,
            defStyle,
            0
        ).apply {
            try {
                progressPaint.color = getColor(
                    R.styleable.ProgressBarCircle_progressColor,
                    Color.RED
                )
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width.toFloat()/2,height.toFloat()/2, 200f,borderPaint)

        centerX = width / 2f
        centerY = height / 2f

        val sweepAngle = 360f * progress / maxProgress
        canvas.drawArc(    //Рисование арки
            centerX - 200f, //Понимаю что данные значения можно было в отдельную переменную занести
            centerY - 200f,
            centerX + 200f, centerY + 200f,
            -90f,  // начать сверху
            sweepAngle,
            false,
            progressPaint
        )

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = 500f + paddingLeft + paddingRight
        val desireHeight = 500f + paddingTop + paddingBottom

        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)
        val height = resolveSize(desireHeight.toInt(), heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    fun changeProgress(newProgress: Float) {
        progress = newProgress.coerceIn(0f, maxProgress)
        invalidate()
    }

    fun setProgress(newProgress: Float, animate: Boolean = true) {
        val clampedProgress = newProgress.coerceIn(0f, 100f)

        if (animate) {
            ValueAnimator.ofFloat(progress, clampedProgress).apply {
                duration = 800
                interpolator = AccelerateDecelerateInterpolator()
                addUpdateListener { animator ->
                    progress = animator.animatedValue as Float
                    progressPaint.color = getColorForProgress(progress)
                    invalidate()
                }
                start()
            }
        } else {
            progress = clampedProgress
            progressPaint.color = getColorForProgress(progress)
            invalidate()
        }
    }

    private fun getColorForProgress(progress: Float): Int {
        return when {
            progress < 30 -> interpolateColor(
                Color.RED, Color.YELLOW,
                progress / 30f
            )
            progress < 70 -> interpolateColor(
                Color.YELLOW, Color.parseColor("#FFA500"),
                (progress - 30) / 40f
            )
            else -> interpolateColor(
                Color.parseColor("#FFA500"), Color.GREEN,
                (progress - 70) / 30f
            )
        }
    }

    private fun interpolateColor(startColor: Int, endColor: Int, fraction: Float): Int {
        val a = (Color.alpha(endColor) - Color.alpha(startColor)) * fraction + Color.alpha(startColor)
        val r = (Color.red(endColor) - Color.red(startColor)) * fraction + Color.red(startColor)
        val g = (Color.green(endColor) - Color.green(startColor)) * fraction + Color.green(startColor)
        val b = (Color.blue(endColor) - Color.blue(startColor)) * fraction + Color.blue(startColor)
        return Color.argb(a.toInt(), r.toInt(), g.toInt(), b.toInt())
    }

}