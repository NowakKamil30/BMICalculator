package com.kamilnowak.bmicalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.sql.Types.DOUBLE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText weightNumber=findViewById(R.id.weightEditText);
        final EditText heightNumber=findViewById(R.id.heightEditText);
        final TextView mbiValueText=findViewById(R.id.yourBMINumber);
        final TextView mbiText=findViewById(R.id.yourBMI);
        Button btn=findViewById(R.id.activeButton);
        final String[] bmiCategory={
          "Seriously Underweight",
                "Underweight",
                "Normal",
                "Overweight",
                "Obese"
        };
        final double[] bmiRange={
                16.49,
                18.49,
                24.99,
                29.99
        };
        final int[] colorArrays={
                Color.RED,
                Color.rgb(253,106,2),
                Color.GREEN
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(heightNumber.getText().toString()) && !TextUtils.isEmpty(weightNumber.getText().toString())) {
                    double height = Double.parseDouble(heightNumber.getText().toString());
                    double weight = Double.parseDouble(weightNumber.getText().toString());

                    double mbiValue = weight / (Math.pow(height / 100, 2));

                    boolean didYouFind = false;
                    mbiValue = round(mbiValue, 2);
                    for (int i = 0; i < bmiRange.length; i++) {
                        if (mbiValue <= bmiRange[i]) {
                            mbiText.setText(bmiCategory[i]);
                            int temp = i;
                            if (temp >= colorArrays.length) {
                                temp = temp - colorArrays.length;
                            }
                            mbiText.setTextColor(colorArrays[temp]);
                            mbiValueText.setTextColor(colorArrays[temp]);
                            didYouFind = true;
                            break;
                        }
                    }
                    if (!didYouFind) {
                        mbiText.setText(bmiCategory[bmiCategory.length - 1]);
                        mbiText.setTextColor(colorArrays[1]);
                        mbiValueText.setTextColor(colorArrays[1]);
                    }
                    mbiValueText.setText(mbiValue + "");
                }
            }

            public double round(double f, int places)

            {  double temp = (double) (f*(Math.pow(10, places)));

                temp = (Math.round(temp));

                temp = temp/(int)(Math.pow(10, places));

                return temp;

            }

        });
    }
}
