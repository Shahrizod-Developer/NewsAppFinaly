package uz.gita.newsappfinaly.presentation.ui.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Query
import uz.gita.newsappfinaly.domain.usecase.CountryScreenUseCase
import uz.gita.newsappfinaly.presentation.navigation.Navigator
import uz.gita.newsappfinaly.presentation.ui.screen.fragment.choose.ChooseCountryScreenDirections
import uz.gita.newsappfinaly.presentation.ui.viewmodel.ChooseCountryScreenViewModel
import uz.gita.newsappfinaly.utils.flow
import javax.inject.Inject

@HiltViewModel
class ChooseCountryScreenViewModelImpl @Inject constructor(
    private val useCase: CountryScreenUseCase,
    private val navigator: Navigator,
) : ViewModel(), ChooseCountryScreenViewModel {

    override val countryList = flow<List<CountryEntity>>()
    override val isFirst = flow<Boolean>()
    override val isLoading = flow<Boolean>()
    override val message = flow<String>()

    override fun onClickNextButton(query: Query) {
        viewModelScope.launch {
            navigator.navigateTo(ChooseCountryScreenDirections.actionChooseCountryScreenToChooseTopicScreen(
                query))
        }
    }

    override fun search(search: String) {
        viewModelScope.launch {
            useCase.searchCountry(search).collectLatest {
                countryList.emit(it)
            }
        }
    }

    override fun onClickBack() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    init {
        viewModelScope.launch {

            isLoading.emit(true)

            useCase.getAllCountry().collectLatest { it ->

                it.onSuccess {
                    isLoading.emit(false)
                    countryList.emit(it)
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
        viewModelScope.launch {
            useCase.getIsFirst().collectLatest {
                isFirst.emit(it)
            }
        }
    }
}