package com.stela.kotlinwebview.app.src.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stela.kotlinwebview.R
import com.stela.kotlinwebview.app.src.model.PatientData

class ScanAdapter(private val itemList: MutableList<PatientData> = mutableListOf()) :
    RecyclerView.Adapter<ScanAdapter.ScanViewHolder>() {

    class ScanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.patName)
        val patientTag: TextView = itemView.findViewById(R.id.patTag)
        val patientBirth : TextView = itemView.findViewById(R.id.patBirthDate)
        val patientCovenant : TextView = itemView.findViewById(R.id.patCovenant)
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
        val patient = itemList[position]
        holder.patientName.text = "Name: ${patient.name}"
        holder.patientTag.text = "Tag: ${patient.tag}"
        holder.patientBirth.text = "Date of Birth: ${patient.birth}"
        holder.patientCovenant.text = "Covenant: ${patient.covenant}"
    }

    override fun getItemCount(): Int = itemList.size

    fun updateList(newList : List<PatientData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}