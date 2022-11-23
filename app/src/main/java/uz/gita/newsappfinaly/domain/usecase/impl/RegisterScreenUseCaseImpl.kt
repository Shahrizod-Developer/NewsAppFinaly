package uz.gita.newsappfinaly.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.data.repository.auth.AuthRepository
import uz.gita.newsappfinaly.domain.usecase.RegisterScreenUseCase
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class RegisterScreenUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
) : RegisterScreenUseCase {

    override fun register(authData: AuthData): Flow<ResultData<String>> =
        repository.register(authData)
}