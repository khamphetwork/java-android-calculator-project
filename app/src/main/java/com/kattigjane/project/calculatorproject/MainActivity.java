package com.kattigjane.project.calculatorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String x = "";
    private String y = "";
    private String result = "0";

    private String operator;

    private boolean hasOperator = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(this.result.toString());

    }

    public void setInputValue (View view) {
        Button v = (Button)view;
        TextView txtResult = findViewById(R.id.txtResult);

        String value = v.getText().toString();

        System.out.println("val " + txtResult.getText().toString());


        if (txtResult.getText().toString() == "0" && value == "0") {
            txtResult.setText("0");
        } else {

            if (this.x == "" && this.y == "" && this.result != "") {
                this.result = "";
            }

            if (hasOperator) {
                this.y = this.y + value;
            } else if (!hasOperator) {
                this.x = this.x + value;
            }

            this.result = this.result + value;

            txtResult.setText(this.result);
        }


    }

    public void setOperator (View view) {

        if (this.x != "" && this.y != "") {
            this.calculate(view);
        }

        if (this.x != "" && this.y == "") {
            TextView txtResult = findViewById(R.id.txtResult);

            Button o = (Button) view;

            String operator = o.getText().toString();

            this.operator = operator;
            this.hasOperator = true;

            this.result = this.result + " " + operator + " ";

            System.out.println("o " + this.operator);

            txtResult.setText(this.result);
        }

        if (this.x == "" && this.y == "" && this.result != "") {

            this.x = this.result;

            TextView txtResult = findViewById(R.id.txtResult);

            Button o = (Button) view;

            String operator = o.getText().toString();

            this.operator = operator;
            this.hasOperator = true;

            this.result = this.x + " " + operator + " ";

            txtResult.setText(this.result);
        }


    }

    public void calculate (View view) {

        if (this.x != "" && this.y != "") {
            this.hasOperator = false;

            TextView txtResult = findViewById(R.id.txtResult);

            Integer xx = Integer.valueOf(this.x);
            Integer yy = Integer.valueOf(this.y);

            Integer result = null;
            switch (this.operator) {
                case "+":
                    result = xx + yy;
                    txtResult.setText(result.toString());
                    break;
                case "-":
                    result = xx - yy;
                    txtResult.setText(result.toString());
                    break;
                case "x":
                    result = xx * yy;
                    txtResult.setText(result.toString());
                    break;
                case "/":
                    result = xx / yy;
                    txtResult.setText(result.toString());
                    break;
            }

            txtResult.setText(result.toString());

            this.x = "";
            this.y = "";
            this.operator = "";
            this.result = result.toString();
        }

    }

    public void clear (View view) {
        this.x = "";
        this.y = "";
        this.result = "";
        this.operator = "";
        this.hasOperator = false;

        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText("0");
    }

}
