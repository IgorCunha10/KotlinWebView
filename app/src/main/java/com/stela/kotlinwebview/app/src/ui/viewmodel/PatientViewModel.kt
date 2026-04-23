package com.stela.kotlinwebview.app.src.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stela.kotlinwebview.app.src.data.PatientRepository
import com.stela.kotlinwebview.app.src.model.PatientData
import kotlinx.coroutines.launch

class PatientViewModel : ViewModel(){

    private val repository = PatientRepository()

    private val _patients = MutableLiveData<List<PatientData>>(emptyList())
    val patients : LiveData<List<PatientData>> = _patients

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _erro = MutableLiveData<String?>()
    val erro : LiveData<String?> = _erro

    fun verifyTag(tag: String) {
        viewModelScope.launch {
            _loading.value = true

            try {

                val patient = repository.findPatient(tag)

                if(patient != null) {
                    val actualList = _patients.value.orEmpty().toMutableList()
                    actualList.add(0, patient)
                    _patients.value = actualList
                } else {
                    _erro.value = "Tag is not associated to any patient."
                }

            } catch (e : Exception) {
                _erro.value = e.message ?: "Erro ao verificar tag"
            } finally {
                _loading.value = false
            }
        }
    }

    fun clearList() {
        _patients.value = emptyList()
    }

    }

