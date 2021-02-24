package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherData
import com.example.weatherapp.adapters.WeatherListAdapter
import com.example.weatherapp.viewmodels.WeatherViewModel
import com.example.weatherapp.databinding.FragmentWeatherListBinding
import com.example.weatherapp.helpers.withDivider

class WeatherListFragment : Fragment() {

    private var binding: FragmentWeatherListBinding? = null

    private val weatherListAdapter: WeatherListAdapter
        get() = binding?.weatherListView?.adapter as WeatherListAdapter

    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWeatherListBinding
            .inflate(inflater, container, false)
        this.binding = binding
        binding.initialize()
        return binding.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun FragmentWeatherListBinding.initialize() {
        with(weatherListView) {
            layoutManager = LinearLayoutManager(context)
            withDivider(
                R.drawable.item_divider_light_gray,
                R.dimen.spacing_from_edge
            )
            adapter = WeatherListAdapter(
                context = context,
                onItemClick = ::onItemClick
            )
        }
    }

    private fun onItemClick(args: DetailFragment.Args) {
        findNavController().navigate(R.id.action_WeatherListFragment_to_detailFragment, args.bundle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()

        viewModel.forecast.observe(viewLifecycleOwner, Observer(::updateView))
    }

    private fun updateView(weatherData: WeatherData?) {
        weatherListAdapter.submitList(weatherData?.list)
    }
}
