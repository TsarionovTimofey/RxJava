package tsarionovtimofey.tests.login.domain

interface IsUserNameValidUseCase {
    fun execute(userName: String): Boolean
}