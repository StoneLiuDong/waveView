package com.example.administrator.chengxinyi;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private Button bt;
    private double curTime;
    private TextView tvSecond;
    TextToSpeech textToSpeech = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt= (Button) findViewById(R.id.button);
        textToSpeech=new TextToSpeech(this,this);
        saveToFile(textToSpeech,"this is a pen","test");
        tvSecond= (TextView) findViewById(R.id.showTime);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               readFromFile(textToSpeech,"test");
            }
        });
//    ssss    bt.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View view, MotionEvent motionEvent) {
//               switch (motionEvent.getAction()){
//
//                   case MotionEvent.ACTION_DOWN:
//                       curTime=System.currentTimeMillis();
//                       break;
//                   case MotionEvent.ACTION_UP:
//                       curTime=System.currentTimeMillis()-curTime;
//                       initTime();
//                       break;
//
//               }
//               return false;
//           }
//       });
    }
    public void saveToFile(TextToSpeech speech,String text,String file)
    {
        String destFileName = "/sdcard/tts/"+file+".wav";
        speech.synthesizeToFile(text, null, destFileName);
    }

    public void readFromFile(TextToSpeech speech,String file)
    {
        String destFileName = "/sdcard/tts/"+file+".wav";
        speech.addSpeech("2", destFileName);
        speech.speak("2", TextToSpeech.QUEUE_ADD, null);

    }
    private  void testSpeak(){
        textToSpeech=null;

        textToSpeech.speak("this is a test speaking",TextToSpeech.QUEUE_ADD, null);
    }
    @Override
    public void onInit(int status) {
// TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.ENGLISH);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED
                    || result == TextToSpeech.ERROR) {
                Toast.makeText(this, "数据丢失或语言不支持", Toast.LENGTH_SHORT).show();
            }
            if (result == TextToSpeech.LANG_AVAILABLE) {
                Toast.makeText(this, "支持该语言", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "初始化成功", Toast.LENGTH_SHORT).show();
        }
    }
}
