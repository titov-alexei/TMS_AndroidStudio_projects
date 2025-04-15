package com.example.firstapp.lesson20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.firstapp.R
import com.example.firstapp.databinding.ActivityLesson20Binding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Lesson20 : AppCompatActivity() {

    private var binding: ActivityLesson20Binding? = null
    private var fragmentCount = 2
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLesson20Binding.inflate(layoutInflater)
        setContentView(binding?.root)

        changeTextWithBinding() //Урок 20 задание 1 + Snackbar
        //addTabWithPager() //Урок 20 задача 2
        addDynamicFragment() //Урок 20. ДЗ 1
    }

    //Задание 1
    private fun changeTextWithBinding() {
        binding?.btnLesson20Task1?.setOnClickListener {

            val currentText = binding?.tvLesson20Task1?.text.toString()
            binding?.tvLesson20Task1?.text = getString(R.string.hello_world)

            Snackbar.make(findViewById(R.id.LinearLayoutL20),
                "Текст изменен",
                Snackbar.LENGTH_LONG
            ).setAction("Отменить") {
                binding?.tvLesson20Task1?.text = currentText
            }.show()


        }
    }

    //Задание 2. Сделай экран с TabLayout и ViewPager2. В каждом отдельный фрагмент
    private fun addTabWithPager() {
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        // Создание адаптера
        viewPager.adapter = MyPagerAdapter(this)

        // Связывание TabLayout с ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.fragment_a)
                1 -> getString(R.string.fragment_b)
                else -> "Таб $position"
            }
            tab.icon = when (position) {
                0 -> ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground)
                1 -> ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground)
                else -> null
            }
        }.attach()
    }

    private fun addDynamicFragment() {
        //Инициализируем viewPager и TabLaout
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        //Создаем адаптер и назначаем его viewPager
        adapter = MyPagerAdapter(this)
        viewPager.adapter = adapter

        //
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
            tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_launcher_foreground)
        }.attach()

        //Кнопка с добавлением фрагмента
        binding?.btnAddFragment?.setOnClickListener {
            addNewFragment()
        }
        //Кнопка с удалением фрагмента
        binding?.btnRemoveFragment?.setOnClickListener {
            removeFragment()
        }
    }

    private fun addNewFragment() {
        fragmentCount++
        adapter.addFragment(DynamicFragment.newInstance(fragmentCount))

        //Обновляем (перерисовываем) tablayoutmediator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    private fun removeFragment() {
        fragmentCount--
        adapter.removeFragment(fragmentCount)
    }

}