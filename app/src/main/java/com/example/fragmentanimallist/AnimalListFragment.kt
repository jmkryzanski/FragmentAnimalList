package com.example.fragmentanimallist

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider


class AnimalListFragment : Fragment() {

    private val TAG = "AnimalListFragment"

    // Create a list of some strings that will be shown in the listview
    private val animalList = listOf("dog", "cat", "bear", "rabbit")

    // Create an instance of our ViewModel
    // In our example, this instance does not have to be a global variable, but
    // I defined this way, in case in the future, you want to use this instance across multiple methods
    lateinit var viewModel: PositionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_list, container, false)


        // Create an adapter with 3 parameters: activity (this), layout, list
        val myAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, animalList)

        // set the adapter to listview -- MUST USE view in the beginning to access findViewById method
        // if you directly access the listview without using view in the beginning, expect a crash!!!
        val animalList = view.findViewById<ListView>(R.id.animal_list)
        animalList.adapter = myAdapter


        animalList.setOnItemClickListener { list, item, position, id ->

            // Determine which item in the list is selected
            val selectedItem = list.getItemAtPosition(position).toString()
            Log.d(TAG, "$selectedItem")
            //Toast.makeText(activity, "selectedItem: $selectedItem", Toast.LENGTH_SHORT).show();


            // ViewModelProvider returns an existing ViewModel if one exists,
            // or it creates a new one if it does not already exist.
            // Get an instance of our ViewModel using context
            // and then set the position
            viewModel = ViewModelProvider(requireActivity()).get(PositionViewModel::class.java)
            //viewModel.position = position

            // With mutablelivedata version
            viewModel.setPosition(position)
            //viewModel.position.value = position

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                // We are in portrait orientation
                // Load Detail fragment, i.e., replace listview fragment with detail fragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, AnimalDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
            else {
                // We are in landscape orientation
                // Load Detail fragment, i.e., replace the current detail fragment
                // with detail fragment containing the selected item's details
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.details_container, AnimalDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }

        }

        return view
    }

}