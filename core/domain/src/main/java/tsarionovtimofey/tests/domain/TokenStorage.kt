package tsarionovtimofey.tests.domain

import tsarionovtimofey.tests.domain.model.TokenData

interface TokenStorage {
    fun cache(tokenData: TokenData)
    fun getToken(): String?
    fun clear()
}

