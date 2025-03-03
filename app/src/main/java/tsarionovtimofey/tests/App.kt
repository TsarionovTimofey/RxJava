package tsarionovtimofey.tests

import android.app.Application
import android.content.Context
import tsarionovtimofey.tests.di.AppComponent
import tsarionovtimofey.tests.di.DaggerAppComponent
import tsarionovtimofey.tests.login.di.LoginComponentDependencies
import tsarionovtimofey.tests.login.di.LoginComponentDependenciesProvider

class App : Application(), LoginComponentDependenciesProvider {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }

    override fun getDependencies(): LoginComponentDependencies = appComponent
}

fun Context.appComponent() = (this.applicationContext as App).appComponent