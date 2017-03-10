package com.trustinno.win.jobagtrustinno.Server;

/**
 * Created by zarni on 3/10/17.
 */

public class ServerEventSpinnerTownship {

    private ServerResponse serverResponse;
    private  ServerResponseRegister  serverResponseRegister;

    public ServerEventSpinnerTownship(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public ServerResponseRegister getServerResponseRegister(){
        return serverResponseRegister;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

}
