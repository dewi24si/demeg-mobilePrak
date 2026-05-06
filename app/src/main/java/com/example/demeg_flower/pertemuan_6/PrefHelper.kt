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

    // ── Quiz B: kunci data registrasi ────────────────────────────
    private const val KEY_REG_NAME     = "reg_nama"
    private const val KEY_REG_PHONE    = "reg_phone"
    private const val KEY_REG_USERNAME = "reg_username"
    private const val KEY_REG_PASSWORD = "reg_password"
    private const val KEY_REG_EXISTS   = "reg_exists"

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

    // ── Quiz B: Simpan data registrasi ───────────────────────────

    /** Simpan data user yang baru registrasi setelah OTP verified */
    fun saveRegisteredUser(
        context: Context,
        nama: String,
        phone: String,
        username: String,
        password: String
    ) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_REG_NAME, nama)
            .putString(KEY_REG_PHONE, phone)
            .putString(KEY_REG_USERNAME, username)
            .putString(KEY_REG_PASSWORD, password)
            .putBoolean(KEY_REG_EXISTS, true)
            .apply()
    }

    /** Cek apakah sudah ada akun yang pernah registrasi */
    fun hasRegisteredUser(context: Context): Boolean =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_REG_EXISTS, false)

    /** Ambil username hasil registrasi */
    fun getRegisteredUsername(context: Context): String =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_REG_USERNAME, "") ?: ""

    /** Ambil password hasil registrasi */
    fun getRegisteredPassword(context: Context): String =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_REG_PASSWORD, "") ?: ""
}
