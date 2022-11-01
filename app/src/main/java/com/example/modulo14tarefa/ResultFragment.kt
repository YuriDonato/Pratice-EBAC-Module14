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

class ResultFragment : Fragment() {
    //lateinit var root: View
    lateinit var engine: JokenpoEngine
    lateinit var bind: FragmentResultBinding
    lateinit var resultText: TextView
    lateinit var resultText2: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayerBinding.inflate(inflater,container,false)
        //root = binding.root

        lifecycle.addObserver(CustomObserver())

        bind = FragmentResultBinding.inflate(inflater,container, false)
        engine = JokenpoEngine(resources.getStringArray(R.array.avaiable_plays))

        val currentPlay = arguments?.getString("currentPlay")
        resultText = bind.textView4 //texto a ser mudado
        resultText2 = bind.resultadoGeralTextos

        currentPlay?.let{
            updateResultText(currentPlay)
        }

        setHasOptionsMenu(true)

        return bind.root
    }

    private fun updateResultText(currentPlay: String){
        val resultGame = engine.calculateResult(currentPlay)
        val aiPlay = engine.getAIPlay()

        resultText.text = when(resultGame){
            Result.WIN -> "Voce Ganhou"
            Result.LOSS -> "Voce Perdeu"
            else -> "Voce Empatou"
        }
        resultText2.text = when(resultGame) {
            Result.WIN -> "Voce jogou $currentPlay e a AI jogou $aiPlay"
            Result.LOSS -> "Voce jogou $currentPlay e a AI jogou $aiPlay"
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