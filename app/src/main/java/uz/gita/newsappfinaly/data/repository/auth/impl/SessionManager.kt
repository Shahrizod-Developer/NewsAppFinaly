package uz.gita.newsappfinaly.data.repository.auth.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager @Inject constructor(

) {
    val shp = MySharedPreference.getInstance()

    val loginToken: LiveData<String>
        get() = _loginToken

    private val _loginToken = MutableLiveData<String>()

    // Method #1
    fun setToken(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            shp.token = token
            _loginToken.postValue(token)
        }
    }

    // Method #2
    fun deleteToken() {
        CoroutineScope(Dispatchers.IO).launch {
            shp.token = ""
        }
    }

    // Method #3
    fun getToken(): LiveData<String> {
        CoroutineScope(Dispatchers.IO).launch {
            _loginToken.postValue(shp.token)
        }
        return loginToken
    }
}