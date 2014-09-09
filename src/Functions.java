import java.io.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;
import javax.swing.JFileChooser;

public class Functions extends Thread{   
 
    public static boolean stopFlag;
    public static boolean debug;
    
    public void Functions(){
        stopFlag = false;
        debug = false;
    }
    
    public static void showMem(){
         SimulatorUI.MemoryPanel.setText("");
         for(int n = 0; n<Data.mem.length; n++){    //display in MemoryPanel
              SimulatorUI.MemoryPanel.append(Functions.toBinaryString(Data.mem[n])+"\n");
          }
    }
            
    public static String byteToString(byte b){
        String str = "";
        str += (char) b;
        return str;
    }
    
    public static String toBinaryString(int val){
        String padding = "0000000000000000";
        String result = padding + Long.toBinaryString(val);
        return result.substring(result.length() - 16, result.length());  // take the right-most 16 digits
    }
    
    public static String toBinaryString32(int val){
         String padding = "00000000000000000000000000000000";
        String result = padding + Long.toBinaryString(val);
        return result.substring(result.length() - 32, result.length());  // take the right-most 16 digits
    }
    
     public static short stringToBinary(String str){ //Binary string to short, ex. "0010" to 0b0010
         char[] chars = str.toCharArray();
         short tmp = 0;
              for(int j = 0; j<Short.SIZE; j++){                 
                  tmp += (short) (chars[j] - '0');
                  if(j<Short.SIZE-1)
                    tmp = (short) (tmp<<1);
              } 
              return tmp;
     }
     
     public static void setCC(){  //set Condition Code, from left to right they are overflow, underflow, divzero, equalornot
         if(Data.opcode==4||Data.opcode==6){ //addition
             
         }
     }
    
     public static short cache(short addr){
         int i;
         for(i = 0; i<Data.cache.length; i++){
             if(Data.cache[i][0] == addr){
                 SimulatorUI.ConsolePanel.append("Found in Cache "+ i+"\n");
                 return (short)i;
             }
          }
         if(i==Data.cache.length){
             SimulatorUI.ConsolePanel.append("Not Found in Cache\n");
             Data.cache[Data.C%Data.cache.length][0]=addr;
             Data.cache[Data.C%Data.cache.length][1]=Data.mem[addr];
             SimulatorUI.ConsolePanel.append("Cache "+Data.C%Data.cache.length+" has been updated.\n");
             Data.C++;
             return -1;
         }
         return -1;
     }
     
    public static boolean EA(){
        if(Data.I==0 && Data.PC<Data.mem.length){
            if(Data.IX==0){                
                SimulatorUI.ConsolePanel.append("EA << "+toBinaryString(Data.addr)+"\n");
                Data.EA = Data.addr;
                if(Data.EA>=2048){
                    SimulatorUI.ConsolePanel.append("Fault 0: Illegal Memory Address!\n");
                    SimulatorUI.MFR_result.setText("0");
                    Data.mem[1] = 0;
                    showMem();
                    return false;
                }
                return true;
            }
            else{                    
                SimulatorUI.ConsolePanel.append("EA << "+toBinaryString(Data.addr)+"+"+toBinaryString(Data.X0)+"\n");
                Data.EA = (short) (Data.X0+Data.addr);
                if(Data.EA>=2048){
                    SimulatorUI.ConsolePanel.append("Fault 0: Illegal Memory Address!\n");
                    SimulatorUI.MFR_result.setText("0");
                    Data.mem[1] = 0;
                    showMem();
                    return false;
                }
                return true;
            }
        }
        else if(Data.I==1 && Data.PC<Data.mem.length){
            if(Data.IX==0){
                Data.MAR = Data.addr;
                SimulatorUI.MAR_result.setText(Short.toString(Data.mem[Data.addr])); //MAR<<M(addr)
                SimulatorUI.ConsolePanel.append("MAR << "+Short.toString(Data.mem[Data.addr])+"\n");
                SimulatorUI.ConsolePanel.append("EA << "+Short.toString(Data.mem[Data.MAR])+"\n");                
                Data.EA = Data.mem[Data.MAR];
                if(Data.EA>=2048){
                    SimulatorUI.ConsolePanel.append("Fault 0: Illegal Memory Address!\n");
                    SimulatorUI.MFR_result.setText("0");
                    Data.mem[1] = 0;
                    showMem();
                    return false;
                }
                return true;
            }  
            else {
                Data.MAR = (short) (Data.X0+Data.addr);
                SimulatorUI.MAR_result.setText(toBinaryString(Data.X0+Data.addr)); //MAR<<M(addr)+c(X0)
                SimulatorUI.ConsolePanel.append("MAR << Memory of addr: "+toBinaryString(Data.X0)+"+"+toBinaryString(Data.addr)+"\n");
                SimulatorUI.ConsolePanel.append("EA << Memory of MAR: "+toBinaryString(Data.mem[Data.MAR])+"\n");
                Data.EA = Data.mem[Data.MAR];    
                if(Data.EA>=2048){
                    SimulatorUI.ConsolePanel.append("Fault 0: Illegal Memory Address!\n");
                    SimulatorUI.MFR_result.setText("0");
                    Data.mem[1] = 0;
                    showMem();
                    return false;
                }
                return true;
            }
        }
        else return false;
    }
    
