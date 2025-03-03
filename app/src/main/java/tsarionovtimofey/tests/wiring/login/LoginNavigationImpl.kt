package tsarionovtimofey.tests.wiring.login

import androidx.fragment.app.Fragment
import tsarionovtimofey.tests.dashboard.DashboardFragment
import tsarionovtimofey.tests.login.di.LoginNavigation
import tsarionovtimofey.tests.navigation.Navigation

class LoginNavigationImpl(
    private val navigation: Navigation
) : LoginNavigation {
    override fun next() {
        navigation.navigateTo(DashboardFragment.newInstance()) // todo
    }
}