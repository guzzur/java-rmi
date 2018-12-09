package io.github.guzzur.client;

import io.github.guzzur.rmiinterface.RMIInterface;
import io.github.guzzur.sharedclasses.Message;
import io.github.guzzur.sharedclasses.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Connector {
    private static RMIInterface lookUp;
    private String userName;

    public Connector(String userName) {
        try {
            lookUp = (RMIInterface) Naming.lookup("//localhost/RMIServer");
            this.userName = userName;
        } catch (MalformedURLException murle) {

        } catch (RemoteException re) {

        } catch (NotBoundException nbe) {

        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int sendMsg(String content) {
        try {
            return lookUp.writeMsg(new Message(content, new User(this.userName)));
        } catch (RemoteException re) {
            return -1;
        }
    }

    public ArrayList<Message> getMsgs(int index) {
        try {
            return lookUp.readMsg(index);
        } catch (RemoteException e) {
            return new ArrayList<Message>();
        }
    }
}