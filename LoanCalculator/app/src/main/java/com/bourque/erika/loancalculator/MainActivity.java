package com.bourque.erika.loancalculator;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting the LinearLayout Object
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        // Adding the views to it
        // Various Params
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f);
        LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

        // First Row
        LinearLayout llFirstRow = new LinearLayout(this);
        llFirstRow.setLayoutParams(llParams);
        llFirstRow.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvLoanAmount = new TextView(this);
        tvLoanAmount.setLayoutParams(tvParams);
        tvLoanAmount.setText(R.string.strLoanAmount);
        llFirstRow.addView(tvLoanAmount);

        EditText etLoanAmount = new EditText(this);
        etLoanAmount.setLayoutParams(etParams);
        etLoanAmount.setHint(R.string.hintLoanAmount);
        etLoanAmount.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLoanAmount.setId(R.id.editTxtLoanAmount);
        llFirstRow.addView(etLoanAmount);

        mainLayout.addView(llFirstRow);

        // Second Row
        LinearLayout llSecondRow = new LinearLayout(this);
        llSecondRow.setLayoutParams(llParams);
        llSecondRow.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvNumYears = new TextView(this);
        tvNumYears.setLayoutParams(tvParams);
        tvNumYears.setText(R.string.strNumOfYears);
        llSecondRow.addView(tvNumYears);

        EditText etNumYears = new EditText(this);
        etNumYears.setLayoutParams(etParams);
        etNumYears.setHint(R.string.hintNumOfYears);
        etNumYears.setInputType(InputType.TYPE_CLASS_NUMBER);
        etNumYears.setId(R.id.editTxtNumOfYears);
        llSecondRow.addView(etNumYears);

        mainLayout.addView(llSecondRow);

        // Third Row
        LinearLayout llThirdRow = new LinearLayout(this);
        llThirdRow.setLayoutParams(llParams);
        llThirdRow.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvInterestRate = new TextView(this);
        tvInterestRate.setLayoutParams(tvParams);
        tvInterestRate.setText(R.string.strYearlyInterestRate);
        llThirdRow.addView(tvInterestRate);

        EditText etInterestRate = new EditText(this);
        etInterestRate.setLayoutParams(etParams);
        etInterestRate.setHint(R.string.hintYearlyInterestRate);
        etInterestRate.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etInterestRate.setId(R.id.editTxtYearlyInterestRate);
        llThirdRow.addView(etInterestRate);

        mainLayout.addView(llThirdRow);

        // Button Row
        LinearLayout llButtonRow = new LinearLayout(this);
        llButtonRow.setLayoutParams(llParams);
        llButtonRow.setOrientation(LinearLayout.HORIZONTAL);

        Button btnCalc = new Button(this);
        btnCalc.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f));
        btnCalc.setText(R.string.strCalculate);
        // btnCalc.setOnClickListener(new View.OnClickListener() {});
        llButtonRow.addView(btnCalc);

        Button btnClear = new Button(this);
        btnClear.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        btnClear.setText(R.string.strClear);
        // btnClear.setOnClickListener(new View.OnClickListener() {});
        llButtonRow.addView(btnClear);

        mainLayout.addView(llButtonRow);

        // Result Title Row
        TextView tvResults = new TextView(this);
        tvResults.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvResults.setText(R.string.strResults);
        mainLayout.addView(tvResults);

        //
    }

    /**
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
     */
}
