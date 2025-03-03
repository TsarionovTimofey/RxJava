package tsarionovtimofey.tests.data

import tsarionovtimofey.tests.domain.TokenStorage
import tsarionovtimofey.tests.domain.usecase.IsUserSignedInUseCase

class IsUserSignedInUseCaseImpl(
    private val tokenStorage: TokenStorage
) : IsUserSignedInUseCase {
    override fun execute(): Boolean = tokenStorage.getToken() != null
}