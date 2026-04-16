package com.stela.kotlinwebview.app.src.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grotg.hpp.otglibrary.param.EpcBean
import com.stela.kotlinwebview.R

class ScanAdapter(private val itemList: MutableList<EpcBean>) :
    RecyclerView.Adapter<ScanAdapter.ScanViewHolder>() {

    class ScanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.itViewTxt)
        val patientTag: TextView = itemView.findViewById(R.id.itViewTag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.scan_screen_itemview,
            parent,
            false
        )
        return ScanViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScanViewHolder, position: Int) {
        val item = itemList[position]
        holder.patientName.text = item.toString()
        holder.patientTag.text = item.toString()
    }

    override fun getItemCount(): Int = itemList.size
}