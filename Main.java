import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
		// validate the input file
		if (args.length == 0) {
			System.out.println("Error: No command-line arguments provided");
		}
		String inputPath = args[0].trim();
		File inputFile = new File(inputPath);
		if (!inputFile.exists()) {
			System.out.println("Error: file or directory not found");
		}

		try {
            if (inputFile.isDirectory()) {
                // Input is a directory, process all files in the directory
                File[] files = inputFile.listFiles((dir, name) -> name.toLowerCase().endsWith(".asm"));
                if (files != null) {
                    for (File file : files) {
                        processFile(file);
                    }
                } else {
                    System.out.println("Error: Unable to list files in the directory");
                }
            } else if (inputFile.isFile()) {
                // Input is a single file
                processFile(inputFile);
            } else {
                System.out.println("Error: Invalid input");
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

	private static void processFile(File sourceFile) throws IOException {
		// create the output file - same as the original path with .hack suffix
		String sourceAbsolutePath = sourceFile.getAbsolutePath();
		String fileName = sourceFile.getName();
		int fileNameExtensionIndex = fileName.lastIndexOf(".");
		String fileNameNoExtension = fileName.substring(0, fileNameExtensionIndex);
		int fileNameIndex = sourceFile.getAbsolutePath().indexOf(sourceFile.getName());
		String sourceDirectory = sourceAbsolutePath.substring(0, fileNameIndex);
		String outputFilePath = sourceDirectory + fileNameNoExtension + ".hack";
		File outputFile = new File(outputFilePath);

		
		// create an HackAssembler
		HackAssembler assembler = new HackAssembler(sourceFile, outputFile);
		// translate to machine code
		assembler.firstPass();
		assembler.secondPass();
		
	}

}