    public static void decompose(){
        if(Data.PC<Data.mem.length){ 
            Data.MAR = (short) (Data.PC);         //MAR << PC   , begin at Address 2
            SimulatorUI.MAR_result.setText(toBinaryString(Data.MAR));
            SimulatorUI.ConsolePanel.append("MAR << PC\n");
            int i =cache(Data.MAR);
            if(i != -1){
                SimulatorUI.ConsolePanel.append("Fetch Cache "+ i +" :"+toBinaryString(Data.cache[i][1])+"\n");   //Fetch Cache
                Data.MBR = Data.cache[i][1]; 
                SimulatorUI.MBR_result.setText(toBinaryString(Data.MBR));
            }
            else{
                SimulatorUI.ConsolePanel.append("Fetch Memory: "+toBinaryString(Data.MAR)+"\n");   //Fetch Memory                    
                Data.MBR = Data.mem[Data.MAR];
                SimulatorUI.MBR_result.setText(toBinaryString(Data.MBR));
            }
            SimulatorUI.MBR_result.setText(toBinaryString(Data.MBR));
            SimulatorUI.ConsolePanel.append("MBR << "+toBinaryString(Data.MBR)+"\n");
            Data.IR = Data.MBR;         //IR<<MBR 
            SimulatorUI.IR_result.setText(toBinaryString(Data.IR));
            SimulatorUI.ConsolePanel.append("IR << MBR\n");
            
            byte first = Data.first(Data.mem[Data.MAR]);
            byte second = Data.second(Data.mem[Data.MAR]);
        
            Data.opcode = (short) ((first>>2)&0b111111);
            SimulatorUI.ConsolePanel.append("opcode << "+Long.toBinaryString(Data.opcode)+"\n");
            
            if(Data.opcode>=1&&Data.opcode<=5||Data.opcode==33||Data.opcode==34||  // Load & Store
                Data.opcode>=8&&Data.opcode<=12||Data.opcode==14||Data.opcode == 27||Data.opcode == 28||Data.opcode == 29||Data.opcode==30){                 //transfer & AMR, SMR
                Data.I = (short) ((first>>1) & 1L);
                Data.IX = (short) (first & 1L);                               
                Data.addr = (short) (second & 0b111111); 
                
                SimulatorUI.ConsolePanel.append("I << "+Long.toBinaryString(Data.I)+"\n");
                SimulatorUI.ConsolePanel.append("IX << "+Long.toBinaryString(Data.IX)+"\n");
                SimulatorUI.ConsolePanel.append("Address << "+Long.toBinaryString(Data.addr)+"\n");
                             
                if(Data.opcode>=1&&Data.opcode<=3||Data.opcode == 27||Data.opcode == 28||Data.opcode == 29||Data.opcode==30){
                    Data.RSR = (short) ((second>>6)&0b11);                
                    SimulatorUI.RSR_result.setText(Short.toString(Data.RSR));
                    SimulatorUI.ConsolePanel.append("RSR << "+Short.toString(Data.RSR)+"\n");
                }
                else if(Data.opcode ==10){
                    Data.CC = (short) ((second>>6)&0b11);
                    SimulatorUI.CC_result.setText(Short.toString(Data.CC));
                    SimulatorUI.ConsolePanel.append("CC << "+Short.toString(Data.CC)+"\n");
                }                
                else if(Data.opcode==33||Data.opcode==34){
                    SimulatorUI.RSR_result.setText("X0");
                }    
                Data.in = EA();
                if(Data.in==true&&(Data.opcode>=1&&Data.opcode<=5||Data.opcode==33||  // instructions which need to fetch content of EA, do cache
                Data.opcode==34||Data.opcode>=8&&Data.opcode<=12||Data.opcode==14||Data.opcode == 27 || Data.opcode == 28||Data.opcode == 29||Data.opcode==30)){
                    int j =cache(Data.EA);
                    if(j != -1){
                        if(Data.opcode==2){
                           if(Data.cache[j][1]==Data.R[Data.RSR]) 
                               SimulatorUI.ConsolePanel.append("Content in cache and memory is same.\n"); 
                           else{
                               Data.cache[j][1]=Data.R[Data.RSR];
                               SimulatorUI.ConsolePanel.append("Cache "+ j +" is updated and write through.\n");
                           }
                        }
                        else if(Data.opcode==34){
                            if(Data.cache[j][1]==Data.X0) 
                               SimulatorUI.ConsolePanel.append("Content in cache and memory is same.\n"); 
                           else{
                               Data.cache[j][1]=Data.X0;
                               SimulatorUI.ConsolePanel.append("Cache "+ j +" is updated and write through.\n");
                            }
                        }
                        else{
                        SimulatorUI.ConsolePanel.append("Fetch Cache "+ j +"for c(EA) :"+toBinaryString(Data.cache[j][1])+"\n");   //Fetch Cache
                        Data.MBR = Data.cache[j][1];  
                        SimulatorUI.MBR_result.setText(toBinaryString(Data.MBR));
                        }
                    }
                    else{
                        SimulatorUI.ConsolePanel.append("Fetch Memory: "+toBinaryString(Data.EA)+"\n");   //Fetch Memory                    
                        Data.MBR = Data.mem[Data.EA];
                        SimulatorUI.MBR_result.setText(toBinaryString(Data.MBR));
                    }
                }
            } 
            else if(Data.opcode==0){   //HLT
                stopFlag=true;
                SimulatorUI.ConsolePanel.append("-------------------HALT--------------------\n");
            }
            else if(Data.opcode==24){   //TRAP
               Data.R[3] = (short) ((second>>6)&0b11);                
               SimulatorUI.RSR_result.setText(Short.toString(Data.R[3]));
               SimulatorUI.ConsolePanel.append("RSR << 3\n");
               if(Data.R[3]<0 || Data.R[3]>15){
                    SimulatorUI.ConsolePanel.append("Fault 1: Illegal TRAP Code!!\n");
                    SimulatorUI.MFR_result.setText("1");
                    Data.mem[1] = 1;
                    showMem();
                    stopFlag=true;
                    SimulatorUI.ConsolePanel.append("-------------------HALT--------------------\n");
                }
            }
            else if(Data.opcode ==13||Data.opcode ==6||Data.opcode ==7 ){   //immediate instructions
                //SimulatorUI.ConsolePanel.append("opcode << "+Long.toBinaryString(Data.opcode)+"\n");
                Data.immed = (short) (second & 0b111111);
                if(Data.opcode ==6||Data.opcode ==7){
                Data.RSR = (short) ((second>>6)&0b11);
                SimulatorUI.RSR_result.setText(Short.toString(Data.RSR));
                } 
            }
            else if(Data.opcode ==16||Data.opcode ==17||Data.opcode ==18||Data.opcode ==19||Data.opcode ==20){  //multiply & divide
                Data.RSR=(short)(first & 0b11);
                Data.R[0]=Data.R[Data.RSR];
                SimulatorUI.ConsolePanel.append("R0 << "+"R"+Long.toString(Data.RSR)+": "+toBinaryString(Data.R[0])+"\n");
                Data.RSR=(short)((second>>6)&0b11);
                Data.R[1]=Data.R[Data.RSR];
                SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
                SimulatorUI.R1_result.setText(toBinaryString(Data.R[1]));                
                SimulatorUI.ConsolePanel.append("R1 << "+"R"+Long.toString(Data.RSR)+": "+toBinaryString(Data.R[1])+"\n");
                }
            else if(Data.opcode ==21){   //NOT
                Data.RSR=(short)(first & 0b11);
                Data.R[0]=Data.R[Data.RSR];
                SimulatorUI.ConsolePanel.append("R0 << "+"R"+Long.toString(Data.RSR)+": "+toBinaryString(Data.R[0])+"\n");
                SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
            }
            else if(Data.opcode == 25 || Data.opcode == 26){     //SRC, RRC
                Data.LR = (short) ((first>>1) & 1L);
                Data.RSR = (short) ((first & 1L)*2 + ((second >>7)&1L));                               
                Data.AL = (short) ((second>>6) & 1L); 
                Data.count = (short) (second & 0b1111);
                SimulatorUI.RSR_result.setText(Long.toString(Data.RSR));
                SimulatorUI.ConsolePanel.append("L/R << "+Long.toBinaryString(Data.LR)+"\n");
                SimulatorUI.ConsolePanel.append("RSR << "+Long.toBinaryString(Data.RSR)+"\n");
                SimulatorUI.ConsolePanel.append("AL << "+Long.toBinaryString(Data.AL)+"\n");
                SimulatorUI.ConsolePanel.append("count << "+Long.toBinaryString(Data.count)+"\n");             
            }   
            else if (Data.opcode == 49 || Data.opcode == 50 || Data.opcode == 51){  //IN, OUT, CHK
                Data.RSR = (short) ((first & 1L)*2 + ((second >>7)&1L));                                
                Data.devid = (short) (second & 0b11111);
                SimulatorUI.RSR_result.setText(Long.toString(Data.RSR));
                SimulatorUI.ConsolePanel.append("Devid << "+Long.toBinaryString(Data.devid)+"\n");
                SimulatorUI.ConsolePanel.append("RSR << "+Long.toBinaryString(Data.RSR)+"\n");
            }
            /*else if(Data.opcode == 27) // Float ADD
            {
                Data.I = (short) ((first>>1) & 1L);
                Data.IX = (short) (first & 1L);                               
                Data.addr = (short) (second & 0b111111); 
                
                SimulatorUI.ConsolePanel.append("I << "+Long.toBinaryString(Data.I)+"\n");
                SimulatorUI.ConsolePanel.append("IX << "+Long.toBinaryString(Data.IX)+"\n");
                SimulatorUI.ConsolePanel.append("Address << "+Long.toBinaryString(Data.addr)+"\n");
                
                
                Data.RSR = (short) ((second>>6)&0b11);                
                SimulatorUI.RSR_result.setText(Short.toString(Data.RSR));
                SimulatorUI.ConsolePanel.append("RSR << "+Short.toString(Data.RSR)+"\n");
                
                Data.in = EA();
              // SimulatorUI.ConsolePanel.append("-------------------Float--------------------\n"); 
            }*/
            else {
                SimulatorUI.ConsolePanel.append("Fault 2: Illegal Operation Code!!\n");
                SimulatorUI.MFR_result.setText("2");
                Data.mem[1] = 2;
                showMem();
                stopFlag = true;
                SimulatorUI.ConsolePanel.append("-------------------HALT--------------------\n");
            }

        }
        else SimulatorUI.ConsolePanel.append("--------------------END--------------------\n");
       
    }
    public static void execute() throws Exception{
        decompose();
        //boolean in = EA();
         if(Data.opcode==1 && Data.PC<Data.mem.length && Data.in==true){   //LDR: Load Register From Memory
             
             Data.R[Data.RSR] = Data.mem[Data.EA];
                if(Data.RSR==0)
                    SimulatorUI.R0_result.setText(toBinaryString(Data.mem[Data.EA]));
                if(Data.RSR==1)
                    SimulatorUI.R1_result.setText(toBinaryString(Data.mem[Data.EA]));
                if(Data.RSR==2)
                    SimulatorUI.R2_result.setText(toBinaryString(Data.mem[Data.EA]));
                if(Data.RSR==3)
                    SimulatorUI.R3_result.setText(toBinaryString(Data.mem[Data.EA]));
                SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << "+"content of EA "+toBinaryString(Data.mem[Data.EA])+"\n"); 
                Data.PC++;
                SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                SimulatorUI.ConsolePanel.append("----------------------------------------------\n");              
         }
         if(Data.opcode==2 && Data.PC<Data.mem.length && Data.in==true){  //STR: Store Register To Memory
            if(Data.EA==0 || Data.EA ==1){
                SimulatorUI.ConsolePanel.append("Address 0 and 1 can not be written.\n");
                Data.PC++;
                SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
            }
            else{
             Data.mem[Data.EA] = Data.R[Data.RSR];
             showMem();
             SimulatorUI.MAR_result.setText(toBinaryString(Data.EA));
             SimulatorUI.ConsolePanel.append("R"+Data.RSR+" >> "+"EA \n"); 
             Data.PC++;
             SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
             SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
             SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
            }
         }
          if(Data.opcode==3 && Data.PC<Data.mem.length && Data.in==true){  //LDA: Load Register with Address
                           
                  Data.R[Data.RSR] = Data.EA;
                  SimulatorUI.MAR_result.setText(toBinaryString(Data.EA));
                  if(Data.RSR==0)
                    SimulatorUI.R0_result.setText(toBinaryString(Data.EA));
                  if(Data.RSR==1)
                    SimulatorUI.R1_result.setText(toBinaryString(Data.EA));
                  if(Data.RSR==2)
                    SimulatorUI.R2_result.setText(toBinaryString(Data.EA));
                  if(Data.RSR==3)
                    SimulatorUI.R3_result.setText(toBinaryString(Data.EA));
                  SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << "+"EA: "+toBinaryString(Data.EA)+"\n"); 
                  Data.PC++;
                  SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");                                   
          }
          if(Data.opcode==33 && Data.PC<Data.mem.length && Data.in==true){  //LDX: Load Index Register from Memory
              
              Data.X0 = Data.mem[Data.EA];
              SimulatorUI.MAR_result.setText(toBinaryString(Data.EA));
              SimulatorUI.ConsolePanel.append("X0 << "+"content of EA "+toBinaryString(Data.mem[Data.EA])+"\n"); 
              SimulatorUI.X0_result.setText(toBinaryString(Data.X0));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==34 && Data.PC<Data.mem.length && Data.in==true){  //STX: Store Index Register to Memory
              if(Data.EA==0 || Data.EA ==1){
                SimulatorUI.ConsolePanel.append("Address 0 and 1 can not be written.\n");
                Data.PC++;
                SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
            }
            else{            
              Data.mem[Data.EA] = Data.X0;
              showMem();
              SimulatorUI.MAR_result.setText(toBinaryString(Data.EA));
              SimulatorUI.ConsolePanel.append("EA: "+toBinaryString(Data.EA)+" << "+"X0: "+toBinaryString(Data.X0)+"\n"); 
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
          }
          if(Data.opcode==8 && Data.PC<Data.mem.length){    //JZ
              if(Data.R[Data.RSR]==0){
                  Data.PC = Data.EA;
                  SimulatorUI.ConsolePanel.append("R"+Long.toString(Data.RSR)+" is Zero\n");
                  SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.EA)+"\n"); 
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
              else{
                  SimulatorUI.ConsolePanel.append("R"+Long.toString(Data.RSR)+" is not Zero\n");
                  Data.PC++;
                  SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
          }
          if(Data.opcode==9 && Data.PC<Data.mem.length){    //JNE
              if(Data.R[Data.RSR]!=0){
                  Data.PC = Data.EA;
                  SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.EA)+"\n"); 
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
              else{
                  Data.PC++;
                  SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
          }
          if(Data.opcode==10 && Data.PC<Data.mem.length){    //JNE
              if(Data.CC==1){
                  Data.PC = Data.EA;
                  SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.EA)+"\n"); 
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
              else{
                  Data.PC++;
                  SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
          }
          if(Data.opcode==11 && Data.PC<Data.mem.length){     //JMP
              Data.PC = Data.EA;
              SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.EA)+"\n"); 
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==12 && Data.PC<Data.mem.length){    //JSR
              Data.PC = Data.EA;
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.EA)+"\n"); 
              Data.R[3] = (short) (Data.PC+1);
              SimulatorUI.ConsolePanel.append("R3"+" << "+"PC + 1"+"\n"); 
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==13 && Data.PC<Data.mem.length){   //RFS
              Data.PC = Data.R[3];
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              Data.R[0] = Data.immed;
              SimulatorUI.R0_result.setText(toBinaryString(Data.immed));
              SimulatorUI.ConsolePanel.append("R0 << "+Long.toBinaryString(Data.R[0])+"\n");
              SimulatorUI.ConsolePanel.append("PC << "+toBinaryString(Data.R[3])+"\n");
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==14 && Data.PC<Data.mem.length){     //SOB
              Data.R[Data.RSR] = (short) (Data.R[Data.RSR] - 1);
              SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << " +toBinaryString(Data.R[Data.RSR])+"- 1"+"\n");
              if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.R[Data.RSR]>0){
                  Data.PC = Data.EA;
                  SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.EA)+"\n"); 
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
              else{
                  Data.PC++;
                  SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
          }
          if(Data.opcode==4 && Data.PC<Data.mem.length && Data.in==true){     //AMR
              Data.D1=Data.R[Data.RSR];
              Data.D2=Data.mem[Data.EA];
              SimulatorUI.D1_result.setText(toBinaryString(Data.D1));
              SimulatorUI.ConsolePanel.append("D1 << "+ toBinaryString(Data.D1)+"\n");
              SimulatorUI.ConsolePanel.append("D2 << "+ toBinaryString(Data.D2)+"\n");
              SimulatorUI.D2_result.setText(toBinaryString(Data.D2));
              Data.R[Data.RSR]=(short) (Data.D1+Data.D2);
              SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << D1 + D2\n");
              if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==5 && Data.PC<Data.mem.length && Data.in==true){     //SMR
              Data.D1=Data.R[Data.RSR];
              Data.D2=Data.mem[Data.EA];
              SimulatorUI.D1_result.setText(toBinaryString(Data.D1));
              SimulatorUI.ConsolePanel.append("D1 << "+ toBinaryString(Data.D1)+"\n");
              SimulatorUI.ConsolePanel.append("D2 << "+ toBinaryString(Data.D2)+"\n");
              SimulatorUI.D2_result.setText(toBinaryString(Data.D2));
              Data.R[Data.RSR]=(short) (Data.D1-Data.D2);
              SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << D1 - D2\n");
              if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==6 && Data.PC<Data.mem.length){     //AIR
              Data.D1=Data.R[Data.RSR];
              Data.D2=Data.immed;
              SimulatorUI.D1_result.setText(toBinaryString(Data.D1));
              SimulatorUI.ConsolePanel.append("D1 << "+ toBinaryString(Data.D1)+"\n");
              SimulatorUI.ConsolePanel.append("D2 << "+ toBinaryString(Data.D2)+"\n");
              SimulatorUI.D2_result.setText(toBinaryString(Data.D2));
              Data.R[Data.RSR]=(short) (Data.D1+Data.D2);
              SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << D1 + D2\n");
              if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==7 && Data.PC<Data.mem.length){     //SIR
              Data.D1=Data.R[Data.RSR];
              Data.D2=Data.immed;
              SimulatorUI.D1_result.setText(toBinaryString(Data.D1));
              SimulatorUI.ConsolePanel.append("D1 << "+ toBinaryString(Data.D1)+"\n");
              SimulatorUI.ConsolePanel.append("D2 << "+ toBinaryString(Data.D2)+"\n");
              SimulatorUI.D2_result.setText(toBinaryString(Data.D2));
              Data.R[Data.RSR]=(short) (Data.D1-Data.D2);
              SimulatorUI.ConsolePanel.append("R"+Data.RSR+" << D1 - D2\n");
              if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==16 && Data.PC<Data.mem.length){     //MUL             
              int tmp = Data.R[0] * Data.R[1];
              SimulatorUI.ConsolePanel.append("R0, R1 << Rx * Ry\n");
              Data.R[0]=(short)((tmp>>16) & 0xffff);
              Data.R[1]=(short) (tmp & 0xffff);
              SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
              SimulatorUI.R1_result.setText(toBinaryString(Data.R[1]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==17 && Data.PC<Data.mem.length){     //DIV    
              if(Data.R[1]!=0){
              short quo = (short)(Data.R[0] / Data.R[1]);
              short rem = (short)(Data.R[0]-quo * Data.R[1]);
              SimulatorUI.ConsolePanel.append("R0, R1 << Rx / Ry\n");
              Data.R[0]=quo;
              Data.R[1]=rem;
              SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
              SimulatorUI.R1_result.setText(toBinaryString(Data.R[1]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
              else{
                  SimulatorUI.ConsolePanel.append("Divisor is Zero, c(3) sets to 1\n");
                  SimulatorUI.CC_result.setText("0010");
                  Data.PC++;
                  SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                  SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                  SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }                  
          }
          if(Data.opcode==18 && Data.PC<Data.mem.length && Data.in==true){     //TER             
              if(Data.R[0]==Data.R[1]){
                  SimulatorUI.CC_result.setText("0001");
                  SimulatorUI.ConsolePanel.append("CC sets to 0001\n");
              }
              else{
                  SimulatorUI.CC_result.setText("0000");
                  SimulatorUI.ConsolePanel.append("CC sets to 0000\n");
              }
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==19 && Data.PC<Data.mem.length && Data.in==true){     //AND             
              Data.R[0]=(short) (Data.R[0] & Data.R[1]);
              SimulatorUI.ConsolePanel.append("R0 << Rx AND Ry\n");
              SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==20 && Data.PC<Data.mem.length && Data.in==true){     //ORR             
              Data.R[0]=(short) (Data.R[0] | Data.R[1]);
              SimulatorUI.ConsolePanel.append("R0 << Rx AND Ry\n");
              SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==21 && Data.PC<Data.mem.length && Data.in==true){     //NOT             
              Data.R[0]=(short)~Data.R[1];
              SimulatorUI.ConsolePanel.append("R0 << NOT Rx\n");
              SimulatorUI.R0_result.setText(toBinaryString(Data.R[0]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          
          //if(Data.PC<Data.mem.length&&Data.in == false){
              //Data.PC = Data.EA;
              //SimulatorUI.PC_result.setText(toBinaryString(Data.EA));
          //}
          if(Data.opcode==24 && Data.PC<Data.mem.length && Data.in==true){   //TRAP
              Data.PC = (short) (Data.mem[Data.mem.length - 15 + Data.R[3]]);  // last 16 addresses for table
              SimulatorUI.ConsolePanel.append("PC"+" << "+toBinaryString(Data.PC)+"\n"); 
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==25 && Data.PC<Data.mem.length && Data.in==true){  //SRC
              if(Data.LR == 0){  //shift right
                  //short signBit = (short) (Data.R[Data.RSR] & 0x8000);
                  if(Data.AL == 0){  // arithmetic
                      Data.R[Data.RSR] =(short)( Data.R[Data.RSR]>>Data.count);                    
                      SimulatorUI.ConsolePanel.append("R "+Long.toString(Data.RSR)+" shift right "+Long.toString(Data.count)+" bits arithmetically\n"); 
                  }
                  else{  //logic
                      Data.R[Data.RSR] =(short)( Data.R[Data.RSR]>>1L);
                      Data.R[Data.RSR] = (short)(Data.R[Data.RSR] & 0x7FFF); //sign bit set zero
                      Data.R[Data.RSR] =(short)( Data.R[Data.RSR]>>(Data.count - 1));
                      SimulatorUI.ConsolePanel.append("R "+Long.toString(Data.RSR)+" shift right "+Long.toString(Data.count)+" bits logically\n"); 
                  }
              }
              if(Data.LR == 1){  //shift left
                  if(Data.AL == 0){  // arithmetic
                      Data.R[Data.RSR] =(short)( Data.R[Data.RSR]<<Data.count);                    
                      SimulatorUI.ConsolePanel.append("R "+Long.toString(Data.RSR)+" shift left "+Long.toString(Data.count)+" bits arithmetically\n"); 
                  }
                  else{  //logic                    
                      Data.R[Data.RSR] =(short)( Data.R[Data.RSR]<<Data.count );
                      SimulatorUI.ConsolePanel.append("R "+Long.toString(Data.RSR)+" shift left "+Long.toString(Data.count)+" bits logically\n"); 
                  }
              }
             if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode==26 && Data.PC<Data.mem.length && Data.in==true){  //SRC
              String str = toBinaryString(Data.R[Data.RSR]) + toBinaryString(Data.R[Data.RSR]);
              if(Data.LR == 1){  //rotate left
                  str = str.substring(Data.count, Data.count+16);
                  Data.R[Data.RSR] = stringToBinary(str);
                  SimulatorUI.ConsolePanel.append("R "+Long.toString(Data.RSR)+" rotate left "+Long.toString(Data.count)+" bits\n");
              }
              if(Data.LR == 0){  //rotate right
                  str = str.substring(16-Data.count, 16-Data.count+16);
                  Data.R[Data.RSR] = stringToBinary(str);
                  SimulatorUI.ConsolePanel.append("R "+Long.toString(Data.RSR)+" rotate right "+Long.toString(Data.count)+" bits\n");
              }            
             if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode == 49){  //IN
              if(Data.devid ==0){  //input from keyboard
                  SimulatorUI.ConsolePanel.append("Type a character below:\n");
                  SimulatorUI.Keyboard.append("Input a character: \n");  
                  final String str = "";
                  KeyAdapter k = new KeyAdapter(){
                      @Override
                      public void keyPressed(KeyEvent e){   
                          synchronized(str){
                            char c = e.getKeyChar();   
                            Data.R[Data.RSR] = (short) c;
                            if(c != KeyEvent.VK_ENTER){                           
                                Data.devstat = 1; //set keyboard status to 1                             
                                SimulatorUI.ConsolePanel.append("Input: "+ c + " to R"+Long.toString(Data.RSR)+"\n");   
                            }
                            else
                                Data.devstat = 0; //set keyboard status to 1
                            if(Data.RSR==0)
                                    SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
                                if(Data.RSR==1)
                                    SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
                                if(Data.RSR==2)
                                    SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
                                if(Data.RSR==3)
                                    SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
                          str.notify();
                          }
                      }
                  };
                  SimulatorUI.Keyboard.addKeyListener(k); 
                  synchronized(str){
                      str.wait();
                  }
                  SimulatorUI.Keyboard.removeKeyListener(k);
              }
              else if(Data.devid==2){  //input from file                  
                  File file = new File("CardReader.txt");                
                  try{
                  BufferedReader br = new BufferedReader(new FileReader(file));
                  String str;
                  if((str = br.readLine())!=null){             
                    char[] chars = str.toCharArray();
                    short tmp = 0;
                    for(int j = 0; j<Short.SIZE; j++){                 
                        tmp += (short) (chars[j] - '0');
                        if(j<Short.SIZE-1)
                        tmp = (short) (tmp<<1);
                    }
                    Data.R[Data.RSR] = tmp;
                  }
                  }
                  catch(IOException e){
                      System.out.println(e.getMessage());
                  }                 
                    if(Data.RSR==0)
                        SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
                    if(Data.RSR==1)
                        SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
                    if(Data.RSR==2)
                        SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
                    if(Data.RSR==3)
                        SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
                    SimulatorUI.ConsolePanel.append("Read "+ (char)Data.R[Data.RSR] + " to R"+Long.toString(Data.RSR)+" from Card Reader.\n");
                       
              }
              else {
                SimulatorUI.ConsolePanel.append("Wrong Device ID for IN!!\n");
                SimulatorUI.MFR_result.setText("2");
                Data.mem[1] = 2;
                showMem();
                stopFlag = true;
                SimulatorUI.ConsolePanel.append("-------------------HALT--------------------\n");
            }
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          if(Data.opcode == 50){  //OUT
              if(Data.devid==1){
                SimulatorUI.ConsolePanel.append("R"+Long.toString(Data.RSR)+": "+(char)Data.R[Data.RSR]+"\n");
                Data.PC++;
                SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
                SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
                SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
              }
              else {
                SimulatorUI.ConsolePanel.append("Wrong Device ID for OUT!!\n");
                SimulatorUI.MFR_result.setText("2");
                Data.mem[1] = 2;
                showMem();
                stopFlag = true;
                SimulatorUI.ConsolePanel.append("-------------------HALT--------------------\n");
            }
                              
          }
          if(Data.opcode == 51){  //CHK
              if(Data.devid==0){
                  Data.R[Data.RSR] = Data.devstat;
                  SimulatorUI.ConsolePanel.append("Check Console keyboard status: "+Long.toString(Data.devstat)+"\n");
              }
              else if(Data.devid==1){
                  Data.R[Data.RSR] = 1;
                  SimulatorUI.ConsolePanel.append("Check Console Printer status: 1\n");
              }
              else if(Data.devid==2){
                  Data.R[Data.RSR] = 1;
                  SimulatorUI.ConsolePanel.append("Check Card Reader status: 1\n");
              }
              else {
                SimulatorUI.ConsolePanel.append("Wrong Device ID (0,1 or 2)!!\n");
                SimulatorUI.MFR_result.setText("2");
                Data.mem[1] = 2;
                showMem();
                stopFlag = true;
                SimulatorUI.ConsolePanel.append("-------------------HALT--------------------\n");
            }                        
              if(Data.RSR==0)
                  SimulatorUI.R0_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==1)
                  SimulatorUI.R1_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==2)
                  SimulatorUI.R2_result.setText(toBinaryString(Data.R[Data.RSR]));
              if(Data.RSR==3)
                  SimulatorUI.R3_result.setText(toBinaryString(Data.R[Data.RSR]));
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("----------------------------------------------\n");
          }
          
          if(Data.opcode == 27 && Data.in == true) // Float ADD
          {
             int dataFromMemHigh = Data.mem[Data.EA];
             int dataFromMemLow = Data.mem[Data.EA + 1];
             int dataFromMem = (dataFromMemHigh << 16)+ dataFromMemLow;
             
             float fdata = Float.intBitsToFloat(dataFromMem);        
             
             if(Data.RSR==0)
             {
                Data.FR[0] += fdata;
                int idata = Float.floatToIntBits(Data.FR[0] + fdata);
                SimulatorUI.FR0_result.setText(toBinaryString32(idata));
             }
             if(Data.RSR==1)
             {
                Data.FR[1] += fdata;
                int idata = Float.floatToIntBits(Data.FR[1] + fdata);
                SimulatorUI.FR1_result.setText(toBinaryString32(idata));
             }
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("-------------------Float--------------------\n");    
          }
          
          if(Data.opcode == 28 && Data.in == true) // Float SUB
          {
             int dataFromMemHigh = Data.mem[Data.EA];
             int dataFromMemLow = Data.mem[Data.EA + 1];
             int dataFromMem = (dataFromMemHigh << 16)+ dataFromMemLow;
             
             float fdata = Float.intBitsToFloat(dataFromMem);        
             
             if(Data.RSR==0)
             {
                 Data.FR[0] -= fdata;
                int idata = Float.floatToIntBits(Data.FR[0] - fdata);
                SimulatorUI.FR0_result.setText(toBinaryString32(idata));
             }
             if(Data.RSR==1)
             {
                 Data.FR[1] -= fdata;
                int idata = Float.floatToIntBits(Data.FR[1] - fdata);
                SimulatorUI.FR1_result.setText(toBinaryString32(idata));
             }
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("-------------------Float--------------------\n");    
          }
          if((Data.opcode == 29 || Data.opcode == 30) && Data.in == true)
          {
              Data.R[0] = 20;
              SimulatorUI.ConsolePanel.append("Length <- R0("+ toBinaryString(Data.R[0])+")\n");
              short firstVectorAddr =   Data.mem[Data.EA];
              short secVectorAddr  = Data.mem[Data.EA + 1];
              
              Pipeline p = null;
              if(Data.opcode == 29)
                  p = new Pipeline(0);
              if(Data.opcode == 30)
                  p = new Pipeline(1);
              
              p.load(firstVectorAddr, secVectorAddr, Data.R[0]);
               
              while(p.updata()){}
              Data.PC++;
              SimulatorUI.ConsolePanel.append("PC = PC + 1\n");
              SimulatorUI.PC_result.setText(toBinaryString(Data.PC));
              SimulatorUI.ConsolePanel.append("-------------------VECTOR--------------------\n");  

              
          }        
    }               
    @Override      
    public void run(){        
        while(stopFlag == false){     
            if(debug == true){
                try{
                    execute();
                } catch(Exception e){}
                mysuspend();
            }
            if(debug == false){
                while(Data.PC<Data.mem.length && stopFlag == false){
                    try{
                    execute();
                } catch(Exception e){}
                }
                mysuspend();
            }          
        }
    }
    synchronized void myresume(){
        notify();
    }
    synchronized void mysuspend(){
        try{              
            synchronized(this){
                wait(9999999);
            }
            }
            catch (InterruptedException e) {
                System.out.println("interrupted.");
            }
    }
}
