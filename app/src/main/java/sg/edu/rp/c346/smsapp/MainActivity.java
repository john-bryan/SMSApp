package sg.edu.rp.c346.smsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etReceipent;
    EditText etMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        etReceipent = findViewById(R.id.editTextTo);
        etMessage = findViewById(R.id.editTextTo);
        btnSend = findViewById(R.id.buttonSend);

        final String receipent = etReceipent.getText().toString();
        final String message = etMessage.getText().toString();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] array = etReceipent.getText().toString().split(".");

                for(int x = 0; x< array.length; x++) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(array[x], null, etMessage.getText().toString(), null, null);

                }
                Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_LONG);
                etMessage.setText("");
            }
        });

    }

    private void checkPermission() {
        int permissionSendSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permissionRecvSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);
        if (permissionSendSMS != PackageManager.PERMISSION_GRANTED &&
                permissionRecvSMS != PackageManager.PERMISSION_GRANTED) {
            String[] permissionNeeded = new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permissionNeeded, 1);
        }
    }

}
