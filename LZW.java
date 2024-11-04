import java.io.*;
import java.util.*;

public class LZW {

    // Compresses the input string using the LZW algorithm
    public static List<Integer> compress(String input) {
        int idx = 256; // Initial index for new dictionary entries
        Map<String, Integer> mp = new HashMap<>(); // Dictionary to store substrings and their codes

        // Initialize the dictionary with single-character strings
        for (int i = 0; i < idx; ++i){
            mp.put(Character.toString((char) i), i);
        }

        String curr = ""; // Current sequence being checked
        List<Integer> compressed = new ArrayList<>(); // List to store compressed codes

        // Iterate through each character in the input string
        for(char c : input.toCharArray()){
            String tmp = curr + c; // Form a new sequence by adding the current character to `curr`
            if(mp.containsKey(tmp)) {
                // If the sequence exists in the dictionary, update `curr`
                curr = tmp;
            } else {
                // Otherwise, output the code for `curr`, add `tmp` to the dictionary, and reset `curr`
                compressed.add(mp.get(curr));
                mp.put(tmp, idx++);
                curr = "" + c;
            }
        }

        // Output the code for the final sequence
        if(!curr.isEmpty()) {
            compressed.add(mp.get(curr));
        }

        return compressed; // Return the compressed list of codes
    }

    // Decompresses a list of integer codes into the original string
    public static String decompress(List<Integer> compressed) {
        int idx = 256; // Initial index for new dictionary entries
        Map<Integer, String> mp = new HashMap<>(); // Dictionary to map codes back to strings

        // Initialize the dictionary with single-character strings
        for (int i = 0; i < idx; ++i){
            mp.put(i, Character.toString((char) i));
        }

        // Get the first code and initialize `prev` and `res` with the corresponding string
        String prev = "" + (char) (int) compressed.remove(0);
        StringBuilder res = new StringBuilder(prev);

        // Process each code in the compressed list
        for(int i : compressed){
            String tmp;
            if(mp.containsKey(i)) {
                // If the code exists in the dictionary, retrieve its corresponding string
                tmp = mp.get(i);
            } else if(i == idx) {
                // Special case for the current code being exactly the next index in the dictionary
                tmp = prev + prev.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid Code!\n"); // Error for an invalid code
            }

            // Append the decoded string to the result
            res.append(tmp);

            // Add a new sequence to the dictionary
            mp.put(idx++, prev + tmp.charAt(0));

            // Update `prev` for the next iteration
            prev = tmp;
        }
        
        return res.toString(); // Return the decompressed string
    }

    // Reads the contents of a file into a single string
    public static String readFile(String filename) throws IOException {
        StringBuilder res = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String tmp;
            while((tmp = br.readLine()) != null) {
                res.append(tmp).append("\n"); // Append each line to `res`
            }
        }
        return res.toString();
    }

    // Writes a list of integer codes to a file, each code on a new line
    public static void writeCompressedFile(List<Integer> compressed, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for(int i : compressed) {
                bw.write(i + "\n"); // Write each code followed by a newline
            }
        }
    }

    // Reads a list of integer codes from a file, assuming one code per line
    public static List<Integer> readCompressedFile(String filename) throws IOException {
        List<Integer> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String tmp;
            while((tmp = br.readLine()) != null) {
                res.add(Integer.parseInt(tmp.trim())); // Parse and add each line as an integer
            }
        }
        return res;
    }

    // Writes a string to a file
    public static void writeFile(String filename, String d) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(d); // Write the entire string `d` to the file
        }
    }

    public static void main(String[] args) {
        try {
            // Step 1: Read the input from "input.txt"
            String tmp = readFile("input.txt");

            // Step 2: Compress the input string
            List<Integer> ls = compress(tmp);

            // Step 3: Write the compressed data to "compressed.txt"
            writeCompressedFile(ls, "compressed.txt");

            // Step 4: Decompress the compressed data
            String decompressed = decompress(ls);

            // Step 5: Write the decompressed output to "decompressed.txt"
            writeFile("decompressed.txt", decompressed);
        } catch (Exception e) {
            e.printStackTrace(); // Print any exceptions that occur
        }
    }
}
