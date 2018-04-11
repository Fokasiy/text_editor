package com.texteditor.texteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit_text_view);
        mTextView = findViewById(R.id.text_view);

        handleTextChanges();
    }

    private void handleTextChanges(){
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence)){
                    String resultText = TextEditHelper.getProperString(charSequence.toString());
                    mTextView.setText(resultText);
                }
                else{
                    mTextView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.text_examples_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.text1:{
                mEditText.setText("with its powerful tools and dazzling effects,Keynote makes it Easy to create stunning and memorable presentations. ");
                return true;
            }
            case R.id.text2:{
                mEditText.setText("See Who you ’re working with ... While you’re editing, a separate list lets you quickly see who else is in the presentation.");
                return true;
            }

            case R.id.clear_button:{
                mEditText.setText("");
                return true;
            }

            default: return super.onOptionsItemSelected(item);
        }
    }
}
