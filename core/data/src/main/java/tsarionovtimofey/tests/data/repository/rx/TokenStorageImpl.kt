package tsarionovtimofey.tests.data.repository.rx

import android.annotation.SuppressLint
import android.content.SharedPreferences
import tsarionovtimofey.tests.domain.model.TokenData
import tsarionovtimofey.tests.domain.TokenStorage

@SuppressLint("ApplySharedPref")
class TokenStorageImpl(
    private val prefs: SharedPreferences
) : TokenStorage {
    companion object {
        private const val KEY_TOKEN = "key_token"
    }

    override fun cache(tokenData: TokenData) {
        prefs.edit().putString(KEY_TOKEN, tokenData.token).commit()
    }

    override fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    override fun clear() {
        prefs.edit().clear().commit()
    }
}