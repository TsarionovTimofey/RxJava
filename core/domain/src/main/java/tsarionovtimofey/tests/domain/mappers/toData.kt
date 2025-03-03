package tsarionovtimofey.tests.domain.mappers

import tsarionovtimofey.tests.domain.model.AuthResponse
import tsarionovtimofey.tests.domain.model.TokenData

fun AuthResponse.toData() = TokenData(
    token = token
)