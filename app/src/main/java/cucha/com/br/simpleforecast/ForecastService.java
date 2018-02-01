package cucha.com.br.simpleforecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastService {
    @GET("/data/2.5/forecast")
    Call<WeatherResponse> getForecast(@Query("q") String city, @Query("appid") String appid);
}
