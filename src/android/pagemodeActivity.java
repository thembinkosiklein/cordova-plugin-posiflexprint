package com.pos.printer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class pagemodeActivity extends Activity {
	Spinner myspinner_SelectPrintDirectionInPageMode;
	EditText myeditText_X;
	EditText myeditText_Y;
	EditText myeditText_Width;
	EditText myeditText_Height;
	EditText myeditText_Position;
	EditText myeditText_TextToPrint;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagemode);
        //--------------------------
        myeditText_X=(EditText)findViewById(R.id.editText_pagemode_X);
        myeditText_Y=(EditText)findViewById(R.id.editText_pagemode_Y);
        myeditText_Width=(EditText)findViewById(R.id.editText_pagemode_Width);
        myeditText_Height=(EditText)findViewById(R.id.editText_pagemode_Height);
        myeditText_Position=(EditText)findViewById(R.id.editText_pagemode_Position);
        myeditText_TextToPrint=(EditText)findViewById(R.id.editText_pagemode_TextToPrint);
        //--------------------------
        myspinner_SelectPrintDirectionInPageMode = (Spinner) findViewById(R.id.spinner_pagemode_SelectPrintDirectionInPageMode);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.pagemode_SelectPrintDirectionInPageMode, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_SelectPrintDirectionInPageMode.setAdapter(adapter);
    }

    public void StandardMode_(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectPrintMode(Main.portName,Main.portSettings,0);
    	} else {
    		PrinterFunctions.SelectPrintMode(Main.portName,Main.portSettings,0);
    	}
    }
    
    public void PageMode_(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectPrintMode(Main.portName,Main.portSettings,1);
    	} else {
    		PrinterFunctions.SelectPrintMode(Main.portName,Main.portSettings,1);
    	}
    }
    
    public void CancelPrintDataInPageMode(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.CancelPrintDataInPageMode(Main.portName,Main.portSettings);
    	} else {
    		PrinterFunctions.CancelPrintDataInPageMode(Main.portName,Main.portSettings);
    	}
    }
    
    public void PrintDataInPageMode(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PrintDataInPageMode(Main.portName,Main.portSettings);
        	PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
    	} else {
    		PrinterFunctions.PrintDataInPageMode(Main.portName,Main.portSettings);
    		PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
    	}
    }
    
    public void SelectPrintDirectionInPageMode(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectPrintDirectionInPageMode(
    				Main.portName,
    				Main.portSettings,
    				myspinner_SelectPrintDirectionInPageMode.getSelectedItemPosition());
    	} else {
    		PrinterFunctions.SelectPrintDirectionInPageMode(
    				Main.portName,
    				Main.portSettings,
    				myspinner_SelectPrintDirectionInPageMode.getSelectedItemPosition());
    	}
    }
    
    public void SetPrintingAreaInPageMode(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SetPrintingAreaInPageMode(
	    			Main.portName,
	    			Main.portSettings,
	    			Integer.parseInt(myeditText_X.getText().toString()),
	    			Integer.parseInt(myeditText_Y.getText().toString()),
	    			Integer.parseInt(myeditText_Width.getText().toString()),
	    			Integer.parseInt(myeditText_Height.getText().toString()));
    	} else {
	    	PrinterFunctions.SetPrintingAreaInPageMode(
	    			Main.portName,
	    			Main.portSettings,
	    			Integer.parseInt(myeditText_X.getText().toString()),
	    			Integer.parseInt(myeditText_Y.getText().toString()),
	    			Integer.parseInt(myeditText_Width.getText().toString()),
	    			Integer.parseInt(myeditText_Height.getText().toString()));
    	}
    }
    
    public void SetPrintPositionInPageMode(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SetPrintPositionInPageMode(
        			Main.portName,
        			Main.portSettings,
        			Integer.parseInt(myeditText_Position.getText().toString()));
    	} else {
    		PrinterFunctions.SetPrintPositionInPageMode(
    				Main.portName,
    				Main.portSettings,
    				Integer.parseInt(myeditText_Position.getText().toString()));
    	}
    }
    
    public void pagemode_PrintText(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PrintText(Main.portName,Main.portSettings,0,0,0,0,0,0,0,0,myeditText_TextToPrint.getText().toString());
    	} else {
    		PrinterFunctions.PrintText(Main.portName,Main.portSettings,0,0,0,0,0,0,0,0,myeditText_TextToPrint.getText().toString());
    	}
    }
}
