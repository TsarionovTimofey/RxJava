package tsarionovtimofey.tests.login.domain

interface IsPasswordValidUseCase {

    fun execute(password: String): Boolean
}