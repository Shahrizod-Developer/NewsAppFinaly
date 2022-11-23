package uz.gita.newsappfinaly.data.local.shp

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MySharedPreference @Inject constructor(@ApplicationContext context: Context) {

    var email: String
        get() = sharedPreferences.getString("email", "").toString()
        set(email) {
            editor.putString("email", email).apply()
        }

    var password: String
        get() = sharedPreferences.getString("password", "").toString()
        set(password) {
            editor.putString("password", password).apply()
        }

    var uid: String
        get() = sharedPreferences.getString("uid", "").toString()
        set(uid) {
            editor.putString("uid", uid).apply()
        }

    var token: String
        get() = sharedPreferences.getString("token", "").toString()
        set(token) {
            editor.putString("token", token).apply()
        }
    var userName: String
        get() = sharedPreferences.getString("user_name", "").toString()
        set(userName) {
            editor.putString("user_name", userName).apply()
        }

    var fullName: String
        get() = sharedPreferences.getString("full_name", "").toString()
        set(fullName) {
            editor.putString("full_name", fullName).apply()
        }

    var image: String
        get() = sharedPreferences.getString("image", "").toString()
        set(image) {
            editor.putString("image", image).apply()
        }


    var phoneNumber: String
        get() = sharedPreferences.getString("phone_number", "").toString()
        set(phoneNumber) {
            editor.putString("phone_number", phoneNumber).apply()
        }

    var isFirst: Boolean
        get() = sharedPreferences.getBoolean("is_first", true)
        set(isFirst) {
            editor.putBoolean("is_first", isFirst).apply()
        }

    var isFirstIntro: Boolean
        get() = sharedPreferences.getBoolean("is_first_intro", true)
        set(isFirst) {
            editor.putBoolean("is_first_intro", isFirst).apply()
        }


    companion object {
        var mySharedPreference: MySharedPreference? = null
        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

        fun init(context: Context): MySharedPreference? {
            if (mySharedPreference == null) {
                mySharedPreference = MySharedPreference(context)
            }
            return mySharedPreference
        }

        fun getInstance() = mySharedPreference!!
    }

    init {
        sharedPreferences = context.getSharedPreferences("app_name", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }
}
