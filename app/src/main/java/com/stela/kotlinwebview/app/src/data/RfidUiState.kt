package com.stela.kotlinwebview.app.src.data

import com.stela.kotlinwebview.app.src.model.TagEpc

data class RfidUiState(val isConnected: Boolean = false, val isScanning: Boolean = false,
                       val tags: List<TagEpc> = emptyList(),
                       val errorMessage : String? = null,
                       val lastReadOnce : TagEpc? = null)

