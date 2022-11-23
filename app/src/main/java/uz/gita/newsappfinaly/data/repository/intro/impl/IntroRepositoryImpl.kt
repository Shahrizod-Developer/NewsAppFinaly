package uz.gita.newsappfinaly.data.repository.intro.impl

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import uz.gita.newsappfinaly.data.local.dao.CategoryDao
import uz.gita.newsappfinaly.data.local.dao.CountryDao
import uz.gita.newsappfinaly.data.local.entity.entity.CountryEntity
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference
import uz.gita.newsappfinaly.data.model.Category
import uz.gita.newsappfinaly.data.model.Country
import uz.gita.newsappfinaly.data.model.Data.countryCodeList
import uz.gita.newsappfinaly.data.model.Data.introData
import uz.gita.newsappfinaly.data.model.IntroData
import uz.gita.newsappfinaly.data.model.Mapper.toCategory
import uz.gita.newsappfinaly.data.remote.api.CountryApi
import uz.gita.newsappfinaly.data.repository.intro.IntroRepository
import uz.gita.newsappfinaly.utils.MessageData
import uz.gita.newsappfinaly.utils.ResultData
import uz.gita.newsappfinaly.utils.hasConnection
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class IntroRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao,
    private val categoryDao: CategoryDao,
    private val countryApi: CountryApi,
) : IntroRepository {

    private val shp = MySharedPreference.getInstance()
    private val db = Firebase.firestore

    override fun getAllCountry(): Flow<ResultData<List<CountryEntity>>> = flow {

        if (hasConnection()) {
            try {
                val response = countryApi.getAllCountry()

                when (response.code()) {
                    in 200..299 -> {
                        countryDao.deleteAll()
                        val countryList = ArrayList<Country>()
                        val data = response.body()
                        for (i in data?.indices!!) {
                            for (j in countryCodeList.indices) {
                                if (data[i].code == countryCodeList[j].toUpperCase(Locale.ROOT)) {
                                    countryList.add(data[i])
                                }
                            }
                        }
                        countryDao.insert(countryList.map { it.toEntity() })
                    }
                    in 400..499 -> {
                        emit(ResultData.message(MessageData.messageText(response.errorBody()
                            ?.string().toString())))
                    }
                    in 500..599 -> {
                        emit(ResultData.message(MessageData.messageText(response.errorBody()
                            ?.string().toString())))
                    }
                }
            } catch (e: Exception) {
                emit(ResultData.message(MessageData.messageText(e.message.toString())))
            }
        } else {
            emit(ResultData.message(MessageData.messageText("No internet connection")))
        }

        countryDao.getAllCountry().map {
            emit(ResultData.success(it))
        }.collect()

    }.flowOn(Dispatchers.IO)

    override fun getAllCategory(): Flow<ResultData<List<Category>>> = callbackFlow {
        var data = ArrayList<Category>()
        if (hasConnection()) {
            val listCategory = db.collection("category").addSnapshotListener { value, error ->
                if (value != null) {
                    data = value.documents.map {
                        it.toCategory()
                    }.toList() as ArrayList<Category>
                    trySend(ResultData.success(data))
                }
            }
            awaitClose { listCategory.remove() }
        } else {
            trySend(ResultData.message(MessageData.messageText("No internet connection")))
        }
        trySend(ResultData.success(data))
    }.flowOn(Dispatchers.IO)

    override fun getIsFirst(): Flow<Boolean> = flow { emit(shp.isFirst) }.flowOn(Dispatchers.IO)

    override suspend fun setIsFirst(state: Boolean) {
        shp.isFirst = state
    }

    override fun getIsFirstIntro(): Flow<Boolean> = flow {
        Log.d("CCC", shp.isFirstIntro.toString())
        emit(shp.isFirstIntro)
    }.flowOn(Dispatchers.IO)

    override suspend fun setIsFirstIntro(state: Boolean) {
        Log.d("CCC", state.toString())
        shp.isFirstIntro = state
    }

    override fun searchCountry(query: String): Flow<List<CountryEntity>> =
        countryDao.searchCountry(query)

    override fun searchCategory(query: String): Flow<ResultData<List<Category>>> = callbackFlow {
        var data = ArrayList<Category>()
        if (hasConnection()) {
            val listCategory = db.collection("category").addSnapshotListener { value, error ->
                if (value != null) {
                    data = value.documents.map {
                        it.toCategory()
                    }.filter { it ->
                        it.name.toUpperCase(Locale.ROOT).contains(query.toUpperCase(Locale.ROOT))
                    } as ArrayList<Category>
                    trySend(ResultData.success(data))
                }
            }
            awaitClose { listCategory.remove() }
        } else {
            trySend(ResultData.message(MessageData.messageText("No internet connection")))
        }
        trySend(ResultData.success(data))
    }.flowOn(Dispatchers.IO)

    override fun getIntroData(): Flow<List<IntroData>> = flow { emit(introData) }
}

