package com.github.jaskelai.object_tracking.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import com.github.jaskelai.object_tracking.getAppComponent
import com.github.jaskelai.object_tracking.presentation.di.MainSubcomponent
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var mainSubcomponent: MainSubcomponent

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainSubcomponent = getAppComponent().mainSubcomponentBuilder()
            .build()
        mainSubcomponent.inject(this)

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        observeNavigation()
    }

    private fun observeNavigation() {
        mainViewModel.isAuthed.observe(this) {
            when (it) {
                AuthState.NOT_AUTHED -> findNavController(R.id.nav_host_fragment_main).setGraph(R.navigation.nav_graph_auth)
                AuthState.AUTHED_WITH_SMS -> findNavController(R.id.nav_host_fragment_main).setGraph(R.navigation.nav_graph_set_bio)
                AuthState.FULL_AUTHED -> findNavController(R.id.nav_host_fragment_main).setGraph(R.navigation.nav_graph_authed)
            }
        }
    }
}

fun Fragment.getMainActivity(): MainActivity = activity as MainActivity

fun Fragment.getMainActivitySubcomponent(): MainSubcomponent = getMainActivity().mainSubcomponent
