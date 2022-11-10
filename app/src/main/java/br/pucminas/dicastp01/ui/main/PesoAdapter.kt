package br.pucminas.dicastp01.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pucminas.dicastp01.data.RegistroPeso
import br.pucminas.dicastp01.databinding.ListItemRegistroPesoBinding

class PesoAdapter(val items: List<RegistroPeso>) :
    RecyclerView.Adapter<PesoAdapter.PesoViewHoder>() {
    private lateinit var binding: ListItemRegistroPesoBinding

    inner class PesoViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(peso: RegistroPeso) {
            binding.apply {
                tvPeso.text = peso.peso.toString()
                tvFaixaEtaria.text = peso.faixaEtaria
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