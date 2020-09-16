package com.example.weather4you;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather4you.api.WeatherApi;
import com.example.weather4you.model.WeatherResponse;
import com.example.weather4you.retrofit.OpenWeatherMap;
import com.example.weather4you.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayWeatherFragment extends Fragment {

    ImageView img_weather;
    TextView txt_city_name, txt_humidity, txt_sunrise, txt_sunset, txt_pressure, txt_temperature,
            txt_description, txt_date_time, txt_wind, txt_geo_coord;
    LinearLayout weather_panel;
    ProgressBar loading;

    CompositeDisposable compositeDisposable;
    OpenWeatherMap mService;

    static TodayWeatherFragment instance;

    public static TodayWeatherFragment getInstance() {
        if (instance == null) {
            instance = new TodayWeatherFragment();
        }
        return instance;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TodayWeatherFragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(OpenWeatherMap.class);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayWeatherFragment newInstance(String param1, String param2) {
        TodayWeatherFragment fragment = new TodayWeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_today_weather, container, false);

        img_weather = itemView.findViewById(R.id.img_weather);
        txt_city_name = itemView.findViewById(R.id.txt_city_name);
        txt_humidity = itemView.findViewById(R.id.txt_humidity);
        txt_sunrise = itemView.findViewById(R.id.txt_sunrise);
        txt_sunset = itemView.findViewById(R.id.txt_sunset);
        txt_pressure = itemView.findViewById(R.id.txt_pressure);
        txt_temperature = itemView.findViewById(R.id.txt_temperature);
        txt_description = itemView.findViewById(R.id.txt_description);
        txt_date_time = itemView.findViewById(R.id.txt_date_time);
        txt_wind = itemView.findViewById(R.id.txt_wind);
        txt_geo_coord = itemView.findViewById(R.id.txt_geo_coord);

        weather_panel = itemView.findViewById(R.id.weather_panel);
        loading = itemView.findViewById(R.id.loading);

        getInformation();

        return itemView;
    }

    private void getInformation() {
        compositeDisposable.add(mService.getWeatherByLtLong(String.valueOf(WeatherApi.current_Location.getLatitude()),
                String.valueOf(WeatherApi.current_Location.getLongitude()),
                WeatherApi.API_KEY, "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResponse>() {
                               @Override
                               public void accept(WeatherResponse weatherResponse) throws Exception {

                                   Picasso.get().load(new StringBuilder("https://openweathermap.org/img/wn/")
                                           .append(weatherResponse.getWeather().get(0).getIcon())
                                           .append(".png").toString()).into(img_weather);

                                   txt_city_name.setText(weatherResponse.getName());
                                   txt_description.setText(new StringBuilder("Weather in ").append(weatherResponse.getName()).toString());
                                   txt_temperature.setText(new StringBuilder(String.valueOf((int)weatherResponse.getMain().getTemp())).append("°C").toString());
                                   txt_date_time.setText(WeatherApi.convertUnixToDate(weatherResponse.getDt()));
                                   txt_pressure.setText(new StringBuilder(String.valueOf(weatherResponse.getMain().getPressure())).append(" hpa").toString());
                                   txt_humidity.setText(new StringBuilder(String.valueOf(weatherResponse.getMain().getHumidity())).append(" %").toString());
                                   txt_sunrise.setText(WeatherApi.convertUnixToHour(weatherResponse.getSys().getSunrise()));
                                   txt_sunset.setText(WeatherApi.convertUnixToHour(weatherResponse.getSys().getSunset()));
                                   txt_geo_coord.setText(new StringBuilder(" ").append(weatherResponse.getCoord().toString()).append(" ").toString());
                                   txt_wind.setText(new StringBuilder(String.valueOf(weatherResponse.getWind().getSpeed())).append("m/s"));

                                   weather_panel.setVisibility(View.VISIBLE);
                                   loading.setVisibility(View.GONE);


                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(getActivity(), "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           }
                ));
    }
}