package cucha.com.br.simpleforecast;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {
    static String baseUrl = "http://api.openweathermap.org";
    static OkHttpClient okHttpClient;

    public static void setBaseUrl(String baseUrl) {
        AppModule.baseUrl = baseUrl;
    }

    public static OkHttpClient getOkHttpClient() {
        if(okHttpClient == null) {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder client = new OkHttpClient.Builder();
//            client.addInterceptor(loggingInterceptor);
            okHttpClient = client.build();
        }

        return okHttpClient;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static ForecastService provideForecastService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ForecastService.class);
    }
}
