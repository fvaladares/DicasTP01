package br.pucminas.dicastp01.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.pucminas.dicastp01.data.RegistroPeso

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _listaPeso = MutableLiveData<List<RegistroPeso>>(mutableListOf())
    val listaPeso: LiveData<List<RegistroPeso>> = _listaPeso

    fun salvarNovoRegistro(registroPeso: RegistroPeso?) {
        registroPeso?.let {
            val lista = _listaPeso.value?.toMutableList() ?: mutableListOf()
            lista.add(registroPeso)
            _listaPeso.value = lista
        }
    }
}