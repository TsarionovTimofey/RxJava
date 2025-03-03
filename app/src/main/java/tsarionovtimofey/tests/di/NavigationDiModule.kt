package tsarionovtimofey.tests.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import tsarionovtimofey.tests.navigation.Navigation
import tsarionovtimofey.tests.navigation.NavigationImpl
import javax.inject.Singleton

@Module(includes = [NavigationBindsModule::class])
object NavigationDiModule {
    @Singleton
    @Provides
    fun providesNavigation(): NavigationImpl = NavigationImpl()
}

@Module
interface NavigationBindsModule {
    @Binds
    fun bindsNavigation(impl: NavigationImpl): Navigation
}