package com.smaroid.y03noti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class DisplayInfoActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialCardView cardView1, cardView2, cardView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardView1:
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:seokjong@smaroid.tk"));
                startActivity(intent);
                break;
            case R.id.cardView2:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smaroid.tk"));
                startActivity(intent1);
                break;
            case R.id.cardView3:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("onestore://common/product/0000743900"));
                try {
                    startActivity(intent2);
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "원스토어가 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
