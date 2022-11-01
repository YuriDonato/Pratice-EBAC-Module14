package com.example.modulo14tarefa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.modulo14tarefa.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        val startGameButton = binding.startActivityButton

        startGameButton.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToGameNav()
            findNavController().navigate(action)
        }

        lifecycle.addObserver(CustomObserver())

        return binding.root
    }

}
