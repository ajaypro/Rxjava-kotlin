package com.rxjava2.android.samples.ui.operators

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rx2androidnetworking.Rx2AndroidNetworking
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.model.User
import com.rxjava2.android.samples.utils.AppConstant
import com.rxjava2.android.samples.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ajay Deepak on 20-06-2019.
 */
class BufferExampleActivity : AppCompatActivity() {

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

    fun doSomeWork() {
        val bufferedValue = getCricketFansObservable().buffer(2,1)

        bufferedValue
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer <List<List<User>>>{

                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "$d")
                    }

                    override fun onNext(users: List<List<User>>) {
                        for ((i, user) in users.withIndex()) {
                            textView.append(AppConstant.LINE_SEPARATOR)
                            textView.append(user[i].id.toString())
                            textView.append(AppConstant.LINE_SEPARATOR)
                            textView.append(user[i].firstname + user[i].lastname)
                            textView.append(AppConstant.LINE_SEPARATOR)
                            textView.append(user[i].isFollowing.toString())
                            Log.d(TAG, " onNext : value : ${user[i].id}")

                        }

                    }

                    override fun onError(e: Throwable) {
                        Utils.logError(TAG, e.cause!!)
                    }
                })
    }

    /**
     * This observable return the list of User who loves cricket
     */
    private fun getCricketFansObservable(): Observable <List<User>> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllCricketFans")
                .build()
                .getObjectListObservable(User::class.java)
                .subscribeOn(Schedulers.io())


    }
}