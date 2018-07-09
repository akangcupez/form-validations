package com.akangcupez.czlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.akangcupez.formvalidation.FormValidation;
import com.akangcupez.formvalidation.rule.Rule;
import com.akangcupez.formvalidation.rule.RuleType;

public class DemoActivity extends AppCompatActivity implements FormValidation.ValidationCallback {

    private EditText editText1;
    private FormValidation formValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        editText1 = findViewById(R.id.edit_text_1);
        formValidation = new FormValidation(this);
        formValidation.setCallback(this);
    }

    public void submitClick(View view) {

        Rule rule1 = new Rule.Builder().add(RuleType.REQUIRED).create();

        formValidation
                .start()
                .validate(editText1, rule1)
                .end();
    }

    @Override
    public void onValidationSuccess() {
        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationError() {
        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
    }
}
