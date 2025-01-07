package com.example.bart.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bart.databinding.ItemScheduleBinding
import com.example.bart.model.Etd
import com.example.bart.model.Station


class ScheduleAdapter(private val station:ArrayList<Etd>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>(){

    fun updateOrders(newOrder: List<Etd>){
        station.clear()
        station.addAll(newOrder)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ScheduleViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(station[position])
    }

    override fun getItemCount() = station.size


    inner class ScheduleViewHolder(val binding: ItemScheduleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(station: Etd){
            binding.scheduleStation.text = station.destination.toString()
            binding.scheduleEtd.text = station.estimate.toString()

        }
    }


}