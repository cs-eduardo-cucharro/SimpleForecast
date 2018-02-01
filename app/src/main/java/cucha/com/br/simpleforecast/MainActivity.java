package cucha.com.br.simpleforecast;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements ForecastAdapter.Listener {

    static String city = "sao paulo";
    static String appid = "781b8263713d131646620af488f7659e";
    private ForecastAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupProgress();

        setupRecycler();

        setupFab();

        ForecastService service = AppModule.provideForecastService();

        Call<WeatherResponse> call = service.getForecast(city, appid);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    adapter.addBeanList(response.body().getList());
                    progress.setVisibility(View.GONE);
                } else {
                    showFetchingFail();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                progress.setVisibility(View.GONE);

                showFetchingFail();
            }
        });
    }

    private void setupFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.feature_premium), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFetchingFail() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.falha_ao_carregar))
                .setMessage(getString(R.string.servico_indisponivel))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
    }

    private void setupProgress() {
        progress = findViewById(R.id.progress);
    }

    private void setupRecycler() {
        adapter = new ForecastAdapter(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void itemSelected(WeatherResponse.ListBean bean) {
        Intent intent = DetailActivity.newIntent(this, bean);
        startActivity(intent);
    }
}
