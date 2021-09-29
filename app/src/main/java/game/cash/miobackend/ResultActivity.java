package game.cash.miobackend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle arguments = getIntent().getExtras();

           ListView listView = findViewById(R.id.listView);
           ArrayList<ContactMap> list = new ArrayList<>();
           list.add(new ContactMap(Constant.ADDRESS, arguments.get(Constant.ADDRESS).toString()));
           list.add(new ContactMap(Constant.NAME, arguments.get(Constant.NAME).toString()));
           list.add(new ContactMap(Constant.NUMBER, arguments.get(Constant.NUMBER).toString()));
           list.add(new ContactMap(Constant.TITLE, arguments.get(Constant.TITLE).toString()));
           list.add(new ContactMap(Constant.QUANTITY, arguments.get(Constant.QUANTITY).toString()));
           list.add(new ContactMap(Constant.COAST, arguments.get(Constant.COAST).toString()));

            ListAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item,
                    new String[] { ContactMap.FIRST, ContactMap.SECOND }, new int[] {
                    R.id.text_view_name, R.id.text_view_phone });
            listView.setAdapter(adapter);
    }
}