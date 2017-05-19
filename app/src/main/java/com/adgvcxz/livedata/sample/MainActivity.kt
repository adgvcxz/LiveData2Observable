package com.adgvcxz.livedata.sample

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import com.adgvcxz.livedata.observable.toObservable
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.text
import kotlinx.android.synthetic.main.activity_main.*

/**
 * zhaowei
 * Created by zhaowei on 2017/5/19.
 */

class MainActivity : LifecycleActivity() {

    private val number = MutableLiveData<Int>().also { it.value = 1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.clicks()
                .subscribe { number.value = number.value!! + 1 }
        number.toObservable(this).map { "$it" }
                .subscribe(textView.text())
    }
}