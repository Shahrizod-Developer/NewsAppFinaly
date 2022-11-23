package uz.gita.newsappfinaly.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.domain.usecase.SplashScreenUseCase
import uz.gita.newsappfinaly.presentation.navigation.Navigator
import uz.gita.newsappfinaly.presentation.ui.screen.fragment.intro.SplashScreenDirections
import uz.gita.newsappfinaly.presentation.ui.viewmodel.SplashScreenViewModel
import uz.gita.newsappfinaly.utils.flow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor(
    private val useCase: SplashScreenUseCase,
    private val navigator: Navigator,
) : ViewModel(), SplashScreenViewModel {

    override fun openScreen() {
        viewModelScope.launch {
            delay(3000)
            useCase.getIsFirst().collectLatest {

                if (it) {
                    useCase.getIsFirstIntro().collectLatest {
                        if (it) {
                            navigator.navigateTo(SplashScreenDirections.actionSplashScreenToIntroScreen())
                        } else {
                            navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
                        }
                    }
                } else {
                    navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
                }
            }
        }
    }
}