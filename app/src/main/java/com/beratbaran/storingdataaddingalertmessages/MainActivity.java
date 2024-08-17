package com.beratbaran.storingdataaddingalertmessages;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textview;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textview = findViewById(R.id.textview);

        Toast.makeText(MainActivity.this, "Enter your age", Toast.LENGTH_LONG).show();

        sharedPreferences = this.getSharedPreferences("com.beratbaran.storingdataaddingalertmessages", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge", 0);

        if (storedAge == 0) {
            textview.setText("Your age:");
        } else {
            textview.setText("Your Age: " + storedAge);
        }
    }


    public void save(View view) {
    String ageInput = editText.getText().toString();

    if (ageInput.matches("")) {
        Toast.makeText(MainActivity.this, "Please enter your age", Toast.LENGTH_LONG).show();
        return;
    }

    int userAge = Integer.parseInt(ageInput);
    textview.setText("Your Age: " + userAge);

    AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle("Save");
    alert.setMessage("Are you sure?");
    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            sharedPreferences.edit().putInt("storedAge", userAge).apply();
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
        }
    });
    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // Not Saved
            Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
        }
    });

    alert.show();
}

public void delete(View view) {
    int storedData = sharedPreferences.getInt("storedAge", 0);

    if (storedData != 0) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferences.edit().remove("storedAge").apply();
                textview.setText("Your Age: ");
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Not Deleted
                Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();
    }
}

}

