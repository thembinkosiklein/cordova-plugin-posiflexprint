package com.pos.printer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class samplereceiptActivity extends Activity {
	EditText myeditText_SetLineSpacing;
	Spinner myspinner_SelectCharacterFont;
	Spinner myspinner_SelectCodePage;
	Spinner myspinner_SelectInternationalCharacter;
	
	EditText myeditText_samplereceipt_Str1;
	EditText myeditText_samplereceipt_Str2;
	EditText myeditText_samplereceipt_SelectCodePage_Sample;
	EditText myeditText_samplereceipt_SelectInternationalCharacter_Sample;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.samplereceipt);
        
        myeditText_SetLineSpacing=(EditText)findViewById(R.id.editText_samplereceipt_SetLineSpacing);

        myeditText_samplereceipt_Str1=(EditText)findViewById(R.id.editText_samplereceipt_Str1);
        myeditText_samplereceipt_Str2=(EditText)findViewById(R.id.editText_samplereceipt_Str2);
        myeditText_samplereceipt_SelectCodePage_Sample=(EditText)findViewById(R.id.editText_samplereceipt_SelectCodePage_Sample);
        myeditText_samplereceipt_SelectInternationalCharacter_Sample=(EditText)findViewById(R.id.editText_samplereceipt_SelectInternationalCharacter_Sample);
        //--------------------------
        myspinner_SelectCharacterFont = (Spinner) findViewById(R.id.spinner_samplereceipt_SelectCharacterFont);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.samplereceipt_SelectCharacterFont, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_SelectCharacterFont.setAdapter(adapter);
        
        myspinner_SelectCodePage = (Spinner) findViewById(R.id.spinner_samplereceipt_SelectCodePage);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.samplereceipt_SelectCodePage, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_SelectCodePage.setAdapter(adapter2);
        
        myspinner_SelectInternationalCharacter = (Spinner) findViewById(R.id.spinner_samplereceipt_SelectInternationalCharacter);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.samplereceipt_SelectInternationalCharacter, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_SelectInternationalCharacter.setAdapter(adapter3);
    }

    public void SampleReceipt_(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PrintSampleReceipt(Main.portName,Main.portSettings);
    	} else {
    		PrinterFunctions.PrintSampleReceipt(Main.portName,Main.portSettings);
    	}
    }
    
    public void CnSampleReceipt_(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PrintSampleReceiptCn(Main.portName,Main.portSettings);
    	} else {
    		PrinterFunctions.PrintSampleReceiptCn(Main.portName,Main.portSettings);
    	}
    }
    
    public void samplereceipt_SetLineSpacing(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SetLineSpacing(Main.portName,Main.portSettings,Integer.parseInt(myeditText_SetLineSpacing.getText().toString()));
    	} else {
    		PrinterFunctions.SetLineSpacing(Main.portName,Main.portSettings,Integer.parseInt(myeditText_SetLineSpacing.getText().toString()));
    	}
    }
    
    public void samplereceipt_SelectCharacterFont(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectCharacterFont(Main.portName,Main.portSettings,myspinner_SelectCharacterFont.getSelectedItemPosition());
    	} else {
    		PrinterFunctions.SelectCharacterFont(Main.portName,Main.portSettings,myspinner_SelectCharacterFont.getSelectedItemPosition());
    	}
    }
    
    public void samplereceipt_SelectCharacterFont_Sample(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectCharacterFont(
	    			Main.portName,
	    			Main.portSettings,
	    			0);
	    	
    		PrinterFunctionsLAN.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			myeditText_samplereceipt_Str1.getText().toString());
	    	
    		PrinterFunctionsLAN.SelectCharacterFont(
	    			Main.portName,
	    			Main.portSettings,
	    			1);
	    	
    		PrinterFunctionsLAN.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			myeditText_samplereceipt_Str2.getText().toString()+"\n");
	    	
    		PrinterFunctionsLAN.SelectCharacterFont(
	    			Main.portName,
	    			Main.portSettings,
	    			0);
	    	
    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
    	} else {
	    	PrinterFunctions.SelectCharacterFont(
	    			Main.portName,
	    			Main.portSettings,
	    			0);
	    	
	    	PrinterFunctions.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			myeditText_samplereceipt_Str1.getText().toString());
	    	
	    	PrinterFunctions.SelectCharacterFont(
	    			Main.portName,
	    			Main.portSettings,
	    			1);
	    	
	    	PrinterFunctions.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			myeditText_samplereceipt_Str2.getText().toString()+"\n");
	    	
	    	PrinterFunctions.SelectCharacterFont(
	    			Main.portName,
	    			Main.portSettings,
	    			0);
	    	
	    	PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
    	}
    }
    
    public void samplereceipt_SelectCodePage(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectCodePage(
	    			Main.portName,
	    			Main.portSettings,
	    			myspinner_SelectCodePage.getSelectedItem().toString());
    	} else {
	    	PrinterFunctions.SelectCodePage(
	    			Main.portName,
	    			Main.portSettings,
	    			myspinner_SelectCodePage.getSelectedItem().toString());
    	}
    }
    public void samplereceipt_SelectCodePage_Sample(View view)
    {
    	int ASCII=HexStr2Int(myeditText_samplereceipt_SelectCodePage_Sample.getText().toString());
    	char str=(char)ASCII;
    	
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"Code Page Sample\n" +
	    			myspinner_SelectCodePage.getSelectedItem().toString()+
	    			" : ");
	    	
    		byte mByte[]={(byte)ASCII};
    		PrinterFunctionsLAN.PrintTextKanji(
    				Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			mByte);
    		
    		PrinterFunctionsLAN.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"  (ASCII 0x"+
	    			myeditText_samplereceipt_SelectCodePage_Sample.getText().toString()+")\0\n");

    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
    	} else {
	    	PrinterFunctions.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"Code Page Sample\n" +
	    			myspinner_SelectCodePage.getSelectedItem().toString()+
	    			" : " +
	    			String.valueOf(str)	+
	    			"  (ASCII 0x"+
	    			myeditText_samplereceipt_SelectCodePage_Sample.getText().toString()+")\0");
	    	
	    	PrinterFunctions.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"\n");
	    	
	    	PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
    	}
    }
    
    public void samplereceipt_SelectInternationalCharacter(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.SelectInternationalCharacter(
        			Main.portName,
        			Main.portSettings,
        			myspinner_SelectInternationalCharacter.getSelectedItem().toString());
    	} else {
	    	PrinterFunctions.SelectInternationalCharacter(
	    			Main.portName,
	    			Main.portSettings,
	    			myspinner_SelectInternationalCharacter.getSelectedItem().toString());
    	}
    }
    
    public void samplereceipt_SelectInternationalCharacter_Sample(View view)
    {
    	int ASCII=HexStr2Int(myeditText_samplereceipt_SelectInternationalCharacter_Sample.getText().toString());
    	char str=(char)ASCII;
    	
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"International Character Sample\n" +
	    			myspinner_SelectInternationalCharacter.getSelectedItem().toString()+
	    			" : " +
	    			String.valueOf(str)	+
	    			"  (ASCII 0x"+
	    			myeditText_samplereceipt_SelectInternationalCharacter_Sample.getText().toString()+")\0");
	    	
    		PrinterFunctionsLAN.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"\n");
	    	
    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
    	} else {
	    	PrinterFunctions.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"International Character Sample\n" +
	    			myspinner_SelectInternationalCharacter.getSelectedItem().toString()+
	    			" : " +
	    			String.valueOf(str)	+
	    			"  (ASCII 0x"+
	    			myeditText_samplereceipt_SelectInternationalCharacter_Sample.getText().toString()+")\0");
	    	
	    	PrinterFunctions.PrintText(
	    			Main.portName, 
	    			Main.portSettings, 
	    			0, 0, 0, 0, 0, 0, 0, 0,
	    			"\n");
	    	
	    	PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
    	}
    }
    
    public static int HexStr2Int(String str) {
    	char str_chr[]=str.toCharArray();
    	int str_int=0;
    	
    	for(int i=0;i<str.length();i++) {
    		str_int=str_int*16;
        	if(((int)str_chr[i]>=48) && ((int)str_chr[i]<=57)) { //0~9
        		str_int=str_int+(int)str_chr[i]-48;
        	} else if((str_chr[i]>=65) && (str_chr[i]<=70)) { //A~F
        		str_int=str_int+((int)str_chr[i]-65)+10;
        	} else {
        		return -1;
        	}
    	}
    	
		return str_int;
	}
}
