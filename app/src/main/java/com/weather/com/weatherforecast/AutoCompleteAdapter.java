package com.weather.com.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.weather.com.weatherforecast.models.Result;
import java.util.List;

/**
 * Created by aartichittala on 3/10/18.
 */

public class AutoCompleteAdapter extends ArrayAdapter<Result> {

        List<Result> resultList;
        Context context;
        private LayoutInflater mInflater;

        // Constructors
        public AutoCompleteAdapter(Context context, List<Result> objects) {
            super(context, 0, objects);
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            resultList = objects;
        }

        @Override
        public Result getItem(int position) {
            return resultList.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView == null) {
                View view = mInflater.inflate(R.layout.auto_complete_row, parent, false);
                vh = ViewHolder.create((LinearLayout) view);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            Result item = getItem(position);

            vh.cityZmw.setText(item.getZmw());
            vh.cityZmw.setVisibility(View.INVISIBLE);
            vh.textViewAutoComplete.setText(item.getName());
            return vh.rootView;
        }

        private static class ViewHolder {
            public final LinearLayout rootView;
            public final TextView textViewAutoComplete;
            public final TextView cityZmw;

            private ViewHolder(LinearLayout rootView, TextView textViewAutoComplete, TextView cityZmw) {
                this.rootView = rootView;
                this.textViewAutoComplete = textViewAutoComplete;
                this.cityZmw = cityZmw;
            }

            public static ViewHolder create(LinearLayout rootView) {
                TextView textViewAutoComplete = rootView.findViewById(R.id.auto_complete_content);
                TextView cityZmw = rootView.findViewById(R.id.city_zmw);
                return new ViewHolder(rootView, textViewAutoComplete, cityZmw);
            }
        }
    }
