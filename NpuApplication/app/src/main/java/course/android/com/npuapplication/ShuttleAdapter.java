package course.android.com.npuapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sumirna on 12/6/17.
 */

public class ShuttleAdapter extends BaseAdapter {

    private final shuttleDetails[] data;
    private Activity mActivity;
    private Context context;

    public ShuttleAdapter(Context context, shuttleDetails[] data, Activity mActivity) {
        this.context = context;
        this.data = data;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public shuttleDetails getItem(int position) {
        return this.data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.shuttle_item, parent, false);
        } else {
            result = convertView;
        }

        shuttleDetails item = this.getItem(position);
        TextView name = (TextView) result.findViewById(R.id.shuttle_detail);
        name.setText(item.getTitle());

        result.setPadding(10, 30, 5, 30);

        return result;
    }
}

