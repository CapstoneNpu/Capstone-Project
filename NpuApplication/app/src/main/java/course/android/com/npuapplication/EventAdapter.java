package course.android.com.npuapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sumirna on 12/7/17.
 */

public class EventAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private final EventItems[] data;
    private Activity mActivity;
    private Context context;

    public EventAdapter(Context context, EventItems[] data, Activity mActivity) {
        this.context = context;
        this.data = data;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public EventItems getItem(int position) {
        return this.data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View result = convertView;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        } else {
            result = convertView;
        }

        EventItems item = this.getItem(position);

        TextView name = (TextView) result.findViewById(R.id.event_month);
        name.setText(item.getMonth());
        TextView desc = (TextView) result.findViewById(R.id.event_date);
        desc.setText(item.getDate());
        TextView time = (TextView) result.findViewById(R.id.event_details);
        time.setText(item.getDetails());
        result.setPadding(10, 30, 5, 30);

        return result;
    }
}