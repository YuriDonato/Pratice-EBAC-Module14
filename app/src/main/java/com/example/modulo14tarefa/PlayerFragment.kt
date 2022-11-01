package com.example.modulo14tarefa

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.modulo14tarefa.databinding.FragmentPlayerBinding
import com.google.android.material.snackbar.Snackbar

class PlayerFragment : Fragment() {
    private lateinit var root: View
    private lateinit var selectPlay: Spinner
    //lateinit var listener: JogadorListener
    private lateinit var onItemSelectedListener: OnItemSelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onItemSelectedListener = context as OnItemSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayerBinding.inflate(inflater,container,false)
        root = binding.root
        selectPlay = binding.spinner
        lifecycle.addObserver(CustomObserver())

        setHasOptionsMenu(true)
        setupSelectPlaySpinner()

        //listener.onPlaySelected(selectPlay.toString())
        return root
    }

    interface JogadorListener{
        fun onPlaySelected(selectedPlay: String)
    }

    private fun setupSelectPlaySpinner(){
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.avaiable_plays,
            android.R.layout.simple_spinner_item) //definir conteudo do spinner

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectPlay.adapter = adapter // vincular adapter com spinner
        selectPlay.onItemSelectedListener = onItemSelectedListener//definir quem ta escutando o evento e implementando o callback
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
                Snackbar.make(this.root, "Salvando", Snackbar.LENGTH_LONG).show()
                true
            }
            else -> item.onNavDestinationSelected(findNavController())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}