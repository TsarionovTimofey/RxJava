package tsarionovtimofey.tests.data.model

import tsarionovtimofey.tests.domain.model.AuthResponse

fun AuthResponseDto.toDomain() = AuthResponse(
    token = token
)