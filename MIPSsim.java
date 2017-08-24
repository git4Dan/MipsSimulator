//On my honor, I have neither given nor received unauthorized aid on this assignment
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MIPSsim{//class for this project

	public static void main(String[] args){//need to implement argv
		Scanner userInput = new Scanner(System.in);//used to get input from the user such as name of text file
		String file = "";//for the name of the input file for this program
		String nameOfInstruction = "";//for the name of each instruction
		String registers = "";//will be used to get the register numbers for instructions with up to three registers
		String registers2 = "";
		String registers3 = "";
		String offsetRead = "";//get the offset of the instruction if one is present
		long twoConvert = 0;//these are used to get two's complement values
		short offset2 = 0;
		int offset = 0;//convert to an offset in integer form
		int target = 0;
		int binaryConverter = 0;//to parse binary bits 
		int binaryConverter2 = 0;
		int binaryConverter3 = 0;
		int twoNumber = 0;//another two's complement integer
		int pcAddress = 64;//starting pc address for simulator
		ArrayList<String> listInstructions = new ArrayList<String>();//will hold the entire disassembled instruction
		String[] instrName = new String[500];//just has the name of the instruction
		int[] pcAdd = new int[500];//stores all the pc addresses
		int[] dataPC = new int[500];//store pc address of data 
		int[] dataValue = new int[500];//stores the actual data values
		int[] registerValue = new int[32];//an array to store all the values of a register
		int arrayIndex = 0;//an index to be used for multiple arrays
		int pcIndex = 0;//index to be used for the pcAdd array
		int cycle = 1;//starting cycle number
		for(int x = 0; x < dataPC.length; x++){
			dataPC[x] = 0;//initialize all value of dataPC to zero
		}
		
		boolean loopCheck = false;//boolean value to be used to exit simulator later
		try{
			file = userInput.next();
			File fileName = new File(file);//create a File object
			Scanner fileRead = new Scanner(fileName);//scanner to handle reading the input file
			File disassembler = new File("disassembly.txt");//file to be written to
			PrintWriter disWriter = new PrintWriter(disassembler);//used to write to disassembler file
			
			while(fileRead.hasNextLine()){//this loop completes the disassembler portion of the project
				String bits = fileRead.nextLine();//as long as there is a next line in input file, continue
				if(bits.charAt(0) == '0' && bits.charAt(1) == '0' && bits.charAt(2) == '1'){//category 1
					//below all the instructions will be parsed and the appropriate text will be written to the output file
					if(bits.charAt(3) == '0' && bits.charAt(4) == '0' && bits.charAt(5) == '0'){//NOP Instruction
						nameOfInstruction = "NOP";
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction);
						disWriter.println();
						listInstructions.add(nameOfInstruction);//write to file the name of the instruction
						instrName[pcIndex] = nameOfInstruction;//add the name to the array for later when simulating
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '0' && bits.charAt(5) == '1'){//J
						nameOfInstruction = "J";
						offsetRead += bits.charAt(6);//just parsing the bits to get the offset in this case
						offsetRead += bits.charAt(7);
						offsetRead += bits.charAt(8);
						offsetRead += bits.charAt(9);
						offsetRead += bits.charAt(10);
						offsetRead += bits.charAt(11);
						offsetRead += bits.charAt(12);
						offsetRead += bits.charAt(13);
						offsetRead += bits.charAt(14);
						offsetRead += bits.charAt(15);
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						
						offset = twoComplementConverter(offsetRead);//parse to an integer value the offset of the jump instruction
						target = offset*4;//shift the offset left twice
						
						disWriter.print(bits + "\t");//writes to the file the inputed instruction
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "#" + target);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "#" + target);//add the entire instruction to an listInstructions arrayList
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '1' && bits.charAt(5) == '0'){//BEQ
						nameOfInstruction = "BEQ";//continue repeating similar process for all instructions that follow
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset2);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " +  "#" + offset2);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '1' && bits.charAt(5) == '1'){//BNE
						nameOfInstruction = "BNE";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);//parse the two's complement offset value which has to be constrained to a short
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset2);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " +  "#" + offset2);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '0' && bits.charAt(5) == '0'){//BGTZ
						nameOfInstruction = "BGTZ";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "#" + offset2);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " +  "#" + offset2);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '0' && bits.charAt(5) == '1'){//SW
						nameOfInstruction = "SW";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter2 + ", " + offset2 + "(" + "R" + binaryConverter + ")");
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter2 + ", " + offset2 + "(" + "R" + binaryConverter + ")");
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '1' && bits.charAt(5) == '0'){//LW
						nameOfInstruction = "LW";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter2 + ", " + offset2 + "(" + "R" + binaryConverter + ")");
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter2 + ", " + offset2 + "(" + "R" + binaryConverter + ")");
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '1' && bits.charAt(5) == '1'){//BREAK
						nameOfInstruction = "BREAK";//this instruction consists only of the name
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction);
						disWriter.println();
						listInstructions.add(nameOfInstruction);
						instrName[pcIndex] = nameOfInstruction;
					}
					registers = "";
					registers2 = "";
					binaryConverter = 0;
					binaryConverter2 = 0;
					nameOfInstruction = "";
					offsetRead = "";
					offset = 0;
					offset2 = 0;
					twoConvert = 0;
					target = 0;
				}
				else if(bits.charAt(0) == '0' && bits.charAt(1) == '1' && bits.charAt(2) == '0'){//category 2
					//repeat similar process for all category 2 instructions
					if(bits.charAt(3) == '0' && bits.charAt(4) == '0' && bits.charAt(5) == '0'){//XOR Instruction
						nameOfInstruction = "XOR";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '0' && bits.charAt(5) == '1'){//MUL
						nameOfInstruction = "MUL";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);//need to parse all register values as there are three present for this instruction
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '1' && bits.charAt(5) == '0'){//ADD
						nameOfInstruction = "ADD";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '1' && bits.charAt(5) == '1'){//SUB
						nameOfInstruction = "SUB";

						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '0' && bits.charAt(5) == '0'){//AND
						nameOfInstruction = "AND";

						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '0' && bits.charAt(5) == '1'){//OR
						nameOfInstruction = "OR";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '1' && bits.charAt(5) == '0'){//ADDU
						nameOfInstruction = "ADDU";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '1' && bits.charAt(5) == '1'){//SUBU
						nameOfInstruction = "SUBU";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						registers3 += bits.charAt(16);
						registers3 += bits.charAt(17);
						registers3 += bits.charAt(18);
						registers3 += bits.charAt(19);
						registers3 += bits.charAt(20);
						binaryConverter3 = Integer.parseInt(registers3, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "R" + binaryConverter3);
						instrName[pcIndex] = nameOfInstruction;
					}
					
					registers = "";
					registers2 = "";
					registers3 = "";
					binaryConverter = 0;
					binaryConverter2 = 0;
					binaryConverter3 = 0;
					nameOfInstruction = "";
					offsetRead = "";
					offset = 0;
				}
				else if(bits.charAt(0) == '1' && bits.charAt(1) == '0' && bits.charAt(2) == '0'){//category 3
					//repeat similar process for all category 3 instructions that follow
					if(bits.charAt(3) == '0' && bits.charAt(4) == '0' && bits.charAt(5) == '0'){//ORI Instruction
						nameOfInstruction = "ORI";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset = Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '0' && bits.charAt(5) == '1'){//XORI
						nameOfInstruction = "XORI";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset = Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '1' && bits.charAt(5) == '0'){//ADDI
						nameOfInstruction = "ADDI";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset2);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset2);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '0' && bits.charAt(4) == '1' && bits.charAt(5) == '1'){//SUBI
						nameOfInstruction = "SUBI";
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset2 = (short) Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset2);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset2);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '0' && bits.charAt(5) == '0'){//ANDI
						nameOfInstruction = "ANDI";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(16);
						offsetRead += bits.charAt(17);
						offsetRead += bits.charAt(18);
						offsetRead += bits.charAt(19);
						offsetRead += bits.charAt(20);
						offsetRead += bits.charAt(21);
						offsetRead += bits.charAt(22);
						offsetRead += bits.charAt(23);
						offsetRead += bits.charAt(24);
						offsetRead += bits.charAt(25);
						offsetRead += bits.charAt(26);
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset = Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '0' && bits.charAt(5) == '1'){//SRL
						nameOfInstruction = "SRL";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset = Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '1' && bits.charAt(5) == '0'){//SRA
						nameOfInstruction = "SRA";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset = Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						instrName[pcIndex] = nameOfInstruction;
					}
					else if(bits.charAt(3) == '1' && bits.charAt(4) == '1' && bits.charAt(5) == '1'){//SLL
						nameOfInstruction = "SLL";
						
						registers += bits.charAt(6);
						registers += bits.charAt(7);
						registers += bits.charAt(8);
						registers += bits.charAt(9);
						registers += bits.charAt(10);
						binaryConverter = Integer.parseInt(registers, 2);
						
						registers2 += bits.charAt(11);
						registers2 += bits.charAt(12);
						registers2 += bits.charAt(13);
						registers2 += bits.charAt(14);
						registers2 += bits.charAt(15);
						binaryConverter2 = Integer.parseInt(registers2, 2);
						
						
						offsetRead += bits.charAt(27);
						offsetRead += bits.charAt(28);
						offsetRead += bits.charAt(29);
						offsetRead += bits.charAt(30);
						offsetRead += bits.charAt(31);
						
						offset = Integer.parseInt(offsetRead, 2);
						
						disWriter.print(bits + "\t");
						disWriter.print(pcAddress + "\t");
						disWriter.print(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						disWriter.println();
						listInstructions.add(nameOfInstruction + " " + "R" + binaryConverter + ", " + "R" + binaryConverter2 + ", " + "#" + offset);
						instrName[pcIndex] = nameOfInstruction;
					}
					registers = "";
					registers2 = "";
					binaryConverter = 0;
					binaryConverter2 = 0;
					nameOfInstruction = "";
					offsetRead = "";
					offset = 0;
					offset2 = 0;
					twoConvert = 0;
				}
				else{//the code below gets all the two's complement data values found in the input file
					twoConvert = Long.parseLong(bits, 2);
					twoNumber = (int) twoConvert;//all values need to be parsed and stored appropriately
					disWriter.print(bits + "\t");
					disWriter.print(pcAddress + "\t");
					disWriter.print(twoNumber);//then they need to be written to file
					disWriter.println();
					dataPC[arrayIndex] = pcAddress;//the pc adress that corresponds to the data values are stored in the array
					dataValue[arrayIndex] = twoNumber;//store the actual data value in an array to be used later for the simulator
					arrayIndex++;

					twoConvert = 0;//reset the values back to zero 
					twoNumber = 0;
				}
				pcAdd[pcIndex] = pcAddress;//store the pc address in an array to be used for the simulator
				pcIndex++;
				pcAddress += 4;//increment the pc address by 4 after each instruction
			}
			fileRead.close();//close both file streams
			disWriter.close();
			
			for(int x = 0; x < registerValue.length; x++){
				registerValue[x] = 0;//initialize all register contents to zero
			}
			File simulation = new File("simulation.txt");//create a new file object for the simulation
			PrintWriter simWriter = new PrintWriter(simulation);//be able to write to the file
			int i = 0;//global index for the simulation below that will be used to access each instruction
			String registerNum = "";
			int registerVal = 0;//register values to be parsed from the instructions that are disassembled
			int registerVal2 = 0;
			int registerVal3 = 0;
			boolean afterReg = false;//boolean values that are used to get each register or offset from a particular instruction
			boolean afterOff = false;
			boolean afterReg2 = false;
			while(loopCheck == false){//as long as the instruction BREAK is not encountered continue simulating
				simWriter.println("--------------------");
				simWriter.println("Cycle " + cycle + ":\t" + pcAdd[i] + "\t" + listInstructions.get(i));//WILL NEED TO ALTER THIS FOR BRANCH INSTRUCTIONS
				simWriter.println();
				simWriter.println("Registers");
				if(instrName[i] == ("NOP")){//if NOP no reason to do anything
					
				}
				else if(instrName[i] == ("J")){//if a jump instruction
					int offsetAmount = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(afterReg == false && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							offsetAmount = Integer.parseInt(registerNum);//parse the offset that was already shifted left twice to get to the appropriate address
							j--;
							registerNum = "";//reset value of the string
						}
					}
					for(int x = 0; x < pcAdd.length; x++){
						if(pcAdd[x] == offsetAmount){//look for the address that jump is pointing to from the array of pc adresses
							i = x - 1;				//and if equal, set i to point to that address in the following loop run
							break;
						}
					}
					
					afterReg = false;//reset the boolean values after every instruction
				}
				else if(instrName[i] == ("BEQ")){//if BEQ instruction
					int offsetAmount = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);//parse the first register number
							j--;
							registerNum = "";
							afterReg2 = true;
							afterReg = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							offsetAmount = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int location = 0;//the location to branch to
					if(registerValue[registerVal] == registerValue[registerVal2]){//if the register contents are equal then a branch occurs
						location = (offsetAmount * 4) + (pcAdd[i]);//branches to the location, which is the offset shifted left twice and added to the current pc address 
						for(int x = 0; x < pcAdd.length; x++){
							if(pcAdd[x] == location){//once the correct address is generated, look for it in the array of pc addresses to get the proper index of the instruction to branch to
								i = x;
								break;
							}
						}
					}
					
					afterReg = false;//reset all values again
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					afterReg2 = false;
					
				}
				else if(instrName[i] == ("BNE")){//repeat similar process
					int offsetAmount = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){//if a register is encountered in the instruction
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){//if a number is encountered, then it is the register number
								registerNum += listInstructions.get(i).charAt(j);//keep adding as long as there are digits 
								j++;
							}
							registerVal = Integer.parseInt(registerNum);//parse all register values
							j--;
							registerNum = "";
							afterReg2 = true;
							afterReg = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){//if an offset is encountered in the instruction
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							offsetAmount = Integer.parseInt(registerNum);//parse the offset
							j--;
							registerNum = "";
						}
					}
					
					int location = 0;
					if(registerValue[registerVal] != registerValue[registerVal2]){
						location = (offsetAmount * 4) + (pcAdd[i]);
						for(int x = 0; x < pcAdd.length; x++){
							if(pcAdd[x] == location){//get the address of the instruction that is being branched to
								i = x;
								break;
							}
						}
					}
					
					afterReg = false;//reset all values
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("BGTZ")){//similar to other branch instructions
					int offsetAmount = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
						}
						else if(afterReg == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							offsetAmount = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					int location = 0;
					if(registerValue[registerVal] > 0){
						location = (offsetAmount * 4) + (pcAdd[i]);
						for(int x = 0; x < pcAdd.length; x++){
							if(pcAdd[x] == location){//get the index of the instruction that is branched to
								i = x;
								break;
							}
						}
					}
					
					afterReg = false;//reset values
					registerVal = 0;
				}
				else if(instrName[i] == ("SW")){//if store word instruction
					int instNum = 0;
					int offsetAmount = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}//get the offset value once first register is already parsed and a number is encountered
						else if(afterReg2 == true && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
							while(Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-'){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							offsetAmount = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg2 = false;
							afterOff = true;//allows you to enter final conditional
						}
						else if(afterOff == true && listInstructions.get(i).charAt(j) == 'R'){//gets the register after the offset is found
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){//gets last register
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);//parses last register value
							j--;
							registerNum = "";
						}
						
					}
					int location = registerValue[registerVal2] + offsetAmount;
					for(int x = 0; x < dataPC.length; x++){//gets the location from data that is being stored to
						if(dataPC[x] == location){
							instNum = x;
							break;
						}//and just in case location is not a multiple of four
						else if(location == dataPC[x] + 1 || location == dataPC[x] + 2 || location == dataPC[x] + 3){
							instNum = x;
							break;
						}
					}
					dataValue[instNum] = registerValue[registerVal];//store the register value into the appropriate data address
					
					afterReg = false;//reset values
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("LW")){//similar to SW instruction
					int instNum = 0;
					int offsetAmount = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
							while(Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-'){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							offsetAmount = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg2 = false;
							afterOff = true;
						}
						else if(afterOff == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
						
					}
					int location = registerValue[registerVal2] + offsetAmount;
					for(int x = 0; x < dataPC.length; x++){
						if(dataPC[x] == location){
							instNum = x;
							break;
						}
						else if(location == dataPC[x] + 1 || location == dataPC[x] + 2 || location == dataPC[x] + 3){
							instNum = x;
							break;
						}
					}
					registerValue[registerVal] = dataValue[instNum];//instead of SW, store the data value into the appropriate register
					afterReg = false;//reset values
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("BREAK")){
					loopCheck = true;//if a break instruction is encountered, then the simulation is complete, so set the boolean value of the loop to true
				}
				else if(instrName[i] == ("XOR")){//if XOR
					for(int j = 0; j < listInstructions.get(i).length(); j++){//will check for three registers in the instructions
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false && Character.isDigit(listInstructions.get(i).charAt(j+1))){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg2 = false;
							afterOff = true;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] ^ registerValue[registerVal3];//once everything is parsed bitwise XOR the second and third registers and store in first register
					registerValue[registerVal] = binaryVal;
					
					afterReg = false;//reset values
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("MUL")){//repeat process for MUL for all category 2 instructions
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg2 = false;
							afterOff = true;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					registerValue[registerVal] = registerValue[registerVal2] * registerValue[registerVal3];//multiply second and third registers and store in first register
					
					afterReg = false;//again reset values before next run through
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("ADD")){
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					registerValue[registerVal] = registerValue[registerVal2] + registerValue[registerVal3];//add second and third register contents and store in first
					
					afterReg = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("SUB")){
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}//subtract second and third register contents and store into first one
					registerValue[registerVal] = registerValue[registerVal2] - registerValue[registerVal3];
					
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("AND")){
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					int binaryVal = registerValue[registerVal2] & registerValue[registerVal3];//bitwise AND contents of register 2 and register 3
					registerValue[registerVal] = binaryVal;//store this result into first register
					
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("OR")){
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false && Character.isDigit(listInstructions.get(i).charAt(j+1))){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg2 = false;
							afterOff = true;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] | registerValue[registerVal3];//bitwise OR contents of register 2 and register 3 
					registerValue[registerVal] = binaryVal;//store the result into register 1
			
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("ADDU")){
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg2 = false;
							afterOff = true;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					registerValue[registerVal] = registerValue[registerVal2] + registerValue[registerVal3];//add contents of registers 2 and 3, and store into register 1
					
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("SUBU")){
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == 'R')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal3 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					registerValue[registerVal] = registerValue[registerVal2] - registerValue[registerVal3];//subtract contents of registers 2 and 3 and store in register 1
					
					afterReg = false;//reset values again
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
					registerVal3 = 0;
				}
				else if(instrName[i] == ("ORI")){
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false && Character.isDigit(listInstructions.get(i).charAt(j+1))){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					int binaryVal = registerValue[registerVal2] | immediateValue;//bitwise OR the contents of register 2 with an immediate value
					registerValue[registerVal] = binaryVal;//store the result of the above computation in the appropriate register
					
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("XORI")){
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false && Character.isDigit(listInstructions.get(i).charAt(j+1))){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] ^ immediateValue;//bitwise XORI the contents of register 2 and immediate value
					registerValue[registerVal] = binaryVal;//store in first register 
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("ADDI")){
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] + immediateValue;//add content of register 2 with an immediate value
					registerValue[registerVal] = binaryVal;//store this result in the first register
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("SUBI")){
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){//as with the above instruction, parse the two registers and the immediate value
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] - immediateValue;//subtracts the register content of register 2 with an immediate value
					registerValue[registerVal] = binaryVal;//store this in first parsed register
					afterReg = false;//reset values again
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("ANDI")){
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] & immediateValue;//bitwise AND the contents of second parsed register with the parsed immediate value
					registerValue[registerVal] = binaryVal;//store this result into the first parsed register
					afterReg = false;//reset the values again
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("SRL")){//like dividing a value
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && Character.isDigit(listInstructions.get(i).charAt(j+1)) && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){//get the amount to shift the second register contents by
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] >>> immediateValue;//shift the contents of the second register to the right by the amount specified in the immediate value
					registerValue[registerVal] = binaryVal;//shift in zeros and store in first register
					afterReg = false;//reset values
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("SRA")){//similar to SRL but shift in sign bit
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false && Character.isDigit(listInstructions.get(i).charAt(j+1))){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] >> immediateValue;//shift to the right the second register by the amount specified by the immediate value
					registerValue[registerVal] = binaryVal;//shift in the sign bit and store the result into the first parsed register
					afterReg = false;
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				else if(instrName[i] == ("SLL")){//if an SLL instruction
					int immediateValue = 0;
					for(int j = 0; j < listInstructions.get(i).length(); j++){
						if(listInstructions.get(i).charAt(j) == 'R' && afterReg == false){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterReg = true;
							afterReg2 = true;
						}
						else if(afterReg2 == true && listInstructions.get(i).charAt(j) == 'R'){
							j++;
							while(Character.isDigit(listInstructions.get(i).charAt(j))){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							registerVal2 = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
							afterOff = true;
							afterReg2 = false;
						}
						else if(afterOff == true && (listInstructions.get(i).charAt(j) == '#')){
							j++;
							while((j != listInstructions.get(i).length()) && (Character.isDigit(listInstructions.get(i).charAt(j)) || listInstructions.get(i).charAt(j) == '-')){
								registerNum += listInstructions.get(i).charAt(j);
								j++;
							}
							immediateValue = Integer.parseInt(registerNum);
							j--;
							registerNum = "";
						}
					}
					
					int binaryVal = registerValue[registerVal2] << immediateValue;//shift the contents of the second register to the left by the amount specified by the immediate value
					registerValue[registerVal] = binaryVal;//shift in zeros and store in first parsed register. It is like multiplying a value
					afterReg = false;//reset values
					afterReg2 = false;
					afterOff = false;
					registerVal = 0;
					registerVal2 = 0;
				}
				
				for(int x = 0; x < registerValue.length; x++){//to print out contents of registers after each instruction
					if(x == 0){
						simWriter.print("R00:\t");
					}
					if((x % 8) == 0 && x == 8){//print a newline after every 8th register
						simWriter.println();
						simWriter.print("R08:\t");
					}
					else if((x % 8) == 0 && x == 16){//repeat for 16th
						simWriter.println();
						simWriter.print("R16:\t");
					}
					else if((x % 8) == 0 && x == 24){//repeat for 24th
						simWriter.println();
						simWriter.print("R24:\t");
					}
					
					if(x == 7 || x == 15 || x == 23 || x == 31){//at the end of a line, do not tab at the end
						simWriter.print(registerValue[x]);
					}
					else{
						simWriter.print(registerValue[x] + "\t");
					}
				}
					int endOfPC = 0;
					for(int y = 0; y < dataPC.length; y++){//this gets the ending address of the data section 
						if(dataPC[y] == (pcAddress - 4)){
							endOfPC = y;//stores the index of the last address in the dataPC array
							break;
						}
					}
					simWriter.println();
					simWriter.println();
					simWriter.println("Data");
					//above lets me know how far I should continue printing out values for the data
					for(int y = 0; y < (endOfPC + 1); y++){//prints out data section
						if(y == 0){
							simWriter.print(dataPC[y] + ":\t");
						}
						else if((y % 8) == 0){//if index is divisible by 8, then print a new line
							simWriter.println();
							simWriter.print(dataPC[y] + ":\t");
						}
						
						if((y + 1) % 8 == 0){
							simWriter.print(dataValue[y]);
						}
						else{
							simWriter.print(dataValue[y] + "\t");
						}
					}
				simWriter.println();
				simWriter.println();
				i++;//increment instruction index
				cycle++;//increment cyles
				//break;//for debugging only
			}
			simWriter.close();//close simulation file object
		}
		catch(Exception ex){//just some standard exception handling
			ex.printStackTrace();
		}
	userInput.close();//close last scanner object
	}//end of main
	
	public static int twoComplementConverter(String stringOfBits){//use this for jump instruction
		String convertedBits = "";
		int decimalValue = 0;
		
		if(stringOfBits.charAt(0) == '0'){//this function is just for the jump instruction, which deals with 26 bits and cannot be constrained to a particular data type for two's complement
			decimalValue = Integer.parseInt(stringOfBits, 2);//if an unsigned value, then just parse that as is
		}
		else if(stringOfBits.charAt(0) == '1'){//if a signed value, then two's complement must be performed on offset
			for(int i = 0; i < stringOfBits.length(); i++){
				if(stringOfBits.charAt(i) == '1'){//complements every bit
					convertedBits += '0';
				}
				else if(stringOfBits.charAt(i) == '0'){
					convertedBits += '1';
				}
			}
			
			decimalValue = Integer.parseInt(convertedBits, 2);//parse and convert the complemented bits
			decimalValue += 1;//add one to the above value
			decimalValue = decimalValue * -1;//multiply by -1 as this will be a negative value
		}
		return decimalValue;//return the result
	}//end of converter function
}//end of MIPSsim code
