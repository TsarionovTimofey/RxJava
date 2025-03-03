package tsarionovtimofey.tests.login.domain

import io.reactivex.Single
import tsarionovtimofey.tests.domain.model.LoginParams
import tsarionovtimofey.tests.domain.repository.rx.AuthRepositoryRx
import tsarionovtimofey.tests.domain.TokenStorage
import tsarionovtimofey.tests.domain.mappers.toData
import tsarionovtimofey.tests.domain.usecase.LoginUserUseCaseRx

class LoginUserUseCaseRxImpl(
    private val authRepository: AuthRepositoryRx,
    private val tokenStorage: TokenStorage
) : LoginUserUseCaseRx {
    override fun execute(params: LoginParams): Single<Unit> {
        println("1" + Thread.currentThread())
        return authRepository.login(params)
            .map { response ->
                println("5" + Thread.currentThread())
                tokenStorage.cache(response.toData())
            }
    }
}