package com.cc.grameenphone.api_models;

/**
 * Created by rajkiran on 18/09/15.
 */
public class AddAssociationComapanyModel {
    private String SURCREQ;

    private String COMPNAME;

    private String COMPCODE;

    public String getSURCREQ() {
        return SURCREQ;
    }

    public void setSURCREQ(String SURCREQ) {
        this.SURCREQ = SURCREQ;
    }

    public String getCOMPNAME() {
        return COMPNAME;
    }

    public void setCOMPNAME(String COMPNAME) {
        this.COMPNAME = COMPNAME;
    }

    public String getCOMPCODE() {
        return COMPCODE;
    }

    public void setCOMPCODE(String COMPCODE) {
        this.COMPCODE = COMPCODE;
    }

    @Override
    public String toString() {
        return "ClassPojo [SURCREQ = " + SURCREQ + ", COMPNAME = " + COMPNAME + ", COMPCODE = " + COMPCODE + "]";
    }
}
