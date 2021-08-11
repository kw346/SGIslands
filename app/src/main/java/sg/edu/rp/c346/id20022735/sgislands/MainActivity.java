package sg.edu.rp.c346.id20022735.sgislands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMz, etDes, etDis;
    Button btnInsert, btnShowList;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main));

        etMz = findViewById(R.id.etname);
        etDes = findViewById(R.id.etdescri);
        etDis = findViewById(R.id.etdist);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        rb = findViewById(R.id.ratingBar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etMz.getText().toString().trim();
                String desc = etDes.getText().toString().trim();
                if (name.length() == 0 || desc.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String distance = etDis.getText().toString().trim();
                int dist = Integer.valueOf(distance);
                int rating = (int) rb.getRating();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long result = dbh.insertIsland(name, desc, dist, rating);

                if (result != -1) {
                    Toast.makeText(MainActivity.this, "Song inserted", Toast.LENGTH_LONG).show();
                    etMz.setText("");
                    etDes.setText("");
                    etDis.setText("");
                    rb.setRating(0);
                } else {
                    Toast.makeText(MainActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

}