package tsarionovtimofey.tests.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tsarionovtimofey.tests.data.di.DataDiModule
import tsarionovtimofey.tests.login.di.LoginComponentDependencies
import tsarionovtimofey.tests.MainActivity
import tsarionovtimofey.tests.wiring.WiringDiModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataDiModule::class,
        NavigationDiModule::class,
        ViewModelDiModule::class,
        WiringDiModule::class
    ]
)
interface AppComponent : LoginComponentDependencies {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance appContext: Context): Builder
        fun build(): AppComponent
    }
}