package dani.com.smacktest.core.smack;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager;

import java.io.IOException;

import dani.com.smacktest.core.events.AuthenticationEvent;
import dani.com.smacktest.core.events.ConnectionEvent;
import dani.com.smacktest.core.events.ErrorEvent;

/**
 * Created by flamingo on 16/3/16.
 */
public class TestXMPP implements ConnectionListener {

    public static final String TAG = "TestXmpp";

    private final String serverAddress;
    private final String loginUser;
    private final String passwordUser;
    public static XMPPTCPConnection connection;
    private Gson gson;

    public static TestXMPP getInstance(String serverAddress, String loginUser, String passwordUser) {
        return new TestXMPP(serverAddress, loginUser, passwordUser);
    }

    public TestXMPP(String serverAddress, String loginUser, String passwordUser) {
        this.serverAddress = serverAddress;
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
        EventBus.getDefault().register(this);
        init();
    }

    private void init() {
        gson = new Gson();
        initialiseConnection();
    }

    private void initialiseConnection() {
        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration
                .builder();
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        config.setServiceName(serverAddress);
        config.setHost(serverAddress);
        config.setPort(5222);
        config.setDebuggerEnabled(true);
        XMPPTCPConnection.setUseStreamManagementResumptiodDefault(true);
        XMPPTCPConnection.setUseStreamManagementDefault(true);
        connection = new XMPPTCPConnection(config.build());
        connection.addConnectionListener(this);
    }

    public void disconnect() {
        connection.disconnect();
    }

    public void connect() {
        try {
            connection.connect();
            DeliveryReceiptManager dm = DeliveryReceiptManager
                    .getInstanceFor(connection);
            dm.setAutoReceiptMode(DeliveryReceiptManager.AutoReceiptMode.always);

        } catch (SmackException e) {
            EventBus.getDefault().post(new ErrorEvent(TAG, e.getMessage()));
        } catch (IOException e) {
            EventBus.getDefault().post(new ErrorEvent(TAG, e.getMessage()));
        } catch (XMPPException e) {
            EventBus.getDefault().post(new ErrorEvent(TAG, e.getMessage()));
        }
    }

    public void login() {
        try {
            connection.login(loginUser, passwordUser);
        } catch (XMPPException | SmackException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

    @Override
    public void connected(XMPPConnection connection) {
        if (!connection.isAuthenticated()) {
            login();
        }
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        if(connection.isAuthenticated())
            EventBus.getDefault().post(new AuthenticationEvent(TAG, resumed));
        else
            EventBus.getDefault().post(new ErrorEvent(TAG, "Login: FAIL"));
    }

    @Override
    public void connectionClosed() {
        EventBus.getDefault().post(new ErrorEvent(TAG, "Connection: CLOSED"));
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        EventBus.getDefault().post(new ErrorEvent(TAG, e.getMessage()));
    }

    @Override
    public void reconnectionSuccessful() {
        EventBus.getDefault().post(new ConnectionEvent(TAG));
    }

    @Override
    public void reconnectingIn(int seconds) {

    }

    @Override
    public void reconnectionFailed(Exception e) {
        EventBus.getDefault().post(new ErrorEvent(TAG, "Reconnection: FAILED"));
    }
}
