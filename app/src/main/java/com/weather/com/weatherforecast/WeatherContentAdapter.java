package com.weather.com.weatherforecast;

/**
 * Created by aartichittala on 3/10/18.
 */

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.weather.com.weatherforecast.models.Forecastday_;

import java.util.List;

public class WeatherContentAdapter extends ArrayAdapter<Forecastday_> {

    List<Forecastday_> mforecastList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public WeatherContentAdapter(Context context, List<Forecastday_> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        mforecastList = objects;
    }

    @Override
    public Forecastday_ getItem(int position) {
        return mforecastList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Forecastday_ item = getItem(position);

        vh.day.setText(item.getDate().getWeekdayShort().toString());
        vh.highTemp.setText(item.getHigh().getFahrenheit().toString() + " F");
        vh.lowTemp.setText(item.getLow().getFahrenheit().toString() + " F");
        vh.condition.setText(item.getConditions().toString());
        Picasso.with(context).load(item.getIconUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView highTemp;
        public final TextView lowTemp;
        public final TextView condition;
        public final TextView day;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView day, TextView highTemp, TextView lowTemp, TextView condition) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.day = day;
            this.lowTemp = lowTemp;
            this.highTemp = highTemp;
            this.condition = condition;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = rootView.findViewById(R.id.listImage);
            TextView day = rootView.findViewById(R.id.listDay);
            TextView highTemp = rootView.findViewById(R.id.listHighTemp);
            TextView lowTemp = rootView.findViewById(R.id.listLowTemp);
            TextView condition = rootView.findViewById(R.id.listCondition);
            return new ViewHolder(rootView, imageView, day, highTemp, lowTemp, condition);
        }
    }
}