package com.adgvcxz.livedata.observable

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

/**
 * zhaowei
 * Created by zhaowei on 2017/5/19.
 */

class LiveDataObservable<T>(private val owner: LifecycleOwner?, private val data: LiveData<T>) : Observable<T>() {

    override fun subscribeActual(observer: Observer<in T>) {
        val liveDataObserver = LiveDataObserver(data, observer)
        observer.onSubscribe(liveDataObserver)
        if (owner == null) {
            data.observeForever(liveDataObserver)
        } else {
            data.observe(owner, liveDataObserver)
        }
    }

    class LiveDataObserver<T>(private val data: LiveData<T>, private val observer: Observer<in T>) : MainThreadDisposable(), android.arch.lifecycle.Observer<T> {

        override fun onDispose() {
            data.removeObserver(this)
        }

        override fun onChanged(t: T?) {
            observer.onNext(t)
        }
    }
}

fun <T> LiveData<T>.toObservable(owner: LifecycleOwner): Observable<T> {
    return LiveDataObservable(owner, this)
}

fun <T> LiveData<T>.toForeverObservable(): Observable<T> {
    return LiveDataObservable(null, this)
}