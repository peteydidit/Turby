package competeydidit.facebook.www.turby69;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

class EndCallListener extends PhoneStateListener {
    private static final String LOG_TAG = "";

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        if(TelephonyManager.CALL_STATE_RINGING == state) {
            Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
        }
        if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
            //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
            Log.i(LOG_TAG, "OFFHOOK");
        }
        if(TelephonyManager.CALL_STATE_IDLE == state) {
            //when this state occurs, and your flag is set, restart your app
            Log.i(LOG_TAG, "IDLE");
        }
    }
}
