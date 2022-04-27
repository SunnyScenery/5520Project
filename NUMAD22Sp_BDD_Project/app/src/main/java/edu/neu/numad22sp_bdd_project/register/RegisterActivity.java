package edu.neu.numad22sp_bdd_project.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import edu.neu.numad22sp_bdd_project.R;
import edu.neu.numad22sp_bdd_project.home.HomeActivity;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RegisterViewModel registerViewModel;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private EditText name_EditText;
    private EditText username_EditText;
    private EditText password_EditText;
    private EditText dateOfBirth_EditText;
    private Spinner gender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerViewModel = new ViewModelProvider(this, new RegisterViewModelFactory()).get(RegisterViewModel.class);


        //initialize cloud firestore database and authentication
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Setup dropdown list for gender
        gender = findViewById(R.id.gender);
        String[] genderList = new String[]{"Male", "Female", "Other", "Prefer not to specify"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genderList);
        gender.setAdapter(adapter);

        // Get access to all user input components on UI
        name_EditText = findViewById(R.id.name);
        username_EditText = findViewById(R.id.username);
        password_EditText = findViewById(R.id.password);
        dateOfBirth_EditText = findViewById(R.id.DOB);
        final Button registerButton = findViewById(R.id.register);

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                registerButton.setEnabled(registerFormState.isDataValid());

                if (registerFormState.getNameError() != null) {
                    name_EditText.setError(getString(registerFormState.getNameError()));
                }
                if (registerFormState.getUsernameError() != null) {
                    username_EditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    password_EditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getDOBError() != null) {
                    dateOfBirth_EditText.setError(getString(registerFormState.getDOBError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.registerDataChanged(username_EditText.getText().toString(),
                        password_EditText.getText().toString(), name_EditText.getText().toString(),
                        dateOfBirth_EditText.getText().toString(), gender.getSelectedItem().toString()
                        );
            }
        };

        // Setup listeners for input field changes
        username_EditText.addTextChangedListener(afterTextChangedListener);
        password_EditText.addTextChangedListener(afterTextChangedListener);
        dateOfBirth_EditText.addTextChangedListener(afterTextChangedListener);
        registerButton.setOnClickListener(this);

        gender.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                registerViewModel.registerDataChanged(username_EditText.getText().toString(),
                        password_EditText.getText().toString(), name_EditText.getText().toString(),
                        dateOfBirth_EditText.getText().toString(), gender.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                registerViewModel.registerDataChanged(username_EditText.getText().toString(),
                        password_EditText.getText().toString(), name_EditText.getText().toString(),
                        dateOfBirth_EditText.getText().toString(), gender.getSelectedItem().toString());
            }

        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.register) {
            createAccount(username_EditText.getText().toString(), password_EditText.getText().toString());
        }
    }

    public void addUserData(String UID, String name, String dob, String gender) {
        Map<String,String> user = new HashMap<>();
        user.put("Name", name);
        user.put("DOB", dob);
        user.put("Gender", gender);

        db.collection("users").document(UID).set(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("SUCCESS","DocumentSnapshot added with ID:");
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Registration Failed", "Error adding document", e);
            }
        });
    }

    public void createAccount(String email, String password) {
        // START create_user_with_email
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    addUserData(user.getUid(), name_EditText.getText().toString(), dateOfBirth_EditText.getText().toString(),
                            gender.getSelectedItem().toString());
                    updateUIWithUser(user);
                } else {
                    updateUIWithUser(null);
                }
            }
        });
        // END create_user_with_email
    }

    private void updateUIWithUser(FirebaseUser user) {
        if (user == null) {
            Toast.makeText(RegisterActivity.this,"Registration Failed! The E-mail is already in use",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String welcome = "Welcome " + user.getEmail() + "!";
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
        finish();
    }

}

