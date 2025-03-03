package tsarionovtimofey.tests.login.di

import tsarionovtimofey.tests.domain.TokenStorage
import tsarionovtimofey.tests.domain.repository.rx.AuthRepositoryRx

interface LoginComponentDependencies {
    fun getAuthRepositoryRx(): AuthRepositoryRx
    fun getTokenStorage(): TokenStorage
    fun getLoginNavigation(): LoginNavigation
}