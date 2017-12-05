package course.android.com.npuapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
//        NewsItem[] newsItems = new NewsItem[10];
//        for (int i = 0; i < newsItems.length; i++) {
//            newsItems[i] = new NewsItem("Sample title " + i, "", "");
//        }

        final NewsItem[] newsItems = {
                new NewsItem(
                        "Why Cambridge Exams?",
                        "NPU is an authorized Cambridge English Assessment Center",
                        "http://npu.edu/cambridge/why-cambridge-exams",
                        "http://www.npu.edu/sites/default/files/styles/mt_teaser/public/cambridge-logo-690x441_0.jpg?itok=eUNgSPHb"),
                new NewsItem("Forging Ahead",
                        "NPU President's Message to students, faculty, and staff",
                        "http://npu.edu/news/forging-ahead",
                        "http://www.npu.edu/sites/default/files/styles/mt_teaser/public/PresidentMessage%20copy_4.jpg?itok=AgibdeDZ"),
                new NewsItem("Summer 2017 Capstone Competition",
                        "Congratulations to the winners of NPU Summer 2017 Capstone Competition",
                        "http://npu.edu/news/summer-2017-capstone-competition",
                        "http://www.npu.edu/sites/default/files/styles/mt_teaser/public/CapstoneGroupPic.jpg?itok=fsNpXA4W"),
                new NewsItem("Job Fair",
                        "Find your next greatest career opportunity at NPU's job fair.",
                        "http://npu.edu/news/job-fair-2017",
                        "http://www.npu.edu/sites/default/files/styles/mt_teaser/public/career-fair.jpg?itok=qzDvhZbV"),
                new NewsItem("2017 NPU Commencement Speaker",
                        "Learn more about our commencement speaker.",
                        "http://npu.edu/news/2017_commencement_speaker",
                        "http://www.npu.edu/sites/default/files/styles/mt_teaser/public/Speaker%20Teaser%20%281%29.png?itok=c_rpEWeW")

        };
        newsListView = (ListView) findViewById(R.id.news_list_view);
        NewsItemAdapter adapter = new NewsItemAdapter(getApplicationContext(), newsItems, NewsActivity.this);
        newsListView.setAdapter(adapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(NewsActivity.this, WebViewActivity.class);
                i.putExtra("url", newsItems[position].getUrl());
                startActivity(i);
            }
        });
    }
}

class NewsItem {
    private String title;
    private String desc;
    private String url;
    private String imageUrl;

    NewsItem(String title, String desc, String url, String imageUrl) {
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc(){
       return this.desc;
    }

    public String getUrl(){
        return this.url;
    }

    public String getImageUrl() { return this.imageUrl; }

}
