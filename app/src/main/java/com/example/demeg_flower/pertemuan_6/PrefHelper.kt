package com.example.demeg_flower.pertemuan_6

import android.content.Context

/**
 * Pertemuan 6 – PrefHelper
 * Helper object untuk mengelola SharedPreferences autentikasi Bina Desa.
 */
object PrefHelper {

    private const val PREF_NAME    = "bina_desa_prefs"
    private const val KEY_IS_LOGIN = "isLogin"
    private const val KEY_USERNAME = "saved_username"

    /** Simpan status login dan username ke SharedPreferences */
    fun setLogin(context: Context, username: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_IS_LOGIN, true)
            .putString(KEY_USERNAME, username)
            .apply()
    }

    /** Hapus status login (logout) */
    fun clearLogin(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_IS_LOGIN, false)
            .remove(KEY_USERNAME)
            .apply()
    }

    /** Cek apakah user sudah login */
    fun isLogin(context: Context): Boolean =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_IS_LOGIN, false)

    /** Ambil username yang tersimpan */
    fun getUsername(context: Context): String =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_USERNAME, "Pengguna") ?: "Pengguna"
}
