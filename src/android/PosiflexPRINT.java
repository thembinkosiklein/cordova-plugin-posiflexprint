package cordova.plugin.posiflexprint;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pos.printer.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * This class echoes a string called from JavaScript.
 */
public class PosiflexPRINT extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("print")) {
            PrinterFunctions.PrintSampleReceipt("USB:", 0);
            return true;
        }
        return false;
    }
}
