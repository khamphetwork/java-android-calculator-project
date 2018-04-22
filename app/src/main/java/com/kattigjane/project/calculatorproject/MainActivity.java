package com.kattigjane.project.calculatorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // x value - first input, before add operator
    private String x = "";
    // y value - second input, after added operator
    private String y = "";
    // result - result of calculation
    // and also the text that will show in application
    private String result = "0";

    private String operator;

    private boolean hasOperator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtResult = findViewById(R.id.txtResult);

        // set text show in application with 'this.result' value
        txtResult.setText(this.result);

    }

    // method to set 'x' and 'y' values
    public void setInputValue (View view) {

        Button v = (Button) view;
        TextView txtResult = findViewById(R.id.txtResult);
        Integer value = Integer.valueOf(v.getText().toString());

        // the condition check that if the starter value is already 0
        // then when you press '0' again, it won't add more '0', ex: 000
        if (!(txtResult.getText().toString() == "0" && value == 0)) {

            // if 'x', 'y' are empty, but 'result' is not empty
            // then empty the result value
            // this condition is for preparing for below next condition
            if (this.x == "" && this.y == "" && this.result != "") {
                this.result = "";
            }

            // if already has operator
            // then set 'y' value, because set 'x' value is before set operator
            // else if not have operator
            // then set 'x' value
            if (hasOperator) {
                this.y = this.y + value;
            } else if (!hasOperator) {
                this.x = this.x + value;
            }

            this.result = this.result + value;
            txtResult.setText(this.result);
        }

    }

    // method for setting operator
    // which will work differently according to condition
    public void setOperator (View view) {

        TextView txtResult = findViewById(R.id.txtResult);
        Button o = (Button) view;
        String operator = o.getText().toString();

        // if 'x' and 'y' are not empty
        // then don't need to set operator, just calculate and show
        if (this.x != "" && this.y != "") {
            this.calculate(view);
        }

        // if 'x' and 'y' are empty but 'result' is not empty
        // this condition is for reusing result to continue calculate with other value
        // change old 'result' value to 'x' value and set operator
        // show operator after 'x' value
        if (!this.hasOperator && this.x == "" && this.y == "" && this.result != "") {

            this.x = this.result;

            this.operator = operator;
            this.hasOperator = true;

            this.result = this.x + " " + operator + " ";

            txtResult.setText(this.result);
        } else if (!this.hasOperator && this.x != "" && this.y == "") {
            // if don't have operator, 'x' is not empty, and 'y' empty
            // set operator and show operator after 'x' value

            this.operator = operator;
            this.hasOperator = true;

            this.result = this.result + " " + operator + " ";

            txtResult.setText(this.result);
        } else if (this.hasOperator && this.x != "" && this.y == "") {
            // in case you want to change operator
            // if already set 'x' value and already set operator
            // then change operator

            // remove string of old operator before adding new operator
            String tmp = this.result;
            tmp = tmp.substring(0, tmp.length() - 3);
            this.result = tmp;

            this.operator = operator;
            this.hasOperator = true;

            this.result = this.result + " " + operator + " ";

            txtResult.setText(this.result);
        }
    }

    // calculate method
    public void calculate (View view) {

        // if 'x' and 'y' is not empty, then doing calculate base on operator chose
        // and when finish calculate, empty 'x', 'y' and 'operator'
        // but keep 'result' value.
        if (this.x != "" && this.y != "") {
            this.hasOperator = false;

            TextView txtResult = findViewById(R.id.txtResult);

            Float xx = Float.valueOf(this.x);
            Float yy = Float.valueOf(this.y);

            Float result = null;
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

    // reset every value
    public void clear (View view) {
        this.x = "";
        this.y = "";
        this.result = "0";
        this.operator = "";
        this.hasOperator = false;

        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText("0");
    }

}
