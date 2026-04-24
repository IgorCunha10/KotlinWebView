package com.stela.kotlinwebview.app.src.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stela.kotlinwebview.R
import com.stela.kotlinwebview.app.src.model.PatientData

class ScanAdapter(private val itemList: MutableList<PatientData> = mutableListOf(),
                  private val onOpenWebView: (String) -> Unit
) :
    RecyclerView.Adapter<ScanAdapter.ScanViewHolder>() {

    class ScanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.patName)
        val patientTag: TextView = itemView.findViewById(R.id.patTag)
        val patientBirth : TextView = itemView.findViewById(R.id.patBirthDate)
        val patientCovenant : TextView = itemView.findViewById(R.id.patCovenant)
        val btnRelatorio: FloatingActionButton = itemView.findViewById(R.id.assesmentBtn)


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
        holder.btnRelatorio.setOnClickListener {
            val url = "http://192.168.1.180/browser/#/patient/${patient.tag}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun updateList(newList : List<PatientData>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }
}