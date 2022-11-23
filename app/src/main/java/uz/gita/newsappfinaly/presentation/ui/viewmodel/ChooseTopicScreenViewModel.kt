package uz.gita.newsappfinaly.presentation.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.data.model.Query

interface ChooseTopicScreenViewModel {

    val categoryList: Flow<List<Category>>
    val isLoading: Flow<Boolean>
    val message: Flow<String>

    fun onClickNextButton(query: Query)

    fun search(search: String)

    fun onClickBack()
}