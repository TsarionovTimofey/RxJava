package tsarionovtimofey.tests.data

import io.reactivex.Single
import tsarionovtimofey.tests.data.model.AuthResponseDto
import tsarionovtimofey.tests.data.model.LoginParamsDto

interface AuthApiRx {
    fun login(paramsDto: LoginParamsDto): Single<AuthResponseDto>
}