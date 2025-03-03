package tsarionovtimofey.tests.domain.usecase

import io.reactivex.Single
import tsarionovtimofey.tests.domain.model.LoginParams

interface LoginUserUseCaseRx {
    fun execute(params: LoginParams): Single<Unit>
}

