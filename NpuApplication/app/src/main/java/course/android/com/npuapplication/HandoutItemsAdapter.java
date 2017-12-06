package course.android.com.npuapplication;

/**
 * Created by Sindhu on 12/5/17.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
public class HandoutItemsAdapter extends  BaseAdapter{

        private LayoutInflater inflater;
        private final HandoutItem[] data;
        private Activity mActivity;
        private Context context;

        public HandoutItemsAdapter(Context context, HandoutItem[] data, Activity mActivity) {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.data = data;
            this.mActivity = mActivity;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public HandoutItem getItem(int position) {
            return this.data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            v = inflater.inflate(R.layout.handout_item, null);

            if (inflater == null)
                inflater = (LayoutInflater) mActivity
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                convertView = inflater.inflate(R.layout.handout_item, null);


            HandoutItem item = this.getItem(position);
            TextView title = (TextView) convertView.findViewById(R.id.handout_title);
            title.setText(item.getTitle());

            TextView fileName = (TextView)convertView.findViewById(R.id.handout_file_name);
            fileName.setText(item.getFileName());

            return convertView;
        }
    }


