//Swan Htet Naing - 6511924
public class Registers {
    private String regAdr;
    private int regVal;
    public Registers(int regVal,String regAdr){
        this.regVal =regVal;
        this.regAdr = regAdr;
    }
    public void setRegAdr(String regAdr){this.regAdr = regAdr;}
    public String getRegAdr(){return regAdr;}

    public void setRegVal(int regVal){this.regVal = regVal;}
    public int getRegVal(){return regVal;}

    public String to16bitval(){
        if(regVal>=0){
            return String.format("%016d",Integer.valueOf(Integer.toBinaryString(regVal)));
        }
        else{
            String val16bit = Integer.toBinaryString(regVal);
            return val16bit.substring(val16bit.length()-16,val16bit.length());
        }
    }
    public String to3bitAdr(){
        switch (this.regAdr){
            case "r0":
                return "000";
            case "r1":
                return "001";
            case "r2":
                return "010";
            case "r3":
                return "011";
            case "r4":
                return "100";
            case "r5":
                return "101";
            case "r6":
                return "110";
            case "r7":
                return "111";
        }
        return "";
    }
}

