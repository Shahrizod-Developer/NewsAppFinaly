package uz.gita.newsappfinaly.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference
import uz.gita.newsappfinaly.data.model.IntroData
import uz.gita.newsappfinaly.domain.usecase.IntroScreenUseCase
import uz.gita.newsappfinaly.presentation.navigation.Navigator
import uz.gita.newsappfinaly.presentation.ui.screen.fragment.intro.IntroScreenDirections
import uz.gita.newsappfinaly.presentation.ui.viewmodel.IntroScreenViewModel
import uz.gita.newsappfinaly.utils.flow
import javax.inject.Inject

@HiltViewModel
class IntroScreenViewModelImpl @Inject constructor(
    private val useCase: IntroScreenUseCase,
    private val navigator: Navigator,
) : IntroScreenViewModel, ViewModel() {

    override val introDataList = flow<List<IntroData>>()
    override val currentItemFlow = flow<Int>()

    init {
        viewModelScope.launch {
            useCase.getIntroData().collectLatest {
                introDataList.emit(it)
            }
        }
    }

    override fun onClickSkip() {
        viewModelScope.launch {
            Log.d("KKK", MySharedPreference.getInstance().isFirstIntro.toString())
            useCase.setIsFirstIntro(false)
            navigator.navigateTo(IntroScreenDirections.actionIntroScreenToLoginScreen())
        }
    }

    override fun onClickNext(currentItem: Int) {

        viewModelScope.launch {
            when (currentItem) {
                0 -> {
                    currentItemFlow.emit(1)
                }
                1 -> {
                    currentItemFlow.emit(2)
                }
                2 -> {
                    useCase.setIsFirstIntro(false)
                    navigator.navigateTo(IntroScreenDirections.actionIntroScreenToLoginScreen())
                }
            }
        }
    }
}