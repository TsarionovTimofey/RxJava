package tsarionovtimofey.tests.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import tsarionovtimofey.tests.data.AuthApiRx
import tsarionovtimofey.tests.data.IsUserSignedInUseCaseImpl
import tsarionovtimofey.tests.data.repository.rx.AuthApiRxImpl
import tsarionovtimofey.tests.domain.repository.rx.AuthRepositoryRx
import tsarionovtimofey.tests.data.repository.rx.AuthRepositoryRxImpl
import tsarionovtimofey.tests.domain.TokenStorage
import tsarionovtimofey.tests.data.repository.rx.TokenStorageImpl
import tsarionovtimofey.tests.domain.usecase.IsUserSignedInUseCase
import tsarionovtimofey.tests.domain.usecase.LoginUserUseCaseRx

@Module(includes = [TokenStorageDiModule::class, AuthDiModule::class, LoginDiModule::class])
class DataDiModule

@Module
class TokenStorageDiModule {
    @Provides
    fun provideTokenStoragePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("token_storage", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideTokenStorage(
        prefs: SharedPreferences
    ): TokenStorage = TokenStorageImpl(
        prefs = prefs
    )
}

@Module
class AuthDiModule {
    @Provides
    fun provideAuthApiRx(): AuthApiRx = AuthApiRxImpl()

    @Provides
    fun provideAuthRepositoryRx(
        api: AuthApiRx
    ): AuthRepositoryRx = AuthRepositoryRxImpl(
        api = api
    )
}

@Module
class LoginDiModule {

    @Provides
    fun isUserSignedInUseCase(
        tokenStorage: TokenStorage
    ): IsUserSignedInUseCase = IsUserSignedInUseCaseImpl(
        tokenStorage = tokenStorage
    )
}