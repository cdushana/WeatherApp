package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.helpers.Constants

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null

    data class Args(
        val temperature: Int? = null,
        val feelsLike: Int? = null,
        val appearance: String? = null,
        val appearanceDescription: String? = null
    ) {
        companion object {
            fun fromBundle(bundle: Bundle) =
                Args(
                    temperature = bundle.getInt(Constants.TEMPERATURE),
                    feelsLike = bundle.getInt(Constants.FEELS_LIKE),
                    appearance = bundle.getString(Constants.APPEARANCE),
                    appearanceDescription = bundle.getString(Constants.APPEARANCE_DESCRIPTION)
                )
        }

        val bundle: Bundle
            get() = bundleOf(
                Constants.TEMPERATURE to temperature,
                Constants.FEELS_LIKE to feelsLike,
                Constants.APPEARANCE to appearance,
                Constants.APPEARANCE_DESCRIPTION to appearanceDescription
            )
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let {
            Args.fromBundle(
                it
            )
        }

        binding?.apply {
            temperature.text = args?.temperature.toString()
            feelsLike.text = getString(R.string.feels_like_string, args?.feelsLike.toString())
            appearance.text = args?.appearance
            appearanceDescription.text = args?.appearanceDescription
        }
    }
}

