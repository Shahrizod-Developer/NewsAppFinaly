package uz.gita.newsappfinaly.presentation.ui.viewmodel

import android.content.Context
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.AuthData

interface RegisterScreenViewModel {

    val errorMessage: Flow<String>
    val successMessage: Flow<String>
    val message: Flow<String>
    val loading: Flow<Boolean>

    fun onCLickSignUp(authData: AuthData,  context: Context)

    fun onClickSignIn()
}