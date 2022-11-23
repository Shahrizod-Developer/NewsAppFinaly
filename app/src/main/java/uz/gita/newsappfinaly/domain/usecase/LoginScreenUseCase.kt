package uz.gita.newsappfinaly.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.utils.ResultData

interface LoginScreenUseCase {

    fun login(authData: AuthData): Flow<ResultData<String>>

}