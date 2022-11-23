package uz.gita.newsappfinaly.presentation.ui.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Country
import uz.gita.newsappfinaly.data.model.Query

interface ChooseCountryScreenViewModel {

    val countryList: Flow<List<CountryEntity>>
    val isFirst: Flow<Boolean>
    val isLoading: Flow<Boolean>
    val message: Flow<String>

    fun onClickNextButton(query: Query)

    fun search(search: String)

    fun onClickBack()
}