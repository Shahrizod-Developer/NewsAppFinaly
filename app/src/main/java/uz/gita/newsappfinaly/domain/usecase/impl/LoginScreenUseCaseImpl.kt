package uz.gita.newsappfinaly.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.data.repository.auth.AuthRepository
import uz.gita.newsappfinaly.domain.usecase.LoginScreenUseCase
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class LoginScreenUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
) : LoginScreenUseCase {

    override fun login(authData: AuthData): Flow<ResultData<String>> = repository.login(authData)

}