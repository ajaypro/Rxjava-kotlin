package com.rxjava2.android.samples.ui.operators

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Ajay Deepak on 18-06-2019.
 */


    fun main(){
        Observable.timer(2, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap Observable.create<String> { emitter ->
                        Log.d("TimerExample", "Create")
                        emitter.onNext("MindOrks")
                        emitter.onComplete()
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("TimerExample", it)
                }
    }
