package www.bgctubedu.firebasedemofaisal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailed,passed;
    Button signUpbtn;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBarId);
        emailed = findViewById(R.id.emailEdid);
        passed = findViewById(R.id.passEdid);
        signUpbtn = findViewById(R.id.signUpBtnId);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignUp();
            }
        });
        
    }

    private void userSignUp() {
        String email=emailed.getText().toString().trim();
        String pass=passed.toString().toString().trim();
        if (email.isEmpty()){
            emailed.setError("enter an email address");
            emailed.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailed.setError("enter a valid email address");
            emailed.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            passed.setError("enter a password");
            passed.requestFocus();
            return;
        }
        if(pass.length()<6){
            passed.setError("enter a valid password");
            passed.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Sign up is successfull",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Sign up is not successfull",Toast.LENGTH_SHORT).show();
                }


                }
        });
    }
}
