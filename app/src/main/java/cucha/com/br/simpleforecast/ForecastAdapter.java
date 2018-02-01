package cucha.com.br.simpleforecast;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final Calendar calendar;
    private final SimpleDateFormat formatter;
    private final Listener listener;
    private List<WeatherResponse.ListBean> beanList = new ArrayList<>();

    interface Listener {
        void itemSelected(WeatherResponse.ListBean bean);
    }

    public ForecastAdapter(Listener listener) {
        calendar = Calendar.getInstance();
        formatter = new SimpleDateFormat("hh:mm", Locale.getDefault());
        this.listener = listener;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.view_item_main, parent, false);

        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(beanList.get(position));
    }

    public void addBeanList(List<WeatherResponse.ListBean> beanList) {
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final View view;

        ForecastViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewForecast);
            view = itemView;
        }

        void bind(final WeatherResponse.ListBean bean) {
            double temp = bean.getMain().getTemp();
            int dt = bean.getDt();

            Date date = new Date(dt);

            String s = formatter.format(date);

            textView.setText(s + " - " + String.valueOf(temp) + " graus farenheit");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.itemSelected(bean);
                    }
                }
            });
        }
    }
}
