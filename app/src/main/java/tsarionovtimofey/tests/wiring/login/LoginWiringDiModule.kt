package tsarionovtimofey.tests.wiring.login

import dagger.Module
import dagger.Provides
import tsarionovtimofey.tests.login.di.LoginNavigation
import tsarionovtimofey.tests.navigation.Navigation

@Module
class LoginWiringDiModule {
    @Provides
    fun provideLoginNavigation(
        navigation: Navigation
    ): LoginNavigation = LoginNavigationImpl(navigation = navigation)
}