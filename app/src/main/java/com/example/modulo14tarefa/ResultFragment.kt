package com.example.modulo14tarefa

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.modulo14tarefa.databinding.FragmentPlayerBinding
import com.google.android.material.snackbar.Snackbar

class ResultFragment : Fragment() {
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayerBinding.inflate(inflater,container,false)
        root = binding.root

        lifecycle.addObserver(CustomObserver())

        setHasOptionsMenu(true)
        return root
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