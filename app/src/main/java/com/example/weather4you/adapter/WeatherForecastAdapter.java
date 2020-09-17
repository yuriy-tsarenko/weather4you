package com.example.weather4you.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather4you.R;
import com.example.weather4you.api.WeatherApi;
import com.example.weather4you.model.WeatherForecastResponse;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    Context context;
    WeatherForecastResponse weatherForecastResponse;

    public WeatherForecastAdapter(Context context, WeatherForecastResponse weatherForecastResponse) {
        this.context = context;
        this.weatherForecastResponse = weatherForecastResponse;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return weatherForecastResponse.getList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date, txt_description, txt_temperature;
        ImageView img_weather;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_weather = itemView.findViewById(R.id.img_weather);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_temperature = itemView.findViewById(R.id.txt_temperature);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/wn/")
                .append(weatherForecastResponse.getList().get(position).getWeather().get(0).getIcon())
                .append(".png").toString()).into(holder.img_weather);

        holder.txt_date.setText(new StringBuilder(WeatherApi.convertUnixToDate(weatherForecastResponse.getList().get(position).getDt())));

        holder.txt_description.setText(new StringBuilder(weatherForecastResponse.getList().get(position).getWeather().get(0).getDescription()));

        holder.txt_temperature.setText(String.valueOf(new StringBuilder(String.valueOf((int) weatherForecastResponse.getList().get(position).getMain().getTemp())).append("°C")));


    }


}
