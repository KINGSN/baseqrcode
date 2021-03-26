package qrcode.app.com.darwinbark.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

import qrcode.app.com.darwinbark.R;

public class RegisterActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim,slide_in_left,slide_in_right;
    ImageView logo_image;
    TextView verifyTitle,verifySubTitle;

    TextInputEditText verifyNumber;
    private RelativeLayout verifyMobile,otpLayout,registerLayout;
    private com.chaos.view.PinView PinView;


    private String GetDeviceID(){
        TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String deviceID = null;
        int readIMEI= ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE);
        if(deviceID == null) {
            if (readIMEI == PackageManager.PERMISSION_GRANTED) {
                deviceID = android.provider.Settings.Secure.getString(this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }
        }
        return deviceID;
    }
    public String deviceid;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        }
///////////////////// Animation///////////////////////////
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        slide_in_left = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slide_in_right = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        logo_image = findViewById(R.id.logo_image);
        verifyTitle = findViewById(R.id.verifyTitle);
        verifySubTitle = findViewById(R.id.verifySubTitle);
        Button verifyotpBtn = findViewById(R.id.verifyotpBtn);


        logo_image.setAnimation(topAnim);
        verifyTitle.setAnimation(slide_in_left);
        verifySubTitle.setAnimation(slide_in_left);
////////////////////Animation ////////////////////////

//Hooks
        verifyNumber= findViewById(R.id.verifyNumber);
        Button verifyButton = findViewById(R.id.verifyBtn);
        PinView=findViewById(R.id.pinView);
        verifyMobile=(RelativeLayout)findViewById(R.id.verifyMobile);
        otpLayout=(RelativeLayout)findViewById(R.id.otpLayout);
        registerLayout=(RelativeLayout)findViewById(R.id.registerLayout);




        ////////////////

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyMobile.setVisibility(View.GONE);
                otpLayout.setVisibility(View.VISIBLE);
            }
        });


        verifyotpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyMobile.setVisibility(View.GONE);
                otpLayout.setVisibility(View.GONE);
                registerLayout.setVisibility(View.VISIBLE);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}