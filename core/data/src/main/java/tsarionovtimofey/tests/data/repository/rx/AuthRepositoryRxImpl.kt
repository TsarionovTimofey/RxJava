package tsarionovtimofey.tests.data.repository.rx

import io.reactivex.Single
import tsarionovtimofey.tests.domain.model.AuthResponse
import tsarionovtimofey.tests.domain.model.LoginParams
import tsarionovtimofey.tests.data.model.toDomain
import tsarionovtimofey.tests.data.model.toDto
import tsarionovtimofey.tests.data.AuthApiRx
import tsarionovtimofey.tests.domain.repository.rx.AuthRepositoryRx

class AuthRepositoryRxImpl(
    private val api: AuthApiRx
) : AuthRepositoryRx {
    override fun login(params: LoginParams): Single<AuthResponse> {
        println("2" + Thread.currentThread())
        return api.login(params.toDto()).map { it.toDomain() }
    }
}