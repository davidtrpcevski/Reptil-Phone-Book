package com.david.reptil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ArrayList<ExampleItem> mExampleList;
    private static final int REQUEST_CALL=1;
    private int callingPosition = 0;
    private ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 1 - Рузвелтова", "Ул. Рузвелтова бр.68", "Николина", "02 3136-341","070-316-591"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 2 - Црниче", "Ул. Востаничка бб", "Сузана", "02 2743-113", "070-316-592"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 3 - Кисела Вода", "Ул. Тоне Томшиќ бр.25", "Кети", "02 2779-511", "070-316-593"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 4 - Аеродром", "Ул. Пандил Шишков бр.7 лок.1", "Лидија", "02 2460-127", "071-261-698"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 5 - Ново Лисиче", "Ул. Видое Смилевски Бато бр1/1-1", "Сузана","02 3136-341", "070-316-591"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 6 - Симпо", "Ул. Партизански Одреди бр.59", "Даниела","02 3066-858", "070-231-124" ));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 7 - Порта Влае", "Ул. Партизански Одреди бр.151", "Ане","02 3084-410","070-390-788" ));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 8 - Радишани", "Ул. Радишанска бр.4", "Катерина","02 2626-880", "071-389-887"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 9 - Млечен", "Ул. Анкарска бр.21", "Александра", "02 3080-788", "070-207-894" ));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 10 - Бутел", "Ул. Александар Урдаревски 1/1-1","Благица", "02 2622-661", "070-246-655"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 11 - Државна", "Бул. 50-та Девизија бр.12", "Мери", "Нема", "070-245-645" ));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 12 - Драчево", "Бул. Борис Трајковски бр.15-7", "Марина", "Нема","072-229-006" ));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 13 - Маџари", "Ул. Спиро Црне бр.1", "Габи", "Нема", "070-382-882"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 14 - Лисиче", "Ул. 12-та Македонска Бригада Т.Ц. Лисиче","Јуле", "Нема", "071-373-512"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Рептил 15 - Зелен пазар", "Ул. 11 Октомври бр.16А","Маја","Нема", "072-807-245"));
        mExampleList.add(new ExampleItem(R.drawable.ic_android, "Давид Трпчевски", "Ул. Февруарски Поход", "Давид", "Нема", "070-888-826"));
    }

    public void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("store_name", mExampleList.get(position).getText1());
                intent.putExtra("street_name", mExampleList.get(position).getText2());
                intent.putExtra("number1", mExampleList.get(position).getMnumber1());
                intent.putExtra("number2", mExampleList.get(position).getMnumber2());
                intent.putExtra("lice_name", mExampleList.get(position).getMliceName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }

            @Override
            public void onPhoneClick(int position) {
                makePhoneCall(position);
                callingPosition = position;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private void makePhoneCall(int position) {
        String number = mExampleList.get(position).getMnumber2().replace("-","");
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(MainActivity.this, "Внеси го бројот!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(callingPosition);
            } else {
                Toast.makeText(this, "Притисни ALLOW за да можеш да повикуваш броеви!", Toast.LENGTH_LONG).show();
            }
        }
    }
}