package com.bourque.erika.loancalculator;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view)
    {
        Double loanAmount;
        int numOfYears;
        Double yearlyInterestRate;
        Double monthlyPayment;
        Double totalCost;
        Double totalInterest;

        // Edit Text configured to only allow positive decimal numbers, no parse error
        EditText editTxtLoanAmount = (EditText) findViewById(R.id.editTxtLoanAmount);
        loanAmount = Double.parseDouble(editTxtLoanAmount.getText().toString());

        // Edit Text configured to only allow positive whole numbers, no parse error
        EditText editTxtNumOfYears = (EditText) findViewById(R.id.editTxtNumOfYears);
        numOfYears = Integer.parseInt(editTxtNumOfYears.getText().toString());

        // Edit Text configured to only allow positive decimal numbers, no parse error
        EditText editTxtYearlyInterestRate = (EditText) findViewById(R.id.editTxtYearlyInterestRate);
        yearlyInterestRate = Double.parseDouble(editTxtYearlyInterestRate.getText().toString());

        try
        {
            LoanCalculator calculator = new LoanCalculator(loanAmount, numOfYears, yearlyInterestRate);

            // Getting all values from the LoanCalculator
            monthlyPayment = calculator.getMonthlyPayment();
            totalCost = calculator.getTotalCostOfLoan();
            totalInterest = calculator.getTotalInterest();

            // Adding each value into their respective TextViews
            // Using String.format for better readability
            TextView txtViewMonthlyPayment = (TextView) findViewById(R.id.txtViewMonthlyPayment);
            txtViewMonthlyPayment.setText(String.format("%.2f", monthlyPayment));

            TextView txtViewTotalCostOfLoan = (TextView) findViewById(R.id.txtViewTotalCostOfLoan);
            txtViewTotalCostOfLoan.setText(String.format("%.2f", totalCost));

            TextView txtViewTotalInterest = (TextView) findViewById(R.id.txtViewTotalInterest);
            txtViewTotalInterest.setText(String.format("%.2f", totalInterest));
        }
        catch (IllegalArgumentException iae)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(iae.getMessage());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void clear(View view)
    {
        // Get all the EditTexts and TextViews that need to be cleared
        EditText loanAmount = (EditText) findViewById(R.id.editTxtLoanAmount);
        EditText numOfYears = (EditText) findViewById(R.id.editTxtNumOfYears);
        EditText yearlyInterestRate = (EditText) findViewById(R.id.editTxtYearlyInterestRate);

        TextView monthlyPayment = (TextView) findViewById(R.id.txtViewMonthlyPayment);
        TextView totalCostOfLoan = (TextView) findViewById(R.id.txtViewTotalCostOfLoan);
        TextView totalInterest = (TextView) findViewById(R.id.txtViewTotalInterest);

        // Set all the text to empty string
        loanAmount.setText("");
        numOfYears.setText("");
        yearlyInterestRate.setText("");
        monthlyPayment.setText("");
        totalCostOfLoan.setText("");
        totalInterest.setText("");
    }
}
