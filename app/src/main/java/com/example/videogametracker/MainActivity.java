package com.example.videogametracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_gameName, et_SystemName, et_completed;

    private TextView game_list;

    private Database helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initializations();
    }

    private void initializations(){
        et_gameName = findViewById(R.id.et_gameName);
        et_SystemName = findViewById(R.id.et_SystemName);
        et_completed = findViewById(R.id.et_completed);
        game_list = findViewById(R.id.game_list);


        helper = new Database(this);

    }
    public void addData(View view){
        String game = et_gameName.getText().toString();
        String system = et_SystemName.getText().toString();
        String complete = et_completed.getText().toString();

        if (!(game.isEmpty() || system.isEmpty() || complete.isEmpty())) {

            ContentValues values = new ContentValues();

            values.put(Database.COL_0, game);
            values.put(Database.COL_1, system);
            values.put(Database.COL_2, complete);

            helper.insertData(values);
            Toast.makeText(this, "VALUES INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please provide all fields data", Toast.LENGTH_SHORT).show();
    }
}


    public void viewAll(View view){
        Cursor c = helper.getAlldata();

        if(c.moveToFirst()) {
            game_list.setText("Game List\n");

            do {

                String game = c.getString(0);
                String system = c.getString(1);
                String complete = c.getString(2);


                game_list.append("\nName: " + game);
                game_list.append("\nSystem: " + system);
                game_list.append("\nComplete: " + complete);
                game_list.append("\n\n");


            } while (c.moveToNext());
        }

        else{
            Toast.makeText(this, "Table is empty!", Toast.LENGTH_SHORT).show();
    }
}

    public void delete(View view) {

        String game = et_gameName.getText().toString();


        helper.deleteData(game);
    }
}