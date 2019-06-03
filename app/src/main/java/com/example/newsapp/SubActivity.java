package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {
    Intent i;
    //TextView tv;
    WebView wv;
    String url_mae = "https://www.google.com/search?q=";
    String url_ushiro = "&rlz=1C1CHBF_jaJP780JP780&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjZ7e6u9rriAhWV-mEKHTZIAcgQ_AUIDygC&biw=1366&bih=657";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        LinearLayout ll =new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        Intent i = getIntent();
        String keyword = i.getStringExtra("KEYWORD");
        String url = url_mae + keyword + url_ushiro;
//        tv = new TextView(this);
//        tv.setText(keyword);

        wv = new WebView(this);  //Webサイト出力のためのビューの生成
        wv.setWebViewClient(new WebViewClient());  //Webビュークライアントの設定
        wv.loadUrl(url);  //URLの設定

        Button returnButton = new Button(this);
        returnButton.setText("大学一覧に戻る");
       // setContentView(wv);
        //ll.addView(tv);
        ll.addView(returnButton);
        ll.addView(wv);


//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class ExSampleItemClickListener implements AdapterView.OnItemClickListener {  //リストビューのアイテムクリック時のイベント処理
        public void onItemClick(AdapterView<?> v, View iv, int pos, long id) {
            String url_mae = "https://www.google.com/search?q=";
            String url_ushiro = "&rlz=1C1CHBF_jaJP780JP780&source=lnms&tbm=nws&sa=X&ved=0ahUKEwjZ7e6u9rriAhWV-mEKHTZIAcgQ_AUIDygC&biw=1366&bih=657";  //URLの設定

            TextView tmp = (TextView) iv;
            String url = url_mae + (String)tmp.getText() + url_ushiro;

        }
    }
}
