package com.example.bart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bart.model.ApiResponse
import com.example.bart.model.Etd
import com.example.bart.model.LoadingState
import com.example.bart.retrofit.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealTimeActivityViewModel
    @Inject constructor(private val apiInterface: ApiInterface): ViewModel() {

    val allStations= MutableLiveData<List<Etd>>()


    val loadingStateLiveData = MutableLiveData<LoadingState>()

    init {
        fetchRealTimeData()
    }

    private fun fetchRealTimeData(){
        loadingStateLiveData.value = LoadingState.LOADING
        CoroutineScope(Dispatchers.IO).launch {
            try{
                     val response = apiInterface.getRealTimeBartSchedule()
                    if(response.isSuccessful){
                        val data: ApiResponse? = response.body()
                        Log.d("DataTest", data!!.root.station[0].name)
                        allStations.postValue(data.root.station[0].etd)
                        loadingStateLiveData.postValue(LoadingState.LOADED)
                    }else{
                        Log.d("DataTestApi", response.body().toString())
                        loadingStateLiveData.postValue(LoadingState.ERROR)
                    }
                }catch (e:Exception){
                Log.d("DataTestAp", e.toString())
                loadingStateLiveData.postValue(LoadingState.ERROR)
                }

        }
    }
}