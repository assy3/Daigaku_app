package com.example.newsapp;

import android.content.Intent;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int SAMPLE_APP = 1;  //呼び出し元のインテントのID
    TextView tv;
    ListView lv1, lv2;
    Spinner sp;
    WebView wv;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tl = new TableLayout(this);
        tl.setOrientation(LinearLayout.VERTICAL);
        setContentView(tl);

        sendButton = new Button(this);
        sendButton.setText("音声で検索する");
        sendButton.setTextColor(Color.BLUE);
        sendButton.setTextSize(20);

        sendButton.setOnClickListener(new SampleClickListener());

        wv = new WebView(this);  //Webサイト出力のためのビューの生成
        wv.setWebViewClient(new WebViewClient());  //Webビュークライアントの設定

        tv = new TextView(this);
        tv.setText("【大学News】");
        tv.setTextColor(Color.RED);
        tv.setTextSize(30);

        lv1 = new ListView(this);  //リストビュー1の生成
        lv2 = new ListView(this);  //リストビュー2の生成
        //sp = new Spinner(this);


        String[] str = {"<あ行>",
                "・愛知県立大学","・会津大学","・青山学院大学","・秋田大学","・亜細亜大学","・石川県立大学","・茨城大学","・岩手大学","・宇都宮大学","・大阪大学","・大阪工業大学","・大阪市立大学" ,
                "<か行>","・香川大学", "・学習院大学","・神奈川大学", "・金沢大学", "・金沢学院大学",
                "・金沢工業大学", "・金沢星稜大学","・関西大学", "・関西学院大学","・関東学院大学", "・北里大学", "・九州工業大学",
                "・京都大学","・京都産業大学","・近畿大学","・金城大学","・岐阜大学","・群馬大学","・慶應義塾大学","・高知大学"
                ,"・神戸大学","・公立はこだて未来大学","・國學院大学","・国際教養大学","・駒澤大学",
                "<さ行>","・埼玉大学", "・佐賀大学","・滋賀大学", "・静岡大学", "・芝浦工業大学",
                "・島根大学","・首都大学東京","・信州大学","・上智大学",
                "<た行>","・大東文化大学","・拓殖大学","・多摩美術大学","・千葉大学","・千葉工業大学","・中央大学","・筑波大学","・帝京大学","・東海大学","・ 東京大学","・東京海洋大学","・東京外国語大学","・東京農業大学","・東京理科大学","・東洋大学","・徳島大学","・富山大学","・豊田工業大学","・同志社大学",
                "<な行>","・長岡技術科学大学","・名古屋大学","・名古屋工業大学","・奈良大学","・南山大学","・新潟大学","・日本大学",
                "<は行>","・一橋大学","・弘前大学","・フェリス女学院大学","・福井大学","・福井工業大学","・福島大学","・法政大学","・北陸大学","・北陸先端科学技術大学院大学","・北海道大学",
                "<ま行>","・三重大学","・武蔵野美術大学","・室蘭工業大学","・明治大学","・明治学院大学",
                "<や行>","・山形大学","・山口大学","・山梨学院大学","・横浜国立大学",
                "<ら行>","・立教大学","・立命館大学","・龍谷大学",
                "<わ行>","・和歌山大学","・和光大学","・早稲田大学"};  //リストビュー

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);  //テキストをリストビュー、スピナーに渡すためのアレイアダプター

        lv1.setAdapter(ad);  //リストビュー1にアレイアダプターを登録
        lv1.setOnItemClickListener(new ExSampleItemClickListener());  //リストビュー1のアイテムクリック時のリスナー登録

        ScrollView sv = new ScrollView(this);

        tl.addView(sendButton);
        tl.addView(tv);
        tl.addView(lv1);  //リニアレイアウトにリストビューを設定
        tl.addView(wv);

       // tl.addView(sendButton);

    }

    class ExSampleItemClickListener implements AdapterView.OnItemClickListener {  //リストビューのアイテムクリック時のイベント処理
        public void onItemClick(AdapterView<?> v, View iv, int pos, long id) {
            TextView tmp = (TextView) iv;
            Intent intent = new Intent(getApplication(), SubActivity.class);
            intent.putExtra("KEYWORD",  (String)tmp.getText());
            startActivity(intent);
        }
    }

    public void onActivityResult(int reqcode, int result, Intent it){
        if(reqcode == SAMPLE_APP && result == RESULT_OK){ //インテント先から結果が返ってきたときの処理
            ArrayList<String> list = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  //インテント先からのデータ取得
            String tmp = list.get(0);
            Intent intent = new Intent(getApplication(), SubActivity.class);
            intent.putExtra("KEYWORD", tmp);
            startActivity(intent);
        }
    }

    class SampleClickListener implements View.OnClickListener {
        public void onClick(View v){
            try{
                Intent it = new Intent();
                it.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);  //音声認識にアクションを設定
                it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  //音声認識の設定
                it.putExtra(RecognizerIntent.EXTRA_PROMPT, "入力してください。");  //音声認識のプロンプト文字の設定
                startActivityForResult(it, SAMPLE_APP); //結果を取得するインテントのスタート
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(),"音声認識は利用できません。",Toast.LENGTH_LONG).show();
            }
        }
    }
}