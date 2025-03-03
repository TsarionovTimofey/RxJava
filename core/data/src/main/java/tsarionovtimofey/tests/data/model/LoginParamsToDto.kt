package tsarionovtimofey.tests.data.model

import tsarionovtimofey.tests.domain.model.LoginParams

fun LoginParams.toDto() =
    LoginParamsDto(
        userName = userName,
        password = password
    )