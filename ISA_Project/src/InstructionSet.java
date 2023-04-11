//Swan Htet Naing - 6511924
public class InstructionSet {
    private int step;
    private String opcode;
    private Registers register;
    private int clkcyc;
    private String operand;
    private int value;
    public InstructionSet(int step, String opcode, Registers register, int clkcyc) {
        this.step = step;
        this.opcode = opcode;
        this.register = register;
        this.clkcyc = clkcyc;
        this.value = register.getRegVal();
        this.operand = "";
    }
    public InstructionSet(int step, String opcode, Registers register, int clkcyc, String operand) {
        this.step = step;
        this.opcode = opcode;
        this.register = register;
        this.clkcyc = clkcyc;
        this.value = register.getRegVal();
        this.operand = operand;
    }
    public int getStep() {return step;}
    public void setStep(int step) {this.step = step;}
    public String getOpcode() {return opcode;}
    public void setOpcode(String opcode) {this.opcode = opcode;}
    public Registers getRegister() {return register;}
    public void setRegister(Registers register) {this.register = register;}
    public int getClkcyc() {return clkcyc;}
    public void setClkcyc(int clkcyc) {this.clkcyc = clkcyc;}
    public String getOperand() {return operand;}
    public void setOperand(String operand) {this.operand = operand;}
    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}

    public String fiveBitOpcode(){
        switch(this.opcode){
            case "mov":
                return "00001";
            case "add":
                return "00010";
            case "mul":
                return "00100";
            case "sub":
                return "00011";
            case "div":
                return "00101";
        }
        return "end 0 0";
    }

    public String to16bitval() {
        if (value >= 0) {
            return String.format("%016d", Integer.valueOf(Integer.toBinaryString(value)));
        } else {
            String val16bit = Integer.toBinaryString(value);
            return val16bit.substring(val16bit.length() - 16, val16bit.length());
        }
    }

    @Override
    public String toString(){
        if(this.operand.equals("")){
            return String.format("%s%d%-10s%-5s%-4s,  %-5d%-7s%-5s%-22s%d","[",this.step,"]",this.opcode,this.register.getRegAdr(),
                    this.value,fiveBitOpcode(),fiveBitOpcode(),this.register.to3bitAdr(),this.clkcyc);
        }
        else{
            return String.format("%s%d%-10s%-5s%-4s,  %-5s%-7s%-5s%-22s%d","[",this.step,"]",this.opcode,this.register.getRegAdr(),
                    this.operand,fiveBitOpcode(),this.register.to3bitAdr(),to16bitval(),this.clkcyc);
        }
    }
}