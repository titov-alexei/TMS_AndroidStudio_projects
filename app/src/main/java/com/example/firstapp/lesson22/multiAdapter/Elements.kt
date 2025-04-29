package com.example.firstapp.lesson22.multiAdapter

import android.graphics.drawable.Drawable
import android.widget.Button

sealed class Elements {
    data class NameText(val authorName: String, val textAuthor: String): Elements()
    data class ImageText(val image: Drawable?, val text: String): Elements()
    data class TextButton(val text: String, val btnText: String): Elements()
}