package com.example.modulo14tarefa

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.modulo14tarefa.databinding.FragmentPlayerBinding
import com.example.modulo14tarefa.databinding.FragmentResultBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.Result

class ResultFragment : Fragment() {
    lateinit var bind: FragmentResultBinding
    lateinit var jokenpoEngine: JokenpoEngine
    lateinit var resultText: TextView
    lateinit var playsText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayerBinding.inflate(inflater,container,false)

        lifecycle.addObserver(CustomObserver())

        bind = FragmentResultBinding.inflate(inflater,container, false)
        jokenpoEngine = JokenpoEngine(resources.getStringArray(R.array.avaiable_plays))

        val currentPlay = arguments?.getString("currentPlay")
        resultText = bind.textView4
        playsText = bind.textViewJogadasResult

        currentPlay?.let{
            updateResultText(currentPlay)
        }

        setHasOptionsMenu(true)

        return bind.root
    }

    private fun updateResultText(currentPlay: String){
        val resultGame = jokenpoEngine.calculateResult(currentPlay)
        val aiPlay = jokenpoEngine.getAIPlay()

        resultText.text = when(resultGame){
            com.example.modulo14tarefa.Result.WIN -> "Voce Ganhou"
            com.example.modulo14tarefa.Result.LOSS -> "Voce Perdeu"
            else -> "Voce Empatou"
        }
        playsText.text = when(resultGame){
            com.example.modulo14tarefa.Result.WIN -> "Voce jogou $currentPlay e a AI jogou $aiPlay"
            com.example.modulo14tarefa.Result.LOSS -> "Voce jogou $currentPlay e a AI jogou $aiPlay"
            else -> "Ambos jogaram $currentPlay"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.second_screen_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val botao1 = R.id.homeFragment
        val botao2 = R.id.menu_save
        return when(item.itemId){
            botao1 -> {
                findNavController().navigate(R.id.action_global_homeFragment)
                true
            }
            botao2 -> {
                Snackbar.make(this.requireView(), "Salvando", Snackbar.LENGTH_LONG).show()
                true
            }
            else -> item.onNavDestinationSelected(findNavController())
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Armazenar aqui as informacoes
    }
}