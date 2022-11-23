package uz.gita.newsappfinaly.data.repository.auth

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.utils.ResultData

interface AuthRepository {

    fun register(authData: AuthData): Flow<ResultData<String>>

    fun login(authData: AuthData): Flow<ResultData<String>>

    fun resetPasswordWithEmail(email: String): Flow<ResultData<String>>

    fun logout(authData: AuthData): Flow<ResultData<String>>

    fun getSms(): Flow<ResultData<String>>

}