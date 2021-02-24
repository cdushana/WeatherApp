package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentLookupBinding
import com.example.weatherapp.viewmodels.WeatherViewModel

class LookupFragment : Fragment() {

    private lateinit var cityName: String
    private lateinit var lookupButton: Button
    private lateinit var cityLookupEditText: EditText

    private var binding: FragmentLookupBinding? = null

    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLookupBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lookupButton = view.findViewById(R.id.lookupButton)
        cityLookupEditText = view.findViewById(R.id.cityLookupEditText)

        lookupButton.setOnClickListener {
            cityName = cityLookupEditText.text.toString()
            (activity as AppCompatActivity).supportActionBar?.title = cityName.capitalize()

            if (cityName.isNotEmpty()) {
                // TODO check response and prevent navigation where there aren't valid results
                viewModel.retrieveWeatherForCity(cityName)
                findNavController().navigate(R.id.action_LookupFragment_to_WeatherListFragment)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
}