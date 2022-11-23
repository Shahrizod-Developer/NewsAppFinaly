package uz.gita.newsappfinaly.presentation.ui.viewmodel.impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.domain.usecase.RegisterScreenUseCase
import uz.gita.newsappfinaly.presentation.navigation.Navigator
import uz.gita.newsappfinaly.presentation.ui.viewmodel.RegisterScreenViewModel
import uz.gita.newsappfinaly.utils.ResultData
import uz.gita.newsappfinaly.utils.flow
import javax.inject.Inject


@HiltViewModel
class RegisterScreenViewModelImp @Inject constructor(
    private val navigator: Navigator,
    private val useCase: RegisterScreenUseCase,
) : RegisterScreenViewModel, ViewModel() {


    override val errorMessage = flow<String>()
    override val message = flow<String>()
    override val successMessage = flow<String>()
    override val loading = flow<Boolean>()


    override fun onCLickSignUp(authData: AuthData, context: Context) {
        viewModelScope.launch {
            loading.emit(true)
            useCase.register(authData).collectLatest {
                when (it) {
                    is ResultData.Success -> {
                        loading.emit(false)
                        successMessage.emit(it.data)
                        navigator.back()
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

    override fun onClickSignIn() {
        viewModelScope.launch {
            navigator.back()
        }
    }
}