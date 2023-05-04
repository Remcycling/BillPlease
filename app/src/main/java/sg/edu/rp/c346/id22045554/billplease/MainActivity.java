package sg.edu.rp.c346.id22045554.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView calc1;
    TextView calc2;
    EditText amtText;
    EditText paxText;
    ToggleButton SvsTBtn;
    ToggleButton gstTBtn;
    EditText discText;
    RadioButton cashPick;
    RadioButton pnPick;
    RadioGroup payment;
    Button splitBtn;
    Button resetBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc1 = findViewById(R.id.textView2);
        calc2 = findViewById(R.id.textView3);
        resetBtn = findViewById(R.id.resetbtn);
        splitBtn = findViewById(R.id.splitbtn);
        amtText = findViewById(R.id.amtEnter);
        paxText = findViewById(R.id.paxEnter);
        discText = findViewById(R.id.discEnter);
        SvsTBtn = findViewById(R.id.nosvsTB);
        gstTBtn = findViewById(R.id.gstTB);
        cashPick = findViewById(R.id.cashPay);
        pnPick = findViewById(R.id.payNow);

        payment = findViewById(R.id.payment);




        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Read value from edittext
                String strAmount = amtText.getText().toString();
                String strPax = paxText.getText().toString();
                String strDis = discText.getText().toString();
                //convert to double
                double amt = Double.parseDouble(strAmount);
                double pax = Double.parseDouble(strPax);
                double dis = 1 - Double.parseDouble(strDis) / 100.0;

                // Code for the action
               double ttl = 0.0;
               double discount = 0.0;

                if (SvsTBtn.isChecked()) {
                    ttl = amt * 1.1;
                }
                if (gstTBtn.isChecked()){
                    ttl = amt  * 1.07;
                }
                if (dis > 0) {
                    ttl = dis*ttl;
                }
                else {
                    ttl = ttl;
                }

                String total = "Total bill: " + ttl;
                calc1.setText(total);

                double each = ttl /pax;
                String divide = "Each pays: " + each;

                String op = "";
                int cashChecked = payment.getCheckedRadioButtonId();
                if (cashChecked == R.id.cashPay) {
                    op = " via cash";
                } else {
                    op = " via payNow to 912345678";
                }
                calc2.setText(divide + op);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                amtText.setText("");
                paxText.setText("");
                discText.setText("");
                cashPick.setChecked(false);
                pnPick.setChecked(false);
                calc1.setText("");
                calc2.setText("");
            }
        });




    }
}