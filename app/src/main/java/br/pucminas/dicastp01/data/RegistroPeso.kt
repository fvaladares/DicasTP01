package br.pucminas.dicastp01.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistroPeso(
    val peso: Double,
    val faixaEtaria: String
) : Parcelable
