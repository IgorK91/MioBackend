package game.cash.miobackend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> List = new ArrayList<String>();
    ArrayList<UserModel> List2 = new ArrayList<UserModel>();
    ArrayAdapter<String> adapter;
    DatabaseReference mRef;
    String data;
    UserModel user;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        data();
    }

    public void init() {
        ListView listView = findViewById(R.id.listView);
        List = new ArrayList<String>();
        List2 = new ArrayList<UserModel>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, List);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                user = List2.get(position);
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra(Constant.ADDRESS, user.address);
                intent.putExtra(Constant.NAME, user.name);
                intent.putExtra(Constant.NUMBER, user.number);
                intent.putExtra(Constant.TITLE, user.title);
                intent.putExtra(Constant.QUANTITY, user.quantity);
                intent.putExtra(Constant.COAST, user.coast);
                startActivity(intent);
            }
        });
        mRef = FirebaseDatabase.getInstance().getReference("Заказы");
    }

    public void data() {
        ValueEventListener vList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for (DataSnapshot ds : dataSnapshot.getChildren())
               {
                   data = ds.getKey();
                   List.add(data);
                   user = ds.getValue(UserModel.class);
                   List2.add(user);
               }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
        mRef.addValueEventListener(vList);
    }
}