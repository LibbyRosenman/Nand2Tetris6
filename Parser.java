import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Parser {
    private BufferedReader reader;
    private String currentLine;

    /**
     * contructor of the parser
     * @param source
     * @throws FileNotFoundException
     */
    public Parser(File source) throws FileNotFoundException {
        if (source == null) {
			throw new NullPointerException("source");
		}
		if (!source.exists()) {
			throw new FileNotFoundException(source.getAbsolutePath());
		}
        this.reader = new BufferedReader(new FileReader(source));
        this.currentLine = null;
    }
    
    /**
     * checks if there are more lines in the assembly file
     * @return true or false
     * @throws IOException
     */
    public boolean hasMoreLines() throws IOException {
        if ((this.currentLine = this.reader.readLine()) != null) {
            return true;
        } else { return false; }
    }
    
    /**
     * this method moves the pointer to the next line
     * this method is called only if hasMoreLines() is true
     * this method handles empty lines and documentation lines
     */
    public void advance() throws IOException {
        while (currentLine.trim().isEmpty() || currentLine.trim().startsWith("//")) {
            currentLine = this.reader.readLine();
        }
    }

    /**
     * this method returns the instruction type of the parsed string
     * @return A_command | L_command | C_command
     */
    public InstructionType instructionType() {
        if (currentLine.trim().charAt(0) == '@') {
            return InstructionType.A_COMMAND;
        } else if (currentLine.trim().charAt(0) == '(') {
            return InstructionType.L_COMMAND;
        } else {
            return InstructionType.C_COMMAND;
        }
    }

    /**
     * if the current line is an A instruction, the method returns the location after the '@'
     * if the current line is a L instruction, the method returns the data between the parentheses
     * @param command
     * @return the relevant string
     */
    public String symbol(InstructionType command) {
        if (command.equals(InstructionType.A_COMMAND)) {
            // the command is A_COMMAND, return the data after the '@'
            return currentLine.trim().substring(1);
        } else {
            // the command is L_COMMAND, return the data between the parentheses
            currentLine = currentLine.trim();
            int len = currentLine.length();
            return currentLine.substring(1,len-1);
        }
    }

    /**
     * the method returns the string that identifys the destination field
     * @return string
     */
    public String dest() {
        String current = currentLine.trim();
        int index = current.indexOf("=");
        if (index == -1) {
            return null;
        } else {
            return current.substring(0, index);
        }
    }

    /**
     * the method returns the string that identifys the computhing field
     * @return string 
     */
    public String comp() {
        String current = currentLine.trim();
        int index1 = current.indexOf("=");
        if (index1 != -1) {
			current = current.substring(index1 + 1);
		}
        int index2 = current.indexOf(";");
		if (index2 == -1) {
			return current;
		} else {
			return current.substring(0, index2);
		}
    }

    /**
     * the method returns the string that identifys the jump field
     * @return string
     */
    public String jump() {
        String current = this.currentLine.trim();
		int index = current.indexOf(";");
		if (index == -1) {
			return null;
		} else {
			return current.substring(index + 1);
		}
    }
}
