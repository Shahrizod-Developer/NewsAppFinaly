package uz.gita.newsappfinaly.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.model.IntroData

interface IntroScreenViewModel {

    val introDataList: Flow<List<IntroData>>
    val currentItemFlow: Flow<Int>

    fun onClickSkip()

    fun onClickNext(currentItem: Int)
}