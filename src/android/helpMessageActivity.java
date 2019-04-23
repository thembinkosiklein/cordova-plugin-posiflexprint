package com.pos.printer;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class helpMessageActivity extends Activity 
{
	private static String m_message;
	
	private static boolean isStringMessage = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpmessage);
		
		WebView web = new WebView(this);
		LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout_main);
		layout.addView(web);
		
		if (true == isStringMessage) {
		    web.loadData(helpMessageActivity.HTMLCSS() + m_message, "text/html", "utf-16");
		}
		else {
			web.loadUrl(m_message);	
		}
	}
	
	public static void SetMessage(String message)
	{
		m_message = message;
		isStringMessage = true;
	}
	
	public static void SetHTML(String url)
	{
		m_message = url;
		isStringMessage = false;
	}
	
    public static String HTMLCSS()
    {
    	String cssDefinition = "<html>" +
                			   "<head>" +
                			   "<style type=\"text/css\">" +
                			   "ColorBlue {color:blue}\n" +
                			   "ColorGreen {color:green}\n" +
                			   "ColorRed {color:red}\n" +
                			   "SubTitle{font-size:17;font-weight:bold}\n" + //小標題
                			   "MainTitle{font-size:20px;font-weight:bold}\n" + //大標題
                			   "StandardItalic {font-style:italic}" + //斜體字
                			   "</style>" +
                			   "</head>";
    	return cssDefinition;
    }
}
