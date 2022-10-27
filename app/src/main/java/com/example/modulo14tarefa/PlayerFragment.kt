package com.example.modulo14tarefa

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.modulo14tarefa.databinding.FragmentPlayerBinding
import com.google.android.material.snackbar.Snackbar

class PlayerFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var root: View
    lateinit var selectPlay: Spinner
    lateinit var listener: JogadorListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener =
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

        return root
    }

    interface JogadorListener {
        fun onPlaySelected(selectedPlay: String)
    }

    private fun setupSelectPlaySpinner(){
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.avaiable_plays,
            android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectPlay.adapter = adapter
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
        //Armazenar aqui as informacoes
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val plays = resources.getStringArray(R.array.avaiable_plays)
        val selectedPlay = plays[position]

        Toast.makeText(requireContext(),"Jogada selecionada: $selectedPlay",Toast.LENGTH_SHORT).show() // um teste pra ver se ta armazenando certo
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}