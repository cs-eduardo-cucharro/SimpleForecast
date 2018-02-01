package cucha.com.br.simpleforecast;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {
    static String baseUrl = "http://api.openweathermap.org";

    public static void setBaseUrl(String baseUrl) {
        AppModule.baseUrl = baseUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static ForecastService provideForecastService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ForecastService.class);
    }
}
