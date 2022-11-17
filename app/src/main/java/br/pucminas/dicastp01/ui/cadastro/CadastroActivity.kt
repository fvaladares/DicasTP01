package br.pucminas.dicastp01.ui.cadastro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.pucminas.dicastp01.R
import br.pucminas.dicastp01.data.RegistroPeso
import br.pucminas.dicastp01.databinding.ActivityCadastroBinding
import br.pucminas.dicastp01.ui.main.MainActivity

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configListeners()
    }

    private fun configListeners() {
        configurarRGListener()
        configurartBEnviarListener()
    }

    private fun configurartBEnviarListener() {
        binding.btnEnviar.setOnClickListener {
            salvarPeso()
        }
    }

    private fun salvarPeso() {
        val peso = binding.etExemplo.text.toString()
        val faixaEtaria = validarIdade()

        if (peso.isBlank()) {
            binding.tilExemplo.error = getString(R.string.error_peso)

        } else {
            binding.tilExemplo.error = null
            Intent().apply {
                putExtra(
                    MainActivity.TAG, RegistroPeso(
                        peso = peso.toDouble(), faixaEtaria = faixaEtaria
                    )
                )
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }

    private fun configurarRGListener() {
        // Exemplo de uso do radio button
        // A função setOnCheckedChangeListener identifica quando o estado do componente
        // mudar, o item selecionado é encontrado pelo Id dele.
        binding.rgClassificacaoIdade.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAdulto -> {
                    Toast.makeText(this, getString(R.string.adulto_lbl), Toast.LENGTH_LONG).show()
                    Log.d("PUCMINAS", getString(R.string.adulto_lbl))
                    mudarCorBotao(1)
                }
                R.id.rbIdoso -> {
                    Toast.makeText(this, "Idoso", Toast.LENGTH_LONG).show()
                    Log.d("PUCMINAS", "Idoso")
                    mudarCorBotao(2)
                }
            }
        }
    }

    fun validarIdade(): String {
        val radio = binding.rgClassificacaoIdade
        val selectedId = radio.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(selectedId).text
        return radioButton.toString()
    }


    private fun mudarCorBotao(i: Int) {
// Altera a cor do botão, de acordo com a opção selecionada.
        val color = when (i) {
            1 -> {
                Log.d("PUCMINAS", "Cor Preto")
                getColor(R.color.black)
            }
            else -> {
                Log.d("PUCMINAS", "Cor Preto")
                getColor(R.color.teal_700)
            }
        }
        binding.btnEnviar.setBackgroundColor(color)
    }


}