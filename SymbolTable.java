import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> symbolMap;

    /**
     * constructor of the symbol table
     * constructs an empty table and puts the predefined symbols in the table
     */
    public SymbolTable() {
        this.symbolMap = new HashMap<>();
        // add the pre-defined symbols
        this.symbolMap.put("SP",0);
        this.symbolMap.put("LCL",1);
        this.symbolMap.put("ARG",2);
        this.symbolMap.put("THIS",3);
        this.symbolMap.put("THAT",4);
        this.symbolMap.put("R0",0);
        this.symbolMap.put("R1",1);
        this.symbolMap.put("R2",2);
        this.symbolMap.put("R3",3);
        this.symbolMap.put("R4",4);
        this.symbolMap.put("R5",5);
        this.symbolMap.put("R6",6);
        this.symbolMap.put("R7",7);
        this.symbolMap.put("R8",8);
        this.symbolMap.put("R9",9);
        this.symbolMap.put("R10",10);
        this.symbolMap.put("R11",11);
        this.symbolMap.put("R12",12);
        this.symbolMap.put("R13",13);
        this.symbolMap.put("R14",14);
        this.symbolMap.put("R15",15);
        this.symbolMap.put("SCREEN",16384);
        this.symbolMap.put("KBD",24576);
    }

    /**
     * this method adds a new symbol to the table with the relevant address
     * @param symbol
     * @param address
     */
    public void addEntry(String symbol, Integer address) {
        this.symbolMap.put(symbol,address);
    }

    /**
     * this method checks if a certain symbol is already in the table
     * @param symbol
     * @return true or false
     */
    public boolean contains(String symbol) {
        return this.symbolMap.containsKey(symbol);
    }

    /**
     * this method returns the address of a given symbol
     * @param symbol
     * @return Integer that represents an address in the instruction memory
     */
    public Integer getAddress(String symbol) {
        return this.symbolMap.get(symbol);
    }
}
