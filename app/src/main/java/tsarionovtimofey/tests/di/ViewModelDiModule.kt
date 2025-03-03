package tsarionovtimofey.tests.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tsarionovtimofey.tests.common.ViewModelKey
import tsarionovtimofey.tests.MainViewModel

@Module
interface ViewModelDiModule {
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}