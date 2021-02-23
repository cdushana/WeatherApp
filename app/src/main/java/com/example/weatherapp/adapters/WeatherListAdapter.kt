package com.example.weatherapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherItem
import com.example.weatherapp.databinding.WeatherItemBinding
import com.example.weatherapp.ui.DetailFragment
import kotlin.reflect.KFunction1

class WeatherListAdapter(
    private val context: Context,
    val onItemClick: KFunction1<@ParameterName(name = "args") DetailFragment.Args, Unit>
) : ListAdapter<WeatherItem, WeatherListAdapter.WeatherListViewHolder>(
    DIFF_CALLBACK
) {
    private val inflater = LayoutInflater.from(context)

    inner class WeatherListViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherItem) = with(binding) {
            weather.text = item.weather?.first()?.main
            temperature.text =
                context.getString(R.string.temperature_string, item.main?.temp?.toInt().toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        return WeatherListViewHolder(
            WeatherItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            val item = getItem(position)
            val args = DetailFragment.Args(
                temperature = item.main?.temp?.toInt(),
                feelsLike = item.main?.feels_like?.toInt(),
                appearance = item?.weather?.first()?.main,
                appearanceDescription = item?.weather?.first()?.description
            )

            onItemClick(args)
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherItem>() {
    override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
        return oldItem == newItem
    }
}

