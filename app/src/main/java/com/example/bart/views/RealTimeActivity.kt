package com.example.bart.views

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bart.R
import com.example.bart.databinding.ActivityRealTimeBinding
import com.example.bart.model.LoadingState
import com.example.bart.viewmodel.RealTimeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RealTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRealTimeBinding
    private val viewModel: RealTimeActivityViewModel by viewModels()
    private val adapter= ScheduleAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        initializeUI()
        initializeObservers()
    }


    private fun initializeUI(){
        binding.scheduleRV.adapter = adapter
        binding.scheduleRV.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeObservers(){
        viewModel.loadingStateLiveData.observe(this,{
            onLoadingStateChanged(it)
        })

        viewModel.allStations.observe(this,{
            adapter.updateOrders(it)
        })
    }



    fun onLoadingStateChanged(state: LoadingState){
        binding.scheduleRV.visibility = if (state==LoadingState.LOADED) View.VISIBLE else View.GONE
        binding.errorTV.visibility = if (state==LoadingState.ERROR) View.VISIBLE else View.GONE
        binding.loadingPB.visibility = if (state==LoadingState.LOADING) View.VISIBLE else View.GONE
    }

}


