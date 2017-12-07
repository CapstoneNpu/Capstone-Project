package course.android.com.npuapplication;

/**
 * Created by Sindhu on 12/5/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HandoutActivity extends AppCompatActivity {

    private ListView handoutsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handout);

        final HandoutItem[] handoutItems = {
                new HandoutItem("Week 1",
                        "Communication_oral.pptx",
                        "https://drive.google.com/file/d/14b0z4xDGjXPY_ELT8C2pKOeHiHjSoeXl/view?usp=sharing"),
                new HandoutItem(
                        "Week 2",
                        "NPU Presentation 9-21-17.pdf",
                        "https://drive.google.com/file/d/1viSWhIxjElfA6sdQnIbd6zVsCYbftAOF/view?usp=sharing"),
                new HandoutItem("Week 3",
                        "How to Answer 10 Tricky Interview Questions.pptx",
                        "https://drive.google.com/file/d/1SElsRqZWazXuIH1WChiiKDiFLWXBSJaB/view?usp=sharing"),
                new HandoutItem("Week 4",
                        "50 Worst of the Worst.pptx",
                        "https://drive.google.com/file/d/1n6OFn_Ayiu3628cxPYN9cIfUH4gqUVCq/view?usp=sharing"),
                new HandoutItem("Week 5",
                        "Berkeley ResumeLetterWriting.pdf",
                        "https://drive.google.com/file/d/1d7TG_pFIpH_cSfwQg_ewqsqOkyQbzcSX/view?usp=sharing")
        };

        handoutsListView = (ListView) findViewById(R.id.handouts_list_view);

        HandoutItemsAdapter adapter = new HandoutItemsAdapter(getApplicationContext(), handoutItems, HandoutActivity.this);
        handoutsListView.setAdapter(adapter);
        handoutsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(HandoutActivity.this, WebViewActivity.class);
                i.putExtra("url", handoutItems[position].getUrl());
                startActivity(i);
            }
        });
    }
}


class HandoutItem {
    private String title;
    private String fileName;
    private String url;

    HandoutItem(String title, String fileName, String url) {
        this.title = title;
        this.fileName = fileName;
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl(){
        return this.url;
    }

    public String getFileName() {
        return this.fileName;
    }

}