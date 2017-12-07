package course.android.com.npuapplication;

/**
 * Created by Sindhu on 12/3/17.
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

public class NewsItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private final NewsItem[] data;
    private Activity mActivity;
    private Context context;

    public NewsItemAdapter(Context context, NewsItem[] data, Activity mActivity) {
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
    public NewsItem getItem(int position) {
        return this.data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.news_item, null);

        if (inflater == null)
            inflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.news_item, null);


        NewsItem item = this.getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.news_title);
        name.setText(item.getTitle());
        TextView description = (TextView) convertView.findViewById(R.id.news_desc);
        description.setText(item.getDesc());
        String imgUrl = item.getImageUrl();
        ImageView image = (ImageView)convertView.findViewById(R.id.news_image);
        Picasso.with(this.context).load(imgUrl).into(image);

        return convertView;
    }
}
