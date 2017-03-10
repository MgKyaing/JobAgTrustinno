package com.trustinno.win.jobagtrustinno.Server;

import android.hardware.camera2.params.MeteringRectangle;

/**
 * Created by zarni on 3/9/17.
 */

public class ServerEventSpinnercity {
    private ServerResponse serverResponse;
    private  ServerResponseRegister  serverResponseRegister;

    public ServerEventSpinnercity(ServerResponse serverResponse) {
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
