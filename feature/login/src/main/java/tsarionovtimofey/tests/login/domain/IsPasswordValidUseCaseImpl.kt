package tsarionovtimofey.tests.login.domain

class IsPasswordValidUseCaseImpl : IsPasswordValidUseCase {
    override fun execute(password: String) = password.trim().isNotEmpty()
}