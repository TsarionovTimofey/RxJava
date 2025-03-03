package tsarionovtimofey.tests.domain.repository.rx

import io.reactivex.Single
import tsarionovtimofey.tests.domain.model.AuthResponse
import tsarionovtimofey.tests.domain.model.LoginParams


interface AuthRepositoryRx {
    fun login(params: LoginParams): Single<AuthResponse>
}