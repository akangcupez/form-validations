package com.akangcupez.formvalidation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.akangcupez.formvalidation.rule.Rule;
import com.akangcupez.formvalidation.rule.RuleEntity;
import com.akangcupez.formvalidation.rule.RuleType;
import com.akangcupez.formvalidation.util.Helper;
import com.akangcupez.formvalidation.util.Validator;

import java.util.List;

/**
 * @author Aji Subastian (akangcupez@gmail.com)
 */
@SuppressWarnings({"WeakerAccess, unused"})
public class FormValidation {

    private static final String TAG = FormValidation.class.getSimpleName();
    private static ValidationCallback sCallback;

    private Context mContext;
    private boolean mResult;
    private boolean mHasFocus;

    public FormValidation(Context context) {
        mContext = context;
        initialize();
    }

    private void initialize() {
        mResult = true;
        mHasFocus = false;
    }

    public void setCallback(ValidationCallback callback) {
        sCallback = callback;
    }

    @Deprecated
    public FormValidation addCallback(ValidationCallback callback) {
        sCallback = callback;
        return this;
    }

    public FormValidation start() {
        initialize();
        return this;
    }

    public FormValidation validate(View view, Rule validationRule) {
        return validate(view, validationRule, null);
    }

    public FormValidation validate(View view, Rule rule, @Nullable String errorString) {
        try {
            List<RuleEntity> rules = rule.getValidationRules();
            resetError(view);
            String s = getStringValue(view);
            if (s != null && rules != null && rules.size() > 0) {
                RuleEntity entity = null;
                boolean hasError = false;
                for (RuleEntity ruleEntity : rules) {
                    entity = ruleEntity;
                    RuleType ruleType = entity.getRuleType();
                    Object v1 = entity.getRuleValue1();
                    Object v2 = entity.getRuleValue2();
                    if (ruleType == RuleType.REQUIRED) {
                        hasError = (!Validator.hasValue(s));
                    } else if (ruleType == RuleType.EXACT_LENGTH) {
                        hasError = (!Validator.hasExactLength(s, (int) v1));
                    } else if (ruleType == RuleType.BETWEEN_LENGTH) {
                        hasError = (!Validator.hasBetweenLength(s, (int) v1, (int) v2));
                    } else if (ruleType == RuleType.VALID_EMAIL) {
                        hasError = (!Validator.isValidEmail(s));
                    }
                    if (hasError) break;
                }
                if (hasError) showError(view, entity, errorString);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return this;
    }

    @Nullable
    private String getStringValue(View v) {
        try {
            if (v instanceof TextInputLayout) {
                EditText et = ((TextInputLayout) v).getEditText();
                if (et != null && et.getText() != null) return et.getText().toString();
            } else if (v instanceof EditText) {
                return ((EditText) v).getText().toString();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return null;
    }

    private void resetError(View v) {
        if (v instanceof TextInputLayout) {
            ((TextInputLayout) v).setErrorEnabled(false);
            ((TextInputLayout) v).setError(null);
        } else if (v instanceof EditText) {
            ((EditText) v).setError(null);
        }
    }

    @Nullable
    private CharSequence getViewHint(View v) {
        if (v instanceof TextInputLayout) {
            return ((TextInputLayout) v).getHint();
        } else if (v instanceof EditText) {
            return ((EditText) v).getHint();
        }

        return null;
    }

    private void showError(View v, RuleEntity re, @Nullable String es) {

        mResult = false;
        CharSequence h = getViewHint(v);
        String str = Helper.getErrorString(mContext, re, h);
        String err = (!(TextUtils.isEmpty(es))) ? es : str;

        if (v instanceof TextInputLayout) {
            ((TextInputLayout) v).setErrorEnabled(true);
            ((TextInputLayout) v).setError(err);
        } else if (v instanceof EditText) {
            ((EditText) v).setError(err);
        }

        if (!mHasFocus) {
            v.requestFocus();
            mHasFocus = true;
        }
    }

    public void end() {
        if (sCallback != null) {
            if (mResult) {
                sCallback.onValidationSuccess();
            }
            else {
                sCallback.onValidationError();
            }
        }
        else {
            Log.e(TAG, "Validation Callback is Null");
        }
    }

    public void end(ValidationCallback validationCallback) {
        if (validationCallback != null) {
            if (mResult) {
                validationCallback.onValidationSuccess();
            }
            else {
                validationCallback.onValidationError();
            }
        }
        else {
            Log.e(TAG, "Validation Callback is Null");
        }
    }

    public interface ValidationCallback {

        void onValidationSuccess();

        void onValidationError();

    }

}
