package com.akangcupez.czlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.akangcupez.formvalidation.FormValidation;
import com.akangcupez.formvalidation.rule.Rule;
import com.akangcupez.formvalidation.rule.RuleType;

/**
 * @author Aji Subastian (akangcupez@gmail.com)
 */
public class DemoActivity extends AppCompatActivity implements FormValidation.ValidationCallback {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private FormValidation formValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);

        formValidation = new FormValidation(this);
        formValidation.setCallback(this);
    }

    public void buttonSubmitClick(View view) {

        Rule ruleEmail = new Rule.Builder()
                .add(RuleType.REQUIRED)
                .add(RuleType.VALID_EMAIL)
                .create();

        Rule rulePassword = new Rule.Builder()
                .add(RuleType.REQUIRED)
                .add(RuleType.BETWEEN_LENGTH, 6, 12)
                .create();

        formValidation
                .start()
                .validate(editTextEmail, ruleEmail)
                .validate(editTextPassword, rulePassword)
                .end();
    }

    @Override
    public void onValidationSuccess() {
        Toast.makeText(getApplicationContext(), "VALIDATION SUCCESS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationError() {
        Toast.makeText(getApplicationContext(), "VALIDATION ERROR", Toast.LENGTH_SHORT).show();
    }
}
