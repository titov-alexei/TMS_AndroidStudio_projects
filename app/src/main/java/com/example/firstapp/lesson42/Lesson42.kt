package com.example.firstapp.lesson42


import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.firstapp.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class Lesson42 : AppCompatActivity() {

    lateinit var searchText: EditText
    lateinit var resultText: TextView
    val array = listOf("Kate", "Alex", "Liza", "Marina", "Alexandr", "Natali")
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson42)

        searchText = findViewById(R.id.editText)
        resultText = findViewById(R.id.textView)
        var tempList = mutableListOf<String>()

        val queryInput: Observable<String> = createTextObservable()

        val searchDisposable = queryInput
            .debounce(800, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { query ->
                simulateSearchRequest(query)
                    .onErrorReturnItem(emptyList())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { results ->
                tempList.clear()
                tempList.addAll(results)
                resultText.text = if (results.isEmpty()) {
                    "No result"
                } else {
                    results.joinToString("\n") { "- $it" }
                }
            }

        compositeDisposable.add(searchDisposable)
    }

    private fun createTextObservable(): Observable<String> {
        return Observable.create { emitter ->
            searchText.doAfterTextChanged {
                emitter.onNext(it?.toString() ?: "")
            }
        }
    }

    private fun simulateSearchRequest(query: String): Observable<List<String>> {
        return Observable.fromCallable {
            Thread.sleep((100..500).random().toLong())
            if (query == "Error") {
                throw RuntimeException("The server is not available")
            }
            array.filter { it.contains(query, ignoreCase = true) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}