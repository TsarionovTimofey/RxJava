package tsarionovtimofey.tests.data.repository.rx

import io.reactivex.Single
import tsarionovtimofey.tests.data.model.AuthResponseDto
import tsarionovtimofey.tests.data.model.LoginParamsDto
import tsarionovtimofey.tests.data.AuthApiRx

class AuthApiRxImpl : AuthApiRx {
    companion object {
        private const val SERVER_ERROR_MESSAGE = "Something wrong"
    }

    override fun login(paramsDto: LoginParamsDto): Single<AuthResponseDto> {
        println("3" + Thread.currentThread())
        return Single.fromCallable {
            println("4" + Thread.currentThread())
            Thread.sleep(1500)
            if (paramsDto.userName != "123") throw NetworkException("username")
            if (paramsDto.password != "123") throw NetworkException("password")

            AuthResponseDto(
                token = "real_token"
            )
        }
    }
}


open class AppException : Exception()

class NetworkException(
    val serverMessage: String
) : Exception()