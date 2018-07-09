[![](https://www.jitpack.io/v/akangcupez/form-validations.svg)](https://www.jitpack.io/#akangcupez/form-validations)

# Android Form Validations
An android validation library. Currently only EditText and TextInputLayout are supported. This library is intended to reduce boilerplate in validating input form.

## Features
- Support EditText and TextInputLayout
- Support stacked-validation rules
- Auto-focus on first error view which is defined in validation process chains
- Display error text in multi-languages*
- Display custom error text
- Flexible validation using RegEx (coming soon)

***Supported Languages:**
- English
- Indonesia

## Requirements
- Android 4.2+ (SDK 17+)

## Installation
**STEP 1:** Add it to your root build.gradle at the end of repositories
```
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
```
**STEP 2:** Add the dependency
```
dependencies {
    implementation 'com.github.akangcupez:form-validations:0.1.1'
}
```

## Usage
Demo usage can be found at App folder of this repo.

For example, we have 2 EditTexts in login form:

```java
EditText editTextEmail = findViewById(R.id.edit_text_email);
EditText editTextPassword = findViewById(R.id.edit_text_password);
```

Defining variable and set callback
```java
FormValidation formValidation = new FormValidation(this);
formValidation.setCallback(new Callback() {
    @Override
    public void onValidationSuccess(){
       //do something here (eg: send data to remote server)
    }

    @Override
    public void onValidationError() {
      //do something here, or
      //this event can also be ignored, since the library will catch errors occured
      //and call setError() method directly to corresponding views
    }
});
```

Example on Button's click event:
```java
public void buttonSubmitClick(View view) {
  //define rules for each views
  Rule emailRule = new Rule.Builder()
          .add(RuleType.REQUIRED) //required field
          .add(RuleType.VALID_EMAIL) //must contains valid email format
          .create();

  Rule passwordRule = new Rule.Builder()
          .add(RuleType.REQUIRED) //required field
          .add(RuleType.BETWEEN_LENGTH, 6, 12) //password between 6 and 12 chars
          .create();

  formValidation.start()
          .validate(editTextEmail, emailRule)
          .validate(editTextPassword, passwordRule)
          .end();
}
```

## Contributing
Fork and send pull request

## License
Copyright (c) 2018 Aji Subastian. See the [LICENSE](https://github.com/akangcupez/form-validations/blob/master/LICENSE)