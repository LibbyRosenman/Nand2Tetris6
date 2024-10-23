public class Code {

    /**
     * this method returns the binary string that corresponds to the destination string given
     * according to the assignment instructions - "MD"&"DM", and also "ADM"&"AMD" are treated the same
     * @param dest
     * @return 3 chars string contains 0's and 1's
     */
    public String dest(String dest) {
        switch (dest) {
            case "null":
                return "000";
            case "M":
                return "001";
            case "D":
                return "010";
            case "DM":
                return "011";
            case "MD":
                return "011";
            case "A":
                return "100";
            case "AM":
                return "101";
            case "AD":
                return "110";
            case "AMD":
                return "111";
            case "ADM":
                return "111";
            default:
                throw new IllegalArgumentException("Unknown jump instruction: " + dest);
        }
    }


    /**
     * this method returns the binary string that corresponds to the computation string given
     * @param comp
     * @return 7 chars string contains 0's and 1's
     */
    public String comp(String comp) {
        switch (comp) {
            case "0":
                return "0101010";
            case "1":
                return "0111111";
            case "-1":
                return "0111010";
            case "D":
                return "0001100";
            case "A":
                return "0110000";
            case "M":
                return "1110000";
            case "!D":
                return "0001101";
            case "!A":
                return "0110001";
            case "!M":
                return "1110001";
            case "-D":
                return "0001111";
            case "-A":
                return "0110011";
            case "-M":
                return "1110011";
            case "D+1":
                return "0011111";
            case "A+1":
                return "0110111";
            case "M+1":
                return "1110111";
            case "D-1":
                return "0001110";
            case "A-1":
                return "0110010";
            case "M-1":
                return "1110010";
            case "D+A":
                return "0000010";
            case "D+M":
                return "1000010"; 
            case "D-A":
                return "0010011";
            case "D-M":
                return "1010011";
            case "A-D":
                return "0000111";
            case "M-D":
                return "1000111";
            case "D&A":
                return "0000000";
            case "D&M":
                return "1000000";
            case "D|A":
                return "0010101";
            case "D|M":
                return "1010101";
            default:
                throw new IllegalArgumentException("Unknown jump instruction: " + comp);
        }
    }

    /**
     * this method returns the binary string that corresponds to the jump string given
     * @param jump
     * @return 3 chars string contains 0's and 1's
     */
    public String jump(String jump) {
        switch (jump) {
            case "null":
                return "000";
            case "JGT":
                return "001";
            case "JEQ":
                return "010";
            case "JGE":
                return "011";
            case "JLT":
                return "100";
            case "JNE":
                return "101";
            case "JLE":
                return "110";
            case "JMP":
                return "111";
            default:
                return "000";
        }
    }
}
