package com.example.anonymus.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> model= new ArrayList<>();
    private CustomListView adapter;
    ListView listView;
    Button button;
    EditText edName,edAddress,edNotes;
    RadioGroup rSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWidget();
        setTabhost();


        listView = findViewById(R.id.content);
        adapter = new CustomListView(this,R.layout.row_xml,model);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String content = model.get(i).getNotes();
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Person person = new Person();

                String name = edName.getText().toString();
                String address = edAddress.getText().toString();
                String note = edNotes.getText().toString();
                if (!name.equals("") && !address.equals("") && !note.equals("")){

                    person.setName(name);
                    person.setAddrress(address);
                    person.setNotes(note);

                    switch (rSex.getCheckedRadioButtonId()){
                        case R.id.bMale:
                            person.setSex("Male");
                            break;
                        case R.id.bFemale:
                            person.setSex("Fmale");
                            break;
                        default:
                            person.setSex("default");
                            break;
                    }

                    model.add(person);
                    adapter.notifyDataSetChanged();

                    edName.setText("");
                    edAddress.setText("");
                    edNotes.setText("");

                }else {
                    Toast.makeText(MainActivity.this, "Không được để trống ô này!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    public void setWidget(){

        button = findViewById(R.id.button);
        edName = findViewById(R.id.edName);
        edAddress = findViewById(R.id.edAddress);
        edNotes = findViewById(R.id.edNotes);
        rSex = findViewById(R.id.rSex);

    }

    public void setTabhost(){
        TabHost tabHost =  findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab 1");
        spec1.setContent(R.id.content);
        spec1.setIndicator("CONTENT");

        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab 2");
        spec2.setContent(R.id.Form);
        spec2.setIndicator("FORM");

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);

    }

}
