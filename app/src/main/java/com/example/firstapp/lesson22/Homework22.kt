package com.example.firstapp.lesson22

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityHomework22Binding
import com.example.firstapp.databinding.ActivityLesson22Binding
import com.example.firstapp.lesson22.multiAdapter.Elements
import com.example.firstapp.lesson22.multiAdapter.MultiTypeAdapter
import com.example.firstapp.lesson22.personAdapter.RecyclerViewAdapter
import java.util.jar.Attributes.Name

class Homework22 : AppCompatActivity() {

    private var binding: ActivityHomework22Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomework22Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        val list = listOf(
            Elements.NameText("А.С. Пушкин",
                "Буря мглою небо кроет,\n" +
                        "Вихри снежные крутя;\n" +
                        "То, как зверь, она завоет,\n" +
                        "То заплачет, как дитя,\n" +
                        "То по кровле обветшалой\n" +
                        "Вдруг соломой зашумит,\n" +
                        "То, как путник запоздалый,\n" +
                        "К нам в окошко застучит."),
            Elements.ImageText(getDrawable(R.drawable.smile1), "Смайлик номер 1"),
            Elements.TextButton("Не очень длинный текст, с каким-то смыслом", "Текст кнопки"),
            Elements.NameText("М.Ю. Лермонтов",
                "Скажи-ка, дядя, ведь не даром\n" +
                        "Москва, спаленная пожаром,\n" +
                        "Французу отдана?\n" +
                        "Ведь были ж схватки боевые,\n" +
                        "Да, говорят, еще какие!\n" +
                        "Недаром помнит вся Россия\n" +
                        "Про день Бородина!"),
            Elements.ImageText(getDrawable(R.drawable.smile2), "Смайлик номер 2"),
            Elements.TextButton("Второй не очень длинный текст, с каким-то смыслом", "Текст второй кнопки")
        )

        val copyList = list.toMutableList()

        binding?.multiRecyclerView?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val position = parent.getChildAdapterPosition(view)
                outRect.top = if (position == 0) 0 else resources.getDimensionPixelSize(R.dimen.item_margin)
            }
        })

        binding?.multiRecyclerView?.layoutManager = LinearLayoutManager(this)
        val adapter = MultiTypeAdapter(list)
        binding?.multiRecyclerView?.adapter = adapter

        binding?.btnUpdateList?.setOnClickListener {
            copyList.shuffle()
            adapter.updateView(copyList)
        }
    }


}