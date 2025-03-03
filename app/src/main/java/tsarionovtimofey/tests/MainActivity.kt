package tsarionovtimofey.tests

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import tsarionovtimofey.animations.rxjava.R
import tsarionovtimofey.tests.navigation.NavigationImpl
import tsarionovtimofey.tests.common.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationImpl: NavigationImpl

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        navigationImpl.setNavigator(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel
    }
}