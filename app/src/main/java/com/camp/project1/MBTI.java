package com.camp.project1;

public class MBTI {
    public int[] EI;
    public int[] SN;
    public int[] TF;
    public int[] PJ;
    public int mbti_page;
    String mbtitype;
    public Boolean backward;

    public MBTI(){
        EI = new int[] {0,0};
        SN = new int[] {0,0};
        TF = new int[] {0,0};
        PJ = new int[] {0,0};
        mbti_page = 0;
        mbtitype = null;
        backward = false;
    }

    public void managing_data(String type, String check){
        if(check == "Do"){
            switch (type){
                case "E":
                    this.EI[0]++;
                    break;
                case "I":
                    this.EI[1]++;
                    break;
                case "S":
                    this.SN[0]++;
                    break;
                case "N":
                    this.SN[1]++;
                    break;
                case "T":
                    this.TF[0]++;
                    break;
                case "F":
                    this.TF[1]++;
                    break;
                case "P":
                    this.PJ[0]++;
                    break;
                case "J":
                    this.PJ[1]++;
                    break;
            }
            mbti_page++;
        }else{
            switch (type){
                case "E":
                    this.EI[0]--;
                    break;
                case "I":
                    this.EI[1]--;
                    break;
                case "S":
                    this.SN[0]--;
                    break;
                case "N":
                    this.SN[1]--;
                    break;
                case "T":
                    this.TF[0]--;
                    break;
                case "F":
                    this.TF[1]--;
                    break;
                case "P":
                    this.PJ[0]--;
                    break;
                case "J":
                    this.PJ[1]--;
                    break;
            }
            mbti_page--;
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
