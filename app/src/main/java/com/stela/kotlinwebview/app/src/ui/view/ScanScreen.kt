package com.stela.kotlinwebview.app.src.ui.view

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.stela.kotlinwebview.R
import com.stela.kotlinwebview.app.src.adapter.ScanAdapter
import com.stela.kotlinwebview.app.src.domain.ReaderManager

class ScanScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScanAdapter
    private lateinit var readerManager : ReaderManager
    private lateinit var scanBtn : Button
    private lateinit var clearBtn: Button
    private val itemList = mutableListOf<ScanAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scan_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        readerManager = ReaderManager(this)

        initView()
        initRecyclerView()
    }

    private fun initView() {
        scanBtn = findViewById<Button>(R.id.scanBtn)
        clearBtn = findViewById<Button>(R.id.clearBtn)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    }
    private fun initRecyclerView() {

        adapter = ScanAdapter(itemList)
        recyclerView.adapter = adapter

    }



}