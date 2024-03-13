# Nand2Tetris6
project6 - Assembler
This project focuses on building an assembler for the Hack computer as part of the Nand to Tetris course. 
The assembler translates symbolic assembly language mnemonics into binary machine code instructions that can be executed by the Hack computer's CPU.
Key Components:
- Code - Translates assembly language mnemonics into binary machine code instructions.
- Parser - Parses assembly code lines to extract symbols, labels, mnemonics, and operands.
- Assembler - Orchestrates parsing and code translation processes, manages symbol tables, and generates machine code.
- Main - Acts as the entry point, initializes components, and coordinates the assembly process.
- SymbolTable - Manages symbol information, maps labels to memory addresses, and resolves addresses during assembly.
- InstructionType - Defines instruction types (e.g., A-instructions, C-instructions) for organizing and processing instructions.
