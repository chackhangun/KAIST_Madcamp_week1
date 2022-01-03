package com.camp.project1;

public class MBTI {
    public int[] EI;
    public int[] SN;
    public int[] TF;
    public int[] PJ;
    String mbtitype;

    public MBTI(){
        EI = new int[] {0,0};
        SN = new int[] {0,0};
        TF = new int[] {0,0};
        PJ = new int[] {0,0};
        mbtitype = null;
    }

    public void incrementEI(String type, String check){
        if(check == "Do"){
            if (type == "E"){
                this.EI[0]++;
            }else{
                this.EI[1]++;
            }
        }else{
            if (type == "E"){
                this.EI[0]--;
            }else{
                this.EI[1]--;
            }
        }

    }

    public void incrementSN(String type, String check){
        if(check == "Do") {
            if (type == "S") {
                this.SN[0]++;
            } else {
                this.SN[1]++;
            }
        }else{
            if (type == "S") {
                this.SN[0]--;
            } else {
                this.SN[1]--;
            }
        }
    }

    public void incrementTF(String type, String check){
        if(check == "Do") {
            if (type == "T") {
                this.TF[0]++;
            } else {
                this.TF[1]++;
            }
        }else{
            if (type == "T") {
                this.TF[0]--;
            } else {
                this.TF[1]--;
            }
        }
    }

    public void incrementPJ(String type, String check){
        if(check == "Do") {
            if (type == "P") {
                this.PJ[0]++;
            } else {
                this.PJ[1]++;
            }
        }else{
            if (type == "P") {
                this.PJ[0]--;
            } else {
                this.PJ[1]--;
            }
        }
    }

    public String getMBTItype(){
        if(this.EI[0] > this.EI[1]){
            this.mbtitype = "E";
        }
        else{
            this.mbtitype = "I";
        }

        if(this.SN[0] > this.SN[1]){
            this.mbtitype += "S";
        }
        else{
            this.mbtitype += "N";
        }

        if(this.TF[0] > this.TF[1]){
            this.mbtitype += "T";
        }
        else{
            this.mbtitype += "F";
        }

        if(this.PJ[0] > this.PJ[1]){
            this.mbtitype += "P";
        }
        else{
            this.mbtitype += "J";
        }

        return this.mbtitype;
    }

    public void print(){
        System.out.println("E: " + EI[0] + " I: " + EI[1] + " S: "+ SN[0] + " N: " + SN[1] + " T: " + TF[0] + " F: " + TF[1] + " P: " + PJ[0] + " J: " + PJ[1]+"\n");
    }
}
