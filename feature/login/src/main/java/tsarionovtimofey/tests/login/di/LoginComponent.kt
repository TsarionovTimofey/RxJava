package tsarionovtimofey.tests.login.di

import dagger.Component
import tsarionovtimofey.tests.login.ui.LoginFragment

@Component(
    dependencies = [LoginComponentDependencies::class],
    modules = [LoginViewModelModule::class, LoginDiModule::class]
)
interface LoginComponent {
    fun inject(fragment: LoginFragment)
}