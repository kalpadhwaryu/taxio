package com.example.taxio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FindCAs extends AppCompatActivity {
    private Spinner spinner;
    private Button search;
    private ListView lv_name,lv_phone,lv_email;
//    private TextView tv3;

    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_cas);

        spinner=findViewById(R.id.spinner);
        search=findViewById(R.id.button);
        lv_name=findViewById(R.id.lv_name);
        lv_phone=findViewById(R.id.lv_phone);
        lv_email=findViewById(R.id.lv_email);
//        tv3=findViewById(R.id.tv3);

        String[] items = new String[]{"Vadodara", "Ahmedabad","Surat", "Delhi", "Mumbai"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        final String[] city = new String[1];
        List<CAsHelperClass> list =new ArrayList<>();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                city[0] = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city[0] = "Vadodara";
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = FirebaseDatabase.getInstance().getReference("CAs");
                reference.child(city[0].toLowerCase(Locale.ROOT)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot ds:snapshot.getChildren()){
//                            String name =ds.child("name").getValue(String.class);
//                            String phone = ds.child("phone").getValue(String.class);
//                            String email =ds.child("email").getValue(String.class);
//                            list.add(name +"\n"+phone +"\n"+email+"\n");
//                            tv3.setText(list.toString());
                              CAsHelperClass ca = ds.getValue(CAsHelperClass.class);
                              list.add(ca);
                        }

                        String[] CAname = new String[list.size()];
                        for (int i=0;i<CAname.length;i++){
                            CAname[i]=list.get(i).getName();
                        }

                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,CAname){
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                                textView.setTextColor(Color.BLACK);
                                textView.setTextSize(13);
                                return view;
                            }
                        };
                        lv_name.setAdapter(arrayAdapter);




                        String[] CAphone = new String[list.size()];
                        for (int i=0;i<CAphone.length;i++){
                            CAphone[i]=list.get(i).getPhone();
                        }

                        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,CAphone){
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                                textView.setTextColor(Color.BLACK);
                                textView.setTextSize(13);
                                return view;
                            }
                        };
                        lv_phone.setAdapter(arrayAdapter2);



                        String[] CAemail = new String[list.size()];
                        for (int i=0;i<CAemail.length;i++){
                            CAemail[i]=list.get(i).getEmail();
                        }

                        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,CAemail){
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                                textView.setTextColor(Color.BLACK);
                                textView.setTextSize(13);
                                return view;
                            }
                        };
                        lv_email.setAdapter(arrayAdapter3);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });

    }


}