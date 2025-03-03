package tsarionovtimofey.tests.login.di

import dagger.Module
import dagger.Provides
import tsarionovtimofey.tests.domain.TokenStorage
import tsarionovtimofey.tests.domain.repository.rx.AuthRepositoryRx
import tsarionovtimofey.tests.domain.usecase.LoginUserUseCaseRx
import tsarionovtimofey.tests.login.domain.IsPasswordValidUseCase
import tsarionovtimofey.tests.login.domain.IsPasswordValidUseCaseImpl
import tsarionovtimofey.tests.login.domain.IsUserNameValidUseCase
import tsarionovtimofey.tests.login.domain.IsUserNameValidUseCaseImpl
import tsarionovtimofey.tests.login.domain.LoginUserUseCaseRxImpl

@Module
class LoginDiModule {
    @Provides
    fun provideLoginUserUseCase(
        authRepositoryRx: AuthRepositoryRx,
        tokenStorage: TokenStorage
    ): LoginUserUseCaseRx = LoginUserUseCaseRxImpl(
        authRepository = authRepositoryRx,
        tokenStorage = tokenStorage
    )

    @Provides
    fun provideIsPasswordValidUseCase(): IsPasswordValidUseCase = IsPasswordValidUseCaseImpl()

    @Provides
    fun provideIsUsernameValidUseCase(): IsUserNameValidUseCase = IsUserNameValidUseCaseImpl()
}