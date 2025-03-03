package tsarionovtimofey.tests.login.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tsarionovtimofey.tests.common.ViewModelKey
import tsarionovtimofey.tests.login.ui.LoginViewModel

@Module
interface LoginViewModelModule {
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @Binds
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}