package com.stela.kotlinwebview.app.src.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grotg.hpp.otglibrary.param.EpcBean
import com.stela.kotlinwebview.R
import com.stela.kotlinwebview.app.src.adapter.ScanAdapter
import com.stela.kotlinwebview.app.src.domain.ReaderManager

class ScanScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScanAdapter
    private lateinit var readerManager : ReaderManager
    private lateinit var scanBtn : FloatingActionButton
    private lateinit var clearBtn: Button
    private lateinit var connectBtn : Button
    private var isConnected : Boolean = false
    private val itemList = mutableListOf<EpcBean>()
    private lateinit var readerAdapter: ScanAdapter

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
        initListeners()
//        initRecyclerView()
        initReader()
    }

    private fun initView() {
        connectBtn = findViewById<Button>(R.id.connectBtn)
        clearBtn = findViewById<Button>(R.id.clearBtn)
        scanBtn = findViewById<FloatingActionButton>(R.id.fabScanBtn)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    }

//    private fun initRecyclerView() {
//        recyclerView.adapter = readerAdapter
//    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {

        connectBtn.setOnClickListener {
            readerManager.connect { success, message ->
                if (success) {
                    Toast.makeText(this,
                        "Leitora Conectada",
                        Toast.LENGTH_SHORT).show()
                    isConnected = true

                } else {
                    Toast.makeText(this,
                        "Falha em conectar leitora",
                        Toast.LENGTH_SHORT).show()
                    isConnected = false

                }
            }
        }

        scanBtn.setOnTouchListener { v, event ->  {
            when(event.getAction()) {
            MotionEvent.ACTION_DOWN ->
            }
            }
        }

        clearBtn.setOnClickListener { v -> {

        }
        }
    }

    private fun initReader() {
        readerAdapter = ScanAdapter(mutableListOf())
        recyclerView.adapter = readerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        readerManager.release()
    }

}
