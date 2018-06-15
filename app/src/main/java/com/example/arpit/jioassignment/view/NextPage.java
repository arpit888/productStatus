package com.example.arpit.jioassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arpit.jioassignment.R;

/**
 * Created by arpit on 15/6/18.
 */

public class NextPage extends AppCompatActivity {

    boolean isPageCompleted;
    TextView productStatus;
    Button complete;
    boolean updateAdapter = false;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(updateAdapter){
            Intent intent = getIntent();
            intent.putExtra("status",isPageCompleted);
            setResult(RESULT_OK,intent);
            finish();
        }
        setResult(RESULT_OK,null);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isPageCompleted = super.getIntent().getBooleanExtra("status",true);

        setContentView(R.layout.next_page);
        productStatus = (TextView) findViewById(R.id.statusNext);
        complete = (Button) findViewById(R.id.completebutton);
        productStatus.setText("Status:  " + String.valueOf(isPageCompleted));
        if(!isPageCompleted){
            complete.setVisibility(View.VISIBLE);
        }
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPageCompleted) {
                    isPageCompleted = true;
                    updateAdapter = true;
                    productStatus.setText("Status:  " +String.valueOf(isPageCompleted));
                    Toast.makeText(getApplicationContext(), "Status changed to completed !!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
