package com.deitel.tipcalculator;

import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat; // for currency formatting
import android.app.Activity; // base class for activities
import android.os.Bundle; // for saving state information
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.SeekBar; // for changing custom tip percentage
import android.widget.SeekBar.OnSeekBarChangeListener; // SeekBar listener
import android.widget.TextView; // for displaying text

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends Activity {
	
	
	// currency and percent formatters 
	   private static final NumberFormat currencyFormat = 
	      NumberFormat.getCurrencyInstance();
	   private static final NumberFormat percentFormat = 
	      NumberFormat.getPercentInstance();

	   private double billAmount = 0.0; // bill amount entered by the user
	   private double customPercent = 0.18; // initial custom tip percentage
	   private TextView amountDisplayTextView; // shows formatted bill amount
	   private TextView percentCustomTextView; // shows custom tip percentage
	   private TextView tip15TextView; // shows 15% tip
	   private TextView total15TextView; // shows total with 15% tip
	   private TextView tipCustomTextView; // shows custom tip amount
	   private TextView totalCustomTextView; // shows total with custom tip
	   private TextView AmtPerPerson15TextView;
	   private TextView AmtPerPersonCustomTextView;
	   private int numOfPeople = 0;
	   private CheckBox tax;
	   
	   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState); // call superclass's version
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to the TextViews 
        // that MainActivity interacts with programmatically
        amountDisplayTextView = 
           (TextView) findViewById(R.id.amountDisplayTextView);
        
         EditText numPeopleText = 
                (EditText) findViewById(R.id.peopleNum);
        
        percentCustomTextView = 
           (TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        total15TextView = (TextView) findViewById(R.id.ttotal15TextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = 
           (TextView) findViewById(R.id.totalCustomTextView);
        
        AmtPerPerson15TextView = (TextView) findViewById(R.id.AmtPerPerson15TextView);
        AmtPerPersonCustomTextView = (TextView) findViewById(R.id.AmtPerPersonCustomTextView);
        
        
        
       
     
        tax = (CheckBox) findViewById(R.id.tax);
        
       tax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			updateStandard(); // update the 15% tip TextViews
	        updateCustom();
	        updateStandard15();
	          updateStandarCustom();
		}
	});
        
              
        // update GUI based on billAmount and customPercent 
        amountDisplayTextView.setText(
           currencyFormat.format(billAmount));
        updateStandard(); // update the 15% tip TextViews
        updateCustom(); // update the custom tip TextViews
        
        
    

        // set amountEditText's TextWatcher
        EditText amountEditText = 
           (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        
       
             
        numPeopleText.addTextChangedListener(numOfPeopleTextWatcher);
             
        
        // set customTipSeekBar's OnSeekBarChangeListener
        SeekBar customTipSeekBar = 
           (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }
    
    private void updateStandard() 
    {
    	boolean check = tax.isChecked() ;
    	
    	double tax=0;
    	
    	if(check)
    	{
    		tax=0.13;
    	}
    		
    		
    		
       // calculate 15% tip and total
       double fifteenPercentTip = billAmount * 0.15;
       double fifteenPercentTotal = billAmount + fifteenPercentTip+ ( billAmount * tax);

       // display 15% tip and total formatted as currency
       tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
       total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
    } // end method updateStandard

    // updates the custom tip and total TextViews
    
    
   private void updateCustom() 
    {
	   
	   
	   boolean check = tax.isChecked() ;
   	
   	double tax=0;
   	
   	if(check)
   	{
   		tax=0.13;
   	}
       // show customPercent in percentCustomTextView formatted as %
       percentCustomTextView.setText(percentFormat.format(customPercent));

       // calculate the custom tip and total
       double customTip = billAmount * customPercent;
       double customTotal = billAmount + customTip + ( billAmount * tax);

       // display custom tip and total formatted as currency
       tipCustomTextView.setText(currencyFormat.format(customTip));
       totalCustomTextView.setText(currencyFormat.format(customTotal));
    } // end method updateCustom
    
    
    
    private void updateStandard15()
    {
    	
    	 boolean check = tax.isChecked() ;
    	   	
    	   	double tax=0;
    	   	
    	   	if(check)
    	   	{
    	   		tax=0.13;
    	   	}
       // calculate 15% tip and total
       double fifteenPercentTip = billAmount * 0.15;
       double fifteenPercentTotal = billAmount + fifteenPercentTip + ( billAmount * tax);
       
       
       
       double sharePerPeople15 = fifteenPercentTotal/numOfPeople;
       

              
       AmtPerPerson15TextView.setText(currencyFormat.format(sharePerPeople15));
       
    } // end method updateStandard
    
    
    
    private void updateStandarCustom()
    {
boolean check = tax.isChecked() ;
    	
    	double tax=0;
    	
    	if(check)
    	{
    		tax=0.13;
    	}
    	
    
        percentCustomTextView.setText(percentFormat.format(customPercent));

        // calculate the custom tip and total
        double customTip = billAmount * customPercent;
        double customTotal = billAmount + customTip + ( billAmount * tax);

        // display custom tip and total formatted as currency
        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));
      
       
       double sharePerPeopleCustom = customTotal/numOfPeople;  
       AmtPerPersonCustomTextView.setText(currencyFormat.format(sharePerPeopleCustom));
       
    } // end method updateStandard
    
    
    
    
    
        
    
    
    
    
    // called when the user changes the position of SeekBar
    private OnSeekBarChangeListener customSeekBarListener = 
       new OnSeekBarChangeListener() 
    {
       // update customPercent, then call updateCustom
       @Override
       public void onProgressChanged(SeekBar seekBar, int progress,
          boolean fromUser) 
       {
          // sets customPercent to position of the SeekBar's thumb
          customPercent = progress / 100.0;
          updateCustom(); // update the custom tip TextViews
          updateStandarCustom();
       } // end method onProgressChanged

       @Override
       public void onStartTrackingTouch(SeekBar seekBar) 
       {
       } // end method onStartTrackingTouch

       @Override
       public void onStopTrackingTouch(SeekBar seekBar) 
       {
       } // end method onStopTrackingTouch
    }; // end OnSeekBarChangeListener

    // event-handling object that responds to amountEditText's events
    private TextWatcher amountEditTextWatcher = new TextWatcher() 
    {
       // called when the user enters a number
       @Override
       public void onTextChanged(CharSequence s, int start, 
          int before, int count) 
       {         
          // convert amountEditText's text to a double
          try
          {
             billAmount = Double.parseDouble(s.toString()) / 100.0;
          } // end try
          catch (NumberFormatException e)
          {
             billAmount = 0.0; // default if an exception occurs
          } // end catch 

          // display currency formatted bill amount
          amountDisplayTextView.setText(currencyFormat.format(billAmount));
          updateStandard(); // update the 15% tip TextViews
          updateCustom(); // update the custom tip TextViews
       } // end method onTextChanged

       @Override
       public void afterTextChanged(Editable s) 
       {
       } // end method afterTextChanged

       @Override
       public void beforeTextChanged(CharSequence s, int start, int count,
          int after) 
       {
       } // end method beforeTextChanged
    };
    
    
    
    private TextWatcher numOfPeopleTextWatcher = new TextWatcher() 
    {
       // called when the user enters a number
       @Override
       public void onTextChanged(CharSequence s, int start, 
          int before, int count) 
       {         
    	   try
          {
        	  numOfPeople = Integer.parseInt(s.toString()) ;
          } // end try
          catch (NumberFormatException e)
          {
        	  numOfPeople = 0; // default if an exception occurs
          } // end catch 

          
                   
          updateStandard15();
          updateStandarCustom();
          
       } // end method onTextChanged

       @Override
       public void afterTextChanged(Editable s) 
       {
       } // end method afterTextChanged

       @Override
       public void beforeTextChanged(CharSequence s, int start, int count,
          int after) 
       {
       } // end method beforeTextChanged
    };
    
    
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
