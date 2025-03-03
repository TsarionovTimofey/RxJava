package tsarionovtimofey.tests.login.domain

class IsUserNameValidUseCaseImpl : IsUserNameValidUseCase {
    override fun execute(userName: String) = userName.trim().isNotEmpty()
}