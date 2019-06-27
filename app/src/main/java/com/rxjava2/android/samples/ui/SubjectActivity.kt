package com.rxjava2.android.samples.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.ui.subjects.PublishSubjectActivity

/**
 * Created by Ajay Deepak on 20-06-2019.
 */
class SubjectActivity:AppCompatActivity() {

    companion object {
        private const val TAG = "SubjectActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)
    }

    fun startPublishSubject(view: View){
        startActivity(Intent(this@SubjectActivity, PublishSubjectActivity::class.java))
    }


}