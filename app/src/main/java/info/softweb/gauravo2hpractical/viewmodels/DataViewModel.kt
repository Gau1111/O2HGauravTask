package info.softweb.gauravo2hpractical.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.softweb.gauravo2hpractical.models.LocalUserResult
import info.softweb.gauravo2hpractical.models.UserListResponseModel
import info.softweb.gauravo2hpractical.network.NetworkConnectionInterceptor
import info.softweb.gauravo2hpractical.network.RetriveApi
import info.softweb.gauravo2hpractical.repositories.RetriveRepository
import info.softweb.gauravo2hpractical.util.ApiException
import info.softweb.gauravo2hpractical.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DataViewModel(
) : ViewModel() {
     var data = MutableLiveData<UserListResponseModel>()
     var localdata: LiveData<List<LocalUserResult>>? = MutableLiveData()
     var isNetworkConnected=MutableLiveData<Boolean>(true)
     lateinit var repository: RetriveRepository
    var loading = MutableLiveData<Boolean>(false)
    var tempList:ArrayList<LocalUserResult> = ArrayList()
    lateinit var networkConnectionInterceptor: NetworkConnectionInterceptor

    fun intializeRepository(context: Context)
    {
        networkConnectionInterceptor=NetworkConnectionInterceptor(context)
        repository = RetriveRepository(RetriveApi(networkConnectionInterceptor),context)
        localdata = repository.getData()
    }


    fun getServerData(results : Int) {
        loading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                data.value = repository.fetchServerData(results)
                data.value?.let {
                    for(i in  data.value!!.results!!.indices) {
                        tempList.add(LocalUserResult(data.value!!.results!![i].cell,data.value!!.results!![i].email,
                        data.value!!.results!![i].gender,data.value!!.results!![i].phone,data.value!!.results!![i].picture!!.medium))
                    }
                    repository.saveQuotes(tempList)
                }

            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                isNetworkConnected.value = false
            }
            loading.value = false
        }
    }
}