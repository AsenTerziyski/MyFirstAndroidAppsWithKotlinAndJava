package bg.asentt.myapplication

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context?) {

    private val pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor? = pref?.edit()

    fun setLogin(isLogin: Boolean) {
        editor?.putBoolean(IS_LOGIN, isLogin)
        editor?.commit()
    }

    fun setUsername(username: String?) {
        editor?.putString("username", username)
        editor?.commit()
    }

    fun isLogin(): Boolean? {
        return pref?.getBoolean(IS_LOGIN, false)
    }

    fun getUsername(): String? {
        return pref?.getString("username", "")
    }

    fun removeData() {
        editor?.clear()
        editor?.commit()
    }

    companion object {
        private const val PREF_NAME = "SharedPreferences"
        private const val IS_LOGIN = "is_login"
    }

}