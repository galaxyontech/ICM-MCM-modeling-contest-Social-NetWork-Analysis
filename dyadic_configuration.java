package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class dyadic_configuration {
	public static void main(String[] args) throws IOException{
		File input = new File("/Users/zeruiji/eclipse-workspace/CS Data structure/src/project3/passingevents.csv");
		Scanner indata = null;
		try {
			indata = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		indata.nextLine();
		try {
		      File myObj = new File("player_coordinates.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		      FileWriter myWriter = new FileWriter("player_coordinates.csv");
		   
		      System.out.println("Successfully wrote to the file.");
		     
		   
		      
		ArrayList<ArrayList<String>> tri_total = new ArrayList<ArrayList<String>>();
		while (indata.hasNextLine()) {
			
			//System.out.println(indata.nextLine());
			
			
			ArrayList<String> line = splitCSVLine(indata.nextLine());
			tri_total.add(line);
			
			
		}
		
		
		
		
		ArrayList<ArrayList<String>> result= new ArrayList<ArrayList<String>>();
		File input2 = new File("/Users/zeruiji/eclipse-workspace/CS Data structure/src/project3/passingevents.csv");
		Scanner indata2 = null;
		try {
			indata2 = new Scanner(input2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		indata2.nextLine();
		int count = 0;
		
		ArrayList<ArrayList<String>> total = new ArrayList<ArrayList<String>>();
		
		while ( indata2.hasNextLine() && count < tri_total.size() -2) {
			ArrayList<String> line = splitCSVLine(indata2.nextLine());
			if (line.get(1).equalsIgnoreCase("Huskies") && line.get(3).equalsIgnoreCase(tri_total.get(count +1 ).get(2)) && 
					line.get(2).equalsIgnoreCase(tri_total.get(count+1).get(3))) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(line.get(2));
				temp.add(line.get(3));

				Collections.sort(temp);
				total.add(temp);
			}
	count +=1;
		}
		Hashtable<ArrayList<String>, Integer> h = 
                new Hashtable<ArrayList<String>, Integer>();
		for (ArrayList<String> key: total) {
			if( h.containsKey(key)) {
				int value = h.get(key);
				value += 1;
				h.replace(key, value);
			}else {
				h.put(key, 1);
			}
		}
		Set<ArrayList<String>> set = h.keySet();
		ArrayList<Integer> in = new ArrayList<Integer>();
		
		
		
		for ( ArrayList<String>key : set) {
			in.add(h.get(key));
		}
		Collections.sort(in);
		
		
		for ( ArrayList<String>key : set) {
			System.out.println(key + ": \t" + h.get(key));
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	public static int search(String var, String[] a) {
		int result = 0;
		for ( int i = 0; i < a.length; i++) {
			if (a[i].equalsIgnoreCase(var)) {
				return i;
			}
		}
		return result;
	}
	
	
	public static ArrayList<String> splitCSVLine(String textLine){
		if (textLine == null ) return null;
			ArrayList<String> entries = new ArrayList<String>(); int lineLength = textLine.length();
			StringBuffer nextWord = new StringBuffer(); char nextChar;
			boolean insideQuotes = false; boolean insideEntry= false;
			// iterate over all characters in the textLine
			for (int i = 0; i < lineLength; i++) { 
				nextChar = textLine.charAt(i);
				// handle smart quotes as well as regular quotes
				if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
						// change insideQuotes flag when nextChar is a quote 
					if (insideQuotes) {
						insideQuotes = false; 
						insideEntry = false;
					}else {
						insideQuotes = true;
						insideEntry = true; 
						}
		} else if (Character.isWhitespace(nextChar)) { 
			if ( insideQuotes || insideEntry ) {
				// add it to the current entry
				  nextWord.append( nextChar );
		}else { // skip all spaces between entries 
			continue;
			}
		} else if ( nextChar == ',') {
		if (insideQuotes){ // comma inside an entry 
			nextWord.append(nextChar);
		} else { // end of entry found
			insideEntry = false; 
			entries.add(nextWord.toString());
			nextWord = new StringBuffer(); }
		} else {
		// add all other characters to the nextWord
		nextWord.append(nextChar); 
		insideEntry = true;
			}
		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list 
			if (!nextWord.toString().equals("")) {
				entries.add(nextWord.toString().trim());
			}
		return entries;
		}
	}