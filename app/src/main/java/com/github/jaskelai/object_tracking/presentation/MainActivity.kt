package com.github.jaskelai.object_tracking.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.presentation.di.MainSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import com.github.jaskelai.object_tracking.presentation.utils.ext.getAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var mainSubcomponent: MainSubcomponent

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainSubcomponent = getAppComponent()
            .mainSubcomponentBuilder()
            .build()
        mainSubcomponent.inject(this)

        super.onCreate(savedInstanceState)
    }
}
