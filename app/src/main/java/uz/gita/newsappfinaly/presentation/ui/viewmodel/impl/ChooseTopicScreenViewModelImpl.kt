package uz.gita.newsappfinaly.presentation.ui.viewmodel.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.data.model.Query
import uz.gita.newsappfinaly.domain.usecase.ChooseTopicScreenUseCase
import uz.gita.newsappfinaly.presentation.navigation.Navigator
import uz.gita.newsappfinaly.presentation.ui.viewmodel.ChooseTopicScreenViewModel
import uz.gita.newsappfinaly.utils.flow
import javax.inject.Inject

@HiltViewModel
class ChooseTopicScreenViewModelImpl @Inject constructor(
    private val useCase: ChooseTopicScreenUseCase,
    private val navigator: Navigator,
) : ViewModel(), ChooseTopicScreenViewModel {

    override val categoryList = flow<List<Category>>()
    override val isLoading = flow<Boolean>()

    override val message = flow<String>()

    override fun onClickNextButton(query: Query) {
        viewModelScope.launch {
//            navigator.navigateTo(
//                ChooseTopicScreenDirections.actionChooseTopicScreenToChooseSourceScreen(
//                    query
//                )
//            )
        }
    }
    override fun search(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.searchCategory(search).collectLatest {
                it.onSuccess {
                    if (it.isNotEmpty()) {
                        isLoading.emit(false)
                        categoryList.emit(it)
                    } else {
                        categoryList.emit(emptyList())
                    }
                }.onMessage { it ->
                    it.onText {
                        message.emit(it)
                    }
                }.onError {
                    isLoading.emit(false)
                    message.emit(it.message.toString())
                }
            }
        }
    }

    override fun onClickBack() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {

            isLoading.emit(true)

            useCase.getAllCategory().collectLatest { it ->

                it.onSuccess {
                    isLoading.emit(false)
                    categoryList.emit(it)
                }.onMessage { it ->
                    it.onText {
                        message.emit(it)
                    }
                }.onError {
                    isLoading.emit(false)
                    message.emit(it.message.toString())
                }
            }
        }
    }
}
