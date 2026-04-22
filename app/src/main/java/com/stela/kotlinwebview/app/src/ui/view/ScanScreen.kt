package com.stela.kotlinwebview.app.src.ui.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grotg.hpp.otglibrary.param.EpcBean
import com.stela.kotlinwebview.R
import com.stela.kotlinwebview.app.src.adapter.ScanAdapter
import com.stela.kotlinwebview.app.src.domain.ReaderManager
import com.stela.kotlinwebview.app.src.ui.viewmodel.PatientViewModel

class ScanScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var readerAdapter: ScanAdapter
    private lateinit var readerManager: ReaderManager
    private lateinit var scanBtn: FloatingActionButton
    private lateinit var clearBtn: Button
    private lateinit var connectBtn: Button
    private lateinit var viewModel : PatientViewModel
    private var isConnected: Boolean = false



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
        viewModel = ViewModelProvider(this)[PatientViewModel::class.java]

        initView()
        initRecyclerView()
        initListeners()
        observeViewModel()
    }

    private fun initView() {
        connectBtn = findViewById(R.id.connectBtn)
        clearBtn = findViewById(R.id.clearBtn)
        scanBtn = findViewById(R.id.fabScanBtn)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initRecyclerView() {
        readerAdapter = ScanAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = readerAdapter
    }

    private fun initListeners() {

        connectBtn.setOnClickListener {
            readerManager.connect { success, message ->
                if (success) {
                    Toast.makeText(this, "Leitora Conectada",
                        Toast.LENGTH_SHORT).show()
                    isConnected = true

                } else {
                    Toast.makeText(this, "Falha em conectar leitora",
                        Toast.LENGTH_SHORT).show()
                    isConnected = false

                }
            }
        }

        readerManager.setTagOnRead(object : ReaderManager.TagCallBack {
            override fun onTagRead(epcBean: EpcBean) {
                runOnUiThread {
                   viewModel.verifyTag(epcBean.strepc)
                    }
                }

        })

        scanBtn.setOnClickListener {
            if (!isConnected) {
                Toast.makeText(this, "Conecte a leitora primeiro",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            readerManager.startScan()
        }

        clearBtn.setOnClickListener {
                viewModel.clearList()
                readerAdapter.notifyDataSetChanged()
        }
    }

    private fun observeViewModel() {
        viewModel.patients.observe(this) {
            list -> readerAdapter.updateList(list)
            recyclerView.scrollToPosition(0)
        }

        viewModel.loading.observe(this) {
            isLoading -> scanBtn.isEnabled = !isLoading
        }

        viewModel.erro.observe(this) {
            message -> message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
    }



