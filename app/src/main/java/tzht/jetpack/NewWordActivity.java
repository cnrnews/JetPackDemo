package tzht.jetpack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditWordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=mEditWordView.getText().toString();

                Intent intent=new Intent();
                if (TextUtils.isEmpty(word)){
                    setResult(RESULT_CANCELED,intent);
                }{
                    intent.putExtra(EXTRA_REPLY,word);
                    setResult(RESULT_OK,intent);
                }
                finish();
            }
        });
    }
}
