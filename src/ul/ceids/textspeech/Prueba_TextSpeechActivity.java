package ul.ceids.textspeech;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
//http://developer.android.com/resources/articles/tts.html
public class Prueba_TextSpeechActivity extends Activity implements OnInitListener{
    
	private static final int MY_DATA_CHECK_CODE = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void download(View v){
    	Log.v("","Hola");
    	Intent checkIntent = new Intent();
    	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
    	startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
    	
    }
    
    public void speak(View v){
    	mTts.setLanguage(Locale.ENGLISH);
//    	mTts.setLanguage(new Locale("spa", "ESP"));
//    	mTts.speak("Hello, World", TextToSpeech.QUEUE_ADD, null);
    	EditText et = (EditText)findViewById(R.id.et_text);
    	mTts.speak(et.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    	
    }
    
    
    
    private TextToSpeech mTts;
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                mTts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }


	@Override
	public void onInit(int status) {

		
	}
    
    
    
}