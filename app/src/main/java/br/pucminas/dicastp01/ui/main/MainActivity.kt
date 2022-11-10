package br.pucminas.dicastp01.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.pucminas.dicastp01.R
import br.pucminas.dicastp01.data.RegistroPeso
import br.pucminas.dicastp01.databinding.ActivityMainBinding
import br.pucminas.dicastp01.ui.cadastro.CadastroActivity

class MainActivity : AppCompatActivity() {

    // objeto utilizado para fornecer acesso à view.
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val retornoActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(TAG)) {
                    val retorno = if (Build.VERSION.SDK_INT >= 33) {
                        it.getParcelableExtra(TAG, RegistroPeso::class.java)
                    } else {
                        it.getParcelableExtra(TAG)
                    }

                    Log.d("PUCMINAS::D", retorno.toString())
                    mainViewModel.salvarNovoRegistro(retorno)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflando o layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Vinculando a Activity à View
        setContentView(binding.root)
        // Função utilizada para configurar os listeners.
        configListeners()
        configObservers()
    }

    private fun configListeners() {
        configurarFabListener()
    }

    private fun configObservers() {
        configurarListaPesoObserver()
    }

    private fun configurarListaPesoObserver() {
        mainViewModel.listaPeso.observe(this) { listaPesos ->
            prepararRecyclerView(listaPesos)
        }
    }

    private fun prepararRecyclerView(listaPesos: List<RegistroPeso>?) {
        binding.rvItens.layoutManager = LinearLayoutManager(applicationContext)
        if (listaPesos.isNullOrEmpty()) {
            binding.tvQtd.setText(R.string.nao_existem_itens)
        } else {
            binding.apply {
                rvItens.adapter = PesoAdapter(listaPesos)
                tvQtd.text = resources.getQuantityString(
                    R.plurals.quantidade_peso,
                    listaPesos.size,
                    listaPesos.size
                )
            }
        }
    }

    private fun configurarFabListener() {
        binding.fabNovaActivity.setOnClickListener {
            iniciarNovaActvity()
        }
    }

    private fun iniciarNovaActvity() {
        Intent(this, CadastroActivity::class.java).let {
            retornoActivity.launch(it)
        }
    }

    companion object {
        const val TAG = "PUC_EXEMPLO_INTENT"
    }
}