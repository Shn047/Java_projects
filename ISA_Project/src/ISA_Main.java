//Swan Htet Naing - 6511924
import java.util.ArrayList;
import java.util.Scanner;
public class ISA_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String instruction = "";
        String opcode ="";
        String operand ="";
        String operand2="";
        Registers[] regs = new Registers[8];
        for(int i =0;i<=7;i++){
            regs[i] = new Registers(0,"r"+i);
        }
        ArrayList<InstructionSet> steps = new ArrayList<>();
        System.out.println("Input instruction ");
        System.out.println("- For Opcode : mov, add, mul, sub and div");
        System.out.println("- For Operand 1 : R0 - R7");
        System.out.println("- For Operand 2 : R0 - R7 or a value");
        System.out.println("- type 'end 0 0' to stop the input request" );
        System.out.println("Enter inputs : ");
        int step = 0;
        while(!(opcode.equals("end")) && !(operand.equals("0")) && !(operand2.equals("0"))){
            instruction = scanner.nextLine();
            if(instruction.equals("end 0 0")){
                break;
            }
            else{
                String[] input = instruction.split(" ");
                opcode = input[0];
                operand = input[1];
                operand2 = input[2];
                Registers destiReg = null;
                for(int s = 0;s<= regs.length;s++){
                    if((regs[s].getRegAdr().equals(operand))){
                        destiReg = regs[s];
                        break;
                    }
                }
                try{
                    int value = Integer.parseInt(operand2); //Change to int
                    switch(opcode){
                        case "mov":
                            destiReg.setRegVal(value);
                            steps.add(new InstructionSet(step,opcode,destiReg,1));
                            break;
                        case "add":
                            destiReg.setRegVal(destiReg.getRegVal()+value);
                            steps.add(new InstructionSet(step,opcode,destiReg,2));
                            break;
                        case "sub":
                            destiReg.setRegVal(destiReg.getRegVal()-value);
                            steps.add(new InstructionSet(step,opcode,destiReg,3));
                            break;
                        case "mul":
                            destiReg.setRegVal(destiReg.getRegVal()*value);
                            steps.add(new InstructionSet(step,opcode,destiReg,4));
                            break;
                        case "div":
                            destiReg.setRegVal(destiReg.getRegVal()/value);
                            steps.add(new InstructionSet(step,opcode,destiReg,4));
                            //break;
                    }
                }catch(NumberFormatException e){
                    Registers sourceReg = null;
                    for(int a = 0;a<= regs.length;a++){
                        if(regs[a].getRegAdr().equals(operand2)){
                            sourceReg = regs[a];
                            break;
                        }
                    }
                    switch(opcode){
                        case "mov":
                            destiReg.setRegVal(sourceReg.getRegVal());
                            steps.add(new InstructionSet(step,opcode,destiReg,1));
                            break;
                        case "add":
                            destiReg.setRegVal(destiReg.getRegVal()+sourceReg.getRegVal());
                            steps.add(new InstructionSet(step,opcode,destiReg,2));
                            break;
                        case "sub":
                            destiReg.setRegVal(destiReg.getRegVal()-sourceReg.getRegVal());
                            steps.add(new InstructionSet(step,opcode,destiReg,3));
                            break;
                        case "mul":
                            destiReg.setRegVal(destiReg.getRegVal()*sourceReg.getRegVal());
                            steps.add(new InstructionSet(step,opcode,destiReg,4));
                            break;
                        case "div":
                            destiReg.setRegVal(destiReg.getRegVal()/sourceReg.getRegVal());
                            steps.add(new InstructionSet(step,opcode,destiReg,4));
                            //break;
                    }
                }
            }
            step++;
        }
        scanner.close();
        System.out.format("%-12s%-19s%-28s%s\n","Step ","Decoded Form ","Encoded Form","Clock Cycles");
        for(InstructionSet s : steps){
            System.out.println(s.toString());
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Steps of the Register");
        for(InstructionSet s:steps){
            System.out.format("%s = %d   [%s]\n",s.getRegister().getRegAdr(),s.getValue(),s.to16bitval());
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Values of registers after the execution of the instruction set");
        for(int i =0; i<regs.length;i++){
            System.out.format("%s   %d  [%s]\n",regs[i].getRegAdr(),regs[i].getRegVal(),regs[i].to16bitval());
        }
        System.out.println("----------------------------------------------------------------------------------------");
        int totalClkCycs = 0;
        for(InstructionSet s : steps){
            totalClkCycs += s.getClkcyc();
        }
        double cpi = totalClkCycs/step;
        System.out.println("CPI of the program is "+cpi);
        System.out.println("----------------------------------------------------------------------------------------");

        int clkcyswithpipeline = steps.size()+3;
        System.out.println("The pipelined version showing execution of the program");
        System.out.println("*******************************************************");
        System.out.printf("%17s","");
        for(int x = 1;x<=clkcyswithpipeline;x++){
            System.out.printf("%5d",x);
        }
        for(InstructionSet s : steps){
            if(s.getOperand().equals("")){
                System.out.printf("%n%d. %-5s%-4s%-2d :",s.getStep()+1,s.getOpcode(),s.getRegister().getRegAdr(),s.getValue());
            }
            else{
                System.out.printf("%n%d. %-5s%-4s%-2s :",s.getStep()+1,s.getOpcode(),s.getRegister().getRegAdr(),s.getOperand());
            }
            for(int y = 0;y<s.getStep()+1;y++){
                System.out.printf("%5s","");
            }
            System.out.printf("IF | ID | EX | WB");//Instruction Fetch,Instruction Decoding,Excecution,WriteBack
        }
        System.out.println("\n\nPipelined execution took "+clkcyswithpipeline+" clock cyles to complete the program execution");
    }
}
