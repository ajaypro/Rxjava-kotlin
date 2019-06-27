package com.rxjava2.android.samples.ui.subjects

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rx2androidnetworking.Rx2AndroidNetworking
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.model.User
import com.rxjava2.android.samples.utils.AppConstant
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by Ajay Deepak on 20-06-2019.
 */
class PublishSubjectActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "BufferExampleActivity"
    }

    private lateinit var btn: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        btn = findViewById(R.id.btn)
        textView = findViewById(R.id.textView)
        btn.setOnClickListener {
            doSomeWork()
        }
    }


    private fun doSomeWork() {
        var publishSubject = PublishSubject.create<List<User>>()

        getCricketFansObservable()
                .subscribe(publishSubject)

        publishSubject.subscribe(getFirstObserver())
        publishSubject.subscribe(getSecondObserver())

    }

    private fun getFirstObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " First onSubscribe : " + d.isDisposed)
            }

            override fun onNext(user: List<User>) {
                for (users in user) {
                    textView.append(AppConstant.LINE_SEPARATOR)
                    textView.append(users.id.toString())
                    textView.append(AppConstant.LINE_SEPARATOR)
                    textView.append(users.firstname + users.lastname)
                    textView.append(AppConstant.LINE_SEPARATOR)
                    textView.append(users.isFollowing.toString())
                }

                Log.d(TAG, " First onNext value : $user")
            }

            override fun onError(e: Throwable) {
                textView.append(" First onError : " + e.message)
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " First onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" First onComplete")
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " First onComplete")
            }
        }
    }

    private fun getSecondObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {

            override fun onSubscribe(d: Disposable) {
                textView.append(" Second onSubscribe : isDisposed :" + d.isDisposed)
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed)
                textView.append(AppConstant.LINE_SEPARATOR)
            }

            override fun onNext(user: List<User>) {
                for (users in user) {
                    textView.append(AppConstant.LINE_SEPARATOR)
                    textView.append(users.id.toString())
                    textView.append(AppConstant.LINE_SEPARATOR)
                    textView.append(users.firstname + users.lastname)
                    textView.append(AppConstant.LINE_SEPARATOR)
                    textView.append(users.isFollowing.toString())
                }

                Log.d(TAG, " Second onNext value : $user")
            }

            override fun onError(e: Throwable) {
                textView.append(" Second onError : " + e.message)
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " Second onError : " + e.message)
            }

            override fun onComplete() {
                textView.append(" Second onComplete")
                textView.append(AppConstant.LINE_SEPARATOR)
                Log.d(TAG, " Second onComplete")
            }
        }
    }

    /**
     * This observable return the list of User who loves cricket
     */
    private fun getCricketFansObservable(): Observable<List<User>> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllCricketFans")
                .build()
                .getObjectListObservable(User::class.java)
                .subscribeOn(Schedulers.io())

    }

}