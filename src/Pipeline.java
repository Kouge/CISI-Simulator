
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ju Huo
 */
public class Pipeline {
    
    private final int ADD = 0;
    private final int SUB = 1;
    
    int operator = -1;
    
    private short firAddr = 0;
    private short secAddr = 0;
    private int length = 0;
    
    ArrayList<VectorElement> dataVector = new ArrayList();
    ArrayList<VectorElement> resultVector = new ArrayList();
    ArrayList<VectorElement> putSignVector = new ArrayList();
    
    public Pipeline(int op){
        operator = op;
    }
    
    public void load(short firstVectorAddr, short secVectorAddr, int len)
    {
        firAddr = firstVectorAddr;
        secAddr = secVectorAddr;
        length = len;
        for(int i = 0; i < len;i++){
            dataVector.add(
                    new VectorElement(Data.mem[firstVectorAddr + i],
                                      Data.mem[secVectorAddr + i],
                                      (short)(i+firstVectorAddr),
                                        i)
                    );
        }
    }
    
    public boolean updata()
    {
        SimulatorUI.ConsolePanel.append("------pipeline-------\n");
        VectorElement p1 = loadData();
        VectorElement p2 = null;
        if(operator == ADD)
            p2 = add();
        if(operator == SUB)
            p2 = sub();
        VectorElement p3 = putSign();
        
        if(null == p1 && p2 == null && p3 == null)
            return false;
        else{
            if(p1 != null)
                resultVector.add(p1);
            if(p2 != null)
                putSignVector.add(p2);
            return true;
        }
    }
    
    private VectorElement loadData()
    {
        if(!dataVector.isEmpty())
        {
            SimulatorUI.ConsolePanel.append("-LRD-\n");
            VectorElement e = dataVector.get(0);
            short fir = e.data1;
            SimulatorUI.ConsolePanel.append("A["+ e.index +"]:"+ Functions.toBinaryString(e.data1)+"\n");
            short sec = e.data2;
            SimulatorUI.ConsolePanel.append("B["+ e.index +"]:"+ Functions.toBinaryString(e.data2)+"\n");
            dataVector.remove(0);
            return e;
        }
        return null;
    }         
    
    private VectorElement add()
    {
        if(!resultVector.isEmpty())
        {
            SimulatorUI.ConsolePanel.append("-NORMALRESULT-\n");
            VectorElement e = resultVector.get(0);
            e.result =(short)( e.data1 + e.data2);
            SimulatorUI.ConsolePanel.append("result["+ e.index +"}:"+ "A["+ e.index +"] + "+"B["+ e.index +"]\n");
            SimulatorUI.ConsolePanel.append("result["+ e.index +"}:"+ Functions.toBinaryString(e.result)+"\n");
            resultVector.remove(0);
            return e;
        }
        return null;
    }
    
        private VectorElement sub()
    {
        if(!resultVector.isEmpty())
        {
            SimulatorUI.ConsolePanel.append("-NORMALRESULT-\n");
            VectorElement e = resultVector.get(0);
            e.result =(short)( e.data1 - e.data2);
            SimulatorUI.ConsolePanel.append("result["+ e.index +"}:"+ "A["+ e.index +"] - "+"B["+ e.index +"]\n");
            SimulatorUI.ConsolePanel.append("result["+ e.index +"}:"+ Functions.toBinaryString(e.result)+"\n");
            resultVector.remove(0);
            return e;
        }
        return null;
    }
    
    private VectorElement putSign()
    {
        if(!putSignVector.isEmpty())
        {
            SimulatorUI.ConsolePanel.append("-PUTSIGN-\n");
            VectorElement e = putSignVector.get(0);
            Data.mem[e.address] = e.result;
            SimulatorUI.ConsolePanel.append("A["+ e.index +"] <- "+"result["+ e.index +"]\n");
            putSignVector.remove(0);
            return e;
        }
        return null;
    }
}

class VectorElement{
    public short data1 = 0;
    public short data2 = 0;
    public short address = 0;
    public short result = 0;
    public int index = 0;
    public VectorElement(short val1,short val2,short addr,int i)
    {
        data1 = val1;
        data2 = val2;
        address = addr;
        index = i;
    }
}