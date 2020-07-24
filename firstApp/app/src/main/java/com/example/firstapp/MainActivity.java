package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_LONG).show();
    }

    public void count(View view) {
        TextView mTvTotal = findViewById(R.id.tv_total);
        total += 1;
        mTvTotal.setText(String.valueOf(total));
    }

    public void test(View view) {
        Toast.makeText(this, "Mensagem de teste", Toast.LENGTH_LONG).show();
    }
}
