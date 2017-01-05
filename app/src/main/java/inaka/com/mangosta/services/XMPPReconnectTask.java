package inaka.com.mangosta.services;

import android.content.Context;

import java.util.TimerTask;

import inaka.com.mangosta.utils.MangostaApplication;
import inaka.com.mangosta.utils.Preferences;
import inaka.com.mangosta.xmpp.XMPPSession;

public class XMPPReconnectTask extends TimerTask {

    private Context mContext;
    private static final Object LOCK_1 = new Object() {
    };

    public XMPPReconnectTask(Context context) {
        this.mContext = context;
    }

    @Override
    public void run() {
        synchronized (LOCK_1) {
            // if app is closed
            if (MangostaApplication.getInstance().getCurrentActivity() == null) {

                Preferences preferences = Preferences.getInstance();
                if (preferences.isLoggedIn()) {
                    XMPPSession.getInstance().backgroundRelogin();
                } else {
                    XMPPSession.getInstance().getXMPPConnection().disconnect();
                }

            }
        }
    }

}
