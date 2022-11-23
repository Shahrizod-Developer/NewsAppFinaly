package uz.gita.newsappfinaly.presentation.ui.viewmodel.impl

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.domain.usecase.LoginScreenUseCase
import uz.gita.newsappfinaly.presentation.navigation.Navigator
import uz.gita.newsappfinaly.presentation.ui.screen.fragment.auth.LoginScreenDirections
import uz.gita.newsappfinaly.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.newsappfinaly.utils.ResultData
import uz.gita.newsappfinaly.utils.flow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModelImpl @Inject constructor(
    private val useCase: LoginScreenUseCase,
    private val navigator: Navigator,
) : LoginScreenViewModel, ViewModel() {

    override val errorMessage = flow<String>()
    override val message = flow<String>()
    override val successMessage = flow<String>()
    override val loading = flow<Boolean>()

    override fun onCLickSignUp() {

        viewModelScope.launch {
            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToRegisterScreen())
        }
    }

    override fun onClickSignIn(authData: AuthData, context: Context) {

        viewModelScope.launch {
                loading.emit(true)
                useCase.login(authData).collectLatest {
                    when (it) {
                        is ResultData.Success -> {
                            loading.emit(false)
                            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToChooseCountryScreen())
                            successMessage.emit(it.data)
                        }
                        is ResultData.Error -> {
                            loading.emit(false)
                            message.emit(it.toString())
                        }
                        is ResultData.Message -> {
                            loading.emit(false)
                            message.emit(it.message.getMessageString(context))
                        }
                    }
                }
        }
    }
}