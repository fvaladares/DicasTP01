package br.pucminas.dicastp01.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pucminas.dicastp01.R
import br.pucminas.dicastp01.data.RegistroPeso
import br.pucminas.dicastp01.databinding.ListItemRegistroPesoBinding

class PesoAdapter(val items: List<RegistroPeso>, val context: Context) :
    RecyclerView.Adapter<PesoAdapter.PesoViewHoder>() {
    private lateinit var binding: ListItemRegistroPesoBinding

    inner class PesoViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(peso: RegistroPeso) {
            binding.apply {
                tvPeso.text =
                    context.getString(
                        R.string.rotulo_peso,
                        peso.peso
                    )//peso.peso.toString()
                tvFaixaEtaria.text =
                    context.getString(R.string.rotulo_faixa_etaria, peso.faixaEtaria)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesoViewHoder {
        binding = ListItemRegistroPesoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PesoViewHoder(binding.root)
    }

    override fun onBindViewHolder(holder: PesoViewHoder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}