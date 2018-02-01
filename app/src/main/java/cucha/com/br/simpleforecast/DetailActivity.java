package cucha.com.br.simpleforecast;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String BEAN = "BEAN";

    public static Intent newIntent(Context context, WeatherResponse.ListBean bean) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(BEAN, bean);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        showBean();
    }

    private void showBean() {

        WeatherResponse.ListBean bean = getIntent().getParcelableExtra(BEAN);

        TextView textView = findViewById(R.id.textView1);
        textView.setText(String.valueOf(bean.getMain().getTemp()));

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(String.valueOf(bean.getMain().getTemp_min()));

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(String.valueOf(bean.getMain().getTemp_max()));

        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText(String.valueOf(bean.getMain().getPressure()));
    }
}
