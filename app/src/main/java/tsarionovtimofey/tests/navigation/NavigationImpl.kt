package tsarionovtimofey.tests.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import tsarionovtimofey.animations.rxjava.R

class NavigationImpl : Navigation {
    private var navigator: AppCompatActivity? = null

    fun setNavigator(activity: AppCompatActivity) {
        navigator = activity
    }

    override fun navigateTo(fragment: Fragment) {
        val transaction = navigator?.supportFragmentManager?.beginTransaction() ?: return
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}