package game.cash.miobackend.updateFB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import game.cash.miobackend.Constant;
import game.cash.miobackend.ContactMap;
import game.cash.miobackend.MainActivity;
import game.cash.miobackend.R;
import game.cash.miobackend.ResultActivity;
import game.cash.miobackend.UserModel;

public class UpdateActivity extends AppCompatActivity {
    Button update1, update2;
    DatabaseReference mRef;
    public String Orders;
    Bundle arguments;
    ListView listView;
    ArrayList<ContactMap> list = new ArrayList<>();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        arguments = getIntent().getExtras();
        update1 = findViewById(R.id.update1);
        update2 = findViewById(R.id.update2);
        listView = findViewById(R.id.listView);
        FB();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        List();
            }
        }, 1000);
    }

    public void FB() {
        mRef = FirebaseDatabase.getInstance().getReference("Клиенты").child(arguments.get(Constant.NUMBER).toString());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Orders = dataSnapshot.getValue(String.class);
                Log.i("TAG", Orders);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public  void List(){
        list.add(new ContactMap(Constant.NUMBER, arguments.get(Constant.NUMBER).toString()));
        list.add(new ContactMap(Constant.ORDERS, Orders));

        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.list_item,
                new String[] { ContactMap.FIRST, ContactMap.SECOND }, new int[] {
                R.id.text_view_name, R.id.text_view_phone });
        listView.setAdapter(adapter);
    }

    public void Update1(View view) {
        i = Integer.parseInt(Orders);
        i = i + 1;
        list.remove(1);
        list.add(new ContactMap(Constant.ORDERS, String.valueOf(i)));
        listView.invalidateViews();
    }

    public void Update2(View view) {
      //  mRef = FirebaseDatabase.getInstance().getReference("Клиенты").child(arguments.get(Constant.NUMBER).toString()).setValue(Orders);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Task<Void> myRef2 = database.getReference().child("Клиенты").child(arguments.get(Constant.NUMBER).toString()).
        setValue(String.valueOf(i));
    }
}
