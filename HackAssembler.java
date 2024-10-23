import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HackAssembler {
    private File hackAssemblyFile;
    private File machineCodeFile;
    private SymbolTable symbolTable;

    /**
     * constructor of the HackAssembler
     * @param source
     */
    public HackAssembler(File asm, File hack) {
        this.hackAssemblyFile = asm;
        this.machineCodeFile = hack;
        this.symbolTable = new SymbolTable();
    }

    /**
     * this method goes over the assembly file, reads the program lines, one by one.
     * focusing only on (label) declarations.
     * Adds the found labels to the symbol table
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void firstPass() throws FileNotFoundException,IOException{
        Parser parse = new Parser(this.hackAssemblyFile);
        String currLabel;
        Integer address = 0;

        while (parse.hasMoreLines()) {
            InstructionType type = parse.instructionType();
            if (type.equals(InstructionType.L_COMMAND)) {
                // this is a L instruction 
                currLabel = parse.symbol(type);
                // add the label to the symbolTable if it isn't already there
                if (!symbolTable.contains(currLabel)) {
                    this.symbolTable.addEntry(currLabel, address);
                }
                // ingore this line in the address counting
                address--;
            }
            // advance and increase the address by 1 also
            parse.advance();
            address++;
        }
    }

    /**
     * this method goes over the assembly file again from the beginning, reads the program lines, one by one.
     * this method gets the next instruction, parses it and writes it to the machine code file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void secondPass() throws FileNotFoundException,IOException {
        Parser parse = new Parser(this.hackAssemblyFile);
        Code coder = new Code();
        BufferedWriter writer = new BufferedWriter(new FileWriter(machineCodeFile));
        InstructionType type;
        String symbol;
        int decimalNumber;
        String binaryString = "";
        String dest;
        String comp;
        String jump;
        Integer address = 16;

        while (parse.hasMoreLines()) {
            parse.advance();
            type = parse.instructionType();
            if (type.equals(InstructionType.A_COMMAND)) {
                // this is an A instruction
                symbol = parse.symbol(type);
                // seperate the treatment between numbers and variables
                if (!isNumeric(symbol)) {
                    //the symbol is a variable
                    // add the symbol to the symbolTable if it isn't already there
                    if (!symbolTable.contains(symbol)) {
                        symbolTable.addEntry(symbol, address);
                        decimalNumber = address;
                        address++;
                    } else {
                        //this is an existing variable
                        decimalNumber = symbolTable.getAddress(symbol);
                    }
                } else {
                    //the symbol is a number
                    decimalNumber = Integer.parseInt(symbol);
                }
                // convert the symbol to binary string with 16 bits
                binaryString = Integer.toBinaryString(decimalNumber);
                binaryString = bit16(binaryString);
                // write the binary string in the output file in a seperate line
                writer.write(binaryString);
                writer.newLine();
            } 
            else if (type.equals(InstructionType.C_COMMAND)) {
                // this is a C instruction
                //create binary strings to the dest,comp,jump fields
                dest = parse.dest();
                if (dest == null) {
                    dest = "null";
                }
                dest = coder.dest(dest);
                comp = parse.comp();
                if (comp == null) {
                    comp = "null";
                }
                comp = coder.comp(comp);
                jump = parse.jump();
                if (jump == null) {
                    jump = "null";
                }
                jump = coder.jump(jump);
                //combine them to one binary string
                binaryString = "111" + comp + dest + jump;
                // write the binary string in the output file in a seperate line
                writer.write(binaryString);
                writer.newLine();
            }
        }
        writer.close();
    }


    /**
     * helper function that identifies a symbol as number or variable
     * @param symbol
     * @return true if the symbol is only numbers, and false otherwise
     */
    public static boolean isNumeric(String symbol) {
        for (int i = 0; i<symbol.length(); i++) {
            if (!Character.isDigit(symbol.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String bit16(String str) {
        int len = str.length();
        int zeros = 16-len;
        StringBuilder zero = new StringBuilder();
        for (int i = 0; i<zeros; i++) {
            zero.append("0");
        }
        zero.append(str);
        String zeroString = zero.toString();
        return zeroString;
    }
}
