package sg.edu.rp.c346.id20022735.sgislands;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    EditText etID, etMZ, etDESCRI, etDIST;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etMZ = (EditText) findViewById(R.id.etNAME);
        etDESCRI = (EditText) findViewById(R.id.etDES);
        etDIST = (EditText) findViewById(R.id.etDIS);

        rb3 = findViewById(R.id.ratingBar3);

        Intent i = getIntent();
        final Island currentIsland = (Island) i.getSerializableExtra("island");

        etID.setText(currentIsland.getId()+"");
        etMZ.setText(currentIsland.getName());
        etDESCRI.setText(currentIsland.getDescription());
        etDIST.setText(currentIsland.getDistance()+"");
        rb3.setRating(currentIsland.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentIsland.setName(etMZ.getText().toString().trim());
                currentIsland.setDescription(etDESCRI.getText().toString().trim());

                int dist = 0;

                try {
                    dist = Integer.valueOf(etDIST.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid distance", Toast.LENGTH_SHORT).show();
                    return;
                }

                currentIsland.setDistance(dist);

                currentIsland.setStars((int) rb3.getRating());
                int result = dbh.updateIsland(currentIsland);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deleteIsland(currentIsland.getId());

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);


                if (result>0){
                    String resname = currentIsland.getName();
                    myBuilder.setTitle("Danger");
                    myBuilder.setMessage("Are you sure you want to delete the island? \n"+resname);
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Cancel", null);
                    myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ThirdActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Do Not Discard", null);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }

}
