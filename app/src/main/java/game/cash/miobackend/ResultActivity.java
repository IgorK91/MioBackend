package game.cash.miobackend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import game.cash.miobackend.updateFB.UpdateActivity;

public class ResultActivity extends AppCompatActivity {
    Button add;
    Bundle arguments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        add = findViewById(R.id.add);

        arguments = getIntent().getExtras();

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

    public void Add(View view) {
        Intent intent = new Intent(ResultActivity.this, UpdateActivity.class);
        intent.putExtra(Constant.NUMBER, arguments.get(Constant.NUMBER).toString());
        startActivity(intent);
    }
}