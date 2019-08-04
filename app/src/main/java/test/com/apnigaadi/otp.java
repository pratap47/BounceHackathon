package test.com.apnigaadi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {

    private EditText phonetxt;

    private EditText codetxt;
ImageView img;

    private Button btnOTP;

    public static FirebaseAuth mAuth;

    private String TAG ="xyz";

    private int btntype=0;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;

    private String mVerificationId;

    private PhoneAuthProvider.ForceResendingToken mResendToken;

    public static String phonenumber ;
    public static String mDeviceNum;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
img=findViewById(R.id.img);
img.setAlpha(.7f);
        mAuth= FirebaseAuth.getInstance();


        phonetxt=(EditText) findViewById(R.id.phoneEditText);

        codetxt=(EditText) findViewById(R.id.codeEditText);

        btnOTP= (Button)   findViewById(R.id.btnotp);

        sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
       editor=sharedPreferences.edit();

        codetxt.setEnabled(false);
//        btnOTP.setEnabled(false);

        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weathernameisenterornot();

            }
        });

        mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override

            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                //When the code is send and the enter

                // phone no. is in the phone in which app is runing this meathod is going to run


                Toast.makeText(otp.this,"Code send to your phone",Toast.LENGTH_SHORT).show();

                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override

            public void onVerificationFailed(FirebaseException e) {

                Log.d("SachinPPPPPPP",e.toString());
                //If sms didn't receive this gonna call
                codetxt.setEnabled(false);

                Toast.makeText(otp.this,"Something went wrong or Phone number is wrong",Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                //This meathod is call when ever the enter

                // phone no. is in the other mobile other than the installed app phone no.

                // The SMS verification code has been sent to the provided phone number, we

                // now need to ask the user to enter the code and then construct a credential

                // by combining the code with a verification ID.
                btnOTP.setEnabled(false);

                Toast.makeText(otp.this, verificationId, Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onCodeSent:" + verificationId);

                Toast.makeText(otp.this,"On code sent meathod",Toast.LENGTH_SHORT).show();

                // Save verification ID and resending token so we can use them later

                btntype=1;


                mVerificationId = verificationId;

                mResendToken = token;

                btnOTP.setText("Verify code");

                // ...
            }
        };


    }

    private void weathernameisenterornot() {

        String p=phonetxt.getText().toString();

        if (p.isEmpty())
        {
        phonetxt.setError("To get an OTP you need to enter a valid phone number");
        phonetxt.requestFocus();
        return;
        }
        else {
            phonetxt.setError("");
            phonetxt.clearFocus();

        }

                if(btntype==0)
        {
            phonenumber=phonetxt.getText().toString().trim();

            codetxt.setEnabled(true);

            PhoneAuthProvider.getInstance().

                    verifyPhoneNumber(phonenumber,60, TimeUnit.SECONDS,
                            otp.this,
                            mcallback);
        }
        else
        {
            String verificationcode=codetxt.getText().toString(); //verificationcode contain the OTP

            PhoneAuthCredential cridential= PhoneAuthProvider.getCredential(mVerificationId,verificationcode);

            signInWithPhoneAuthCredential(cridential);
        }



    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {

                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();


                            editor.putString("Phone",phonenumber);
                            editor.putString("Uid",mAuth.getUid());
                            editor.commit();


                            Intent intent=new Intent(otp.this, MainActivity.class);

                                //intent.putExtra( "phoneno",);

                                startActivity(intent);


                            finish();


                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            Toast.makeText(otp.this,"ERROR",Toast.LENGTH_SHORT).show();

                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


}

