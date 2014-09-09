public class Data {
    public static short[] cAddr = new short[2];  //current address, 16 bits
    public static short[] mem = new short[2048];
    public static short PC = 2; //Address 0 & 1 are reserved
    public static short IR = 0;
    public static short RSR  = 0;
    public static short X0 = 0;
    public static short MAR = 0;
    public static short MBR = 0;
    
    public static short opcode = 0;
    public static short I = 0;
    public static short IX = 0;
    public static short[] R = new short[4];
    public static short addr = 0;
    public static short EA = 0;
    public static boolean in = false; //indicator for whether EA is out of boundary
    
    public static short[][] cache = new short[10][2];   //cache size is 5 words
    public static short C = 0;   //cache counter, initial value 0
    
    public static short CC = 0;   //Condition Counter
    
    public static short D1 =0;   // ALU
    public static short D2 =0;
    
    public static short LR =0;   // SRC, RRC
    public static short AL =0;
    public static short count =0;
    public static short immed=0; //immediate value
    
    public static short devid = 0; //devid
    public static short devstat = 0; //device status for keyboard
    
    public static float[] FR = new float[2]; // fload register
    
    
    public Data(){
        for(int i=0; i<mem.length; i++){
           this.mem[i] = 0; 
        }
        for(int i = 0; i<cAddr.length; i++){
            this.cAddr[i] = 0;
        }  
        for(int i = 0; i<R.length; i++){
            this.R[i] = 0;       
        } 
        for(int i = 0; i<cache.length; i++){
            for(int j = 0; j<2; j++){
                this.cache[i][0] = 0;
                this.cache[i][1]=0;
            }                  
        }
     } 
    
    public static void reset(){
        for(int i=0; i<mem.length; i++){
           Data.mem[i] = 0; 
        }
        mem[0] = (short)(mem.length - 15);   //initialize trap table  
        for(int i = 0; i<cAddr.length; i++){
            Data.cAddr[i] = 0;
        }  
        for(int i = 0; i<R.length; i++){
            Data.R[i] = 0;       
        }
        for(int i = 0; i<cache.length; i++){
            for(int j = 0; j<2; j++){
                Data.cache[i][0] = 0;
                Data.cache[i][1]=0;
            }                  
        }
        Data.PC = 2;
        Data.IR = 0;
        Data.RSR  = 0;
        Data.X0 = 0;
        Data.MAR = 0;
        Data.MBR = 0;  
        Data.opcode = 0;
        Data.I = 0;
        Data.IX = 0;
        Data.addr = 0;
        Data.EA = 0;
        Data.C = 0;
        Data.CC=0;
        Data.D1=0;
        Data.D2=0;
        Data.in = false;
        Data.LR =0;   
        Data.AL =0;
        Data.count=0;
        Data.immed=0;
        Data.devid = 0;
        Data.devstat = 0;
        Data.FR[0] = 0;
        Data.FR[1] = 0;
    }
    
    public static byte second(short val){     //low byte
        return  (byte)(val & 0xff);
    }
    
     public static byte first(short val){   //high byte
        return (byte)((val >> 8) & 0xff);
    }
}
