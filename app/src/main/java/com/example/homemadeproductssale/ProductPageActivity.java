package com.example.homemadeproductssale;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homemadeproductssale.DatabaseHelpers.DBHelperComment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ProductPageActivity extends AppCompatActivity {

    private static final String TAG = "Products Page";
    TextView mName, mPrice;
    ImageView imageView;
    String proName;
    int proPrice, proImage;
    ListView listView;
    EditText comment;
    Button add, update;
    DBHelperComment myDB;
    ArrayList<String> product_comment;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        Log.d(TAG, "onCreate: called");

        Toolbar toolbar = findViewById(R.id.productToolbar);
        toolbar.setTitle("Product");
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.followingBg));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        floatActionButtonOperation();

        mName = findViewById(R.id.product_name2);
        mPrice = findViewById(R.id.product_price);
        imageView = findViewById(R.id.product_image2);

        if(getIntent().hasExtra("productName") && getIntent().hasExtra("productPrice") && getIntent().hasExtra("productImage"))
        {
            proName = getIntent().getStringExtra("productName");
            proPrice = getIntent().getIntExtra("productPrice",50 );
            proImage = getIntent().getIntExtra("productImage", R.drawable.bg);

            mName.setText(proName);
            mPrice.setText(String.valueOf(proPrice));
            imageView.setImageResource(proImage);

        }

        update = findViewById(R.id.post_detail_update_comment_btn);
        listView = findViewById(R.id.listviewComment);
        product_comment = new ArrayList<>();
        comment = findViewById(R.id.post_detail_comment);
        add = findViewById(R.id.post_detail_add_comment_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new DBHelperComment(ProductPageActivity.this);

                String text = comment.getText().toString();
                if(!text.equals(" ") && myDB.addComment(text)){
                    comment.setText("");
                    displayData();
                }
            }
        });
        listViewItemLongClick();
    }

    public void floatActionButtonOperation(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Product added to the favorites.";
                int duration = Snackbar.LENGTH_SHORT;
                Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
                snackbar.setAction("Undo",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast toast = Toast.makeText(ProductPageActivity.this, "Undone!",
                                        Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                snackbar.show();
            }
        });
    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                return true;
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your Body Here";
                String shareSubject = "Your Subject Here";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Help");
                builder.setMessage("Do you need help?");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        closeOptionsMenu();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    void displayData() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                product_comment.add(cursor.getString(1));
            }
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, product_comment);
            listView.setAdapter(arrayAdapter);
        }
    }
    public void deleteTasks(View v){
        myDB.deleteAll();
        displayData();
    }

    private void listViewItemLongClick() {
        ListView myList = findViewById(R.id.listviewComment);
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myDB.deleteRow(id);
                return false;
            }
        });
    }
}
