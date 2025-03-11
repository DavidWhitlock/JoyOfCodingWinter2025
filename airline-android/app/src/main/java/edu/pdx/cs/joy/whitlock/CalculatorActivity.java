package edu.pdx.cs.joy.whitlock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    static final String FLIGHT = "SUM";
    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void backToMain(View view) {
        Flight flight = new Flight(this.sum);
        Intent intent = new Intent();
        intent.putExtra(FLIGHT, flight);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void computeSum(View view) {
        EditText leftOperand = findViewById(R.id.leftOperand);
        EditText rightOperand = findViewById(R.id.rightOperand);

        String leftString = leftOperand.getText().toString();
        String rightString = rightOperand.getText().toString();

        int leftValue;
        try {
            leftValue = Integer.parseInt(leftString);

        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid left operand " + leftString, Toast.LENGTH_SHORT).show();
            return;
        }

        int rightValue;
        try {
            rightValue = Integer.parseInt(rightString);

        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Invalid right operand " + rightString, Toast.LENGTH_SHORT).show();
            return;
        }

        int sumValue = leftValue + rightValue;
        this.sum = sumValue;

        TextView sum = findViewById(R.id.sum);
        sum.setText(String.valueOf(sumValue));
    }
}