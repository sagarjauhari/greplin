package challenge1;



import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class LongestCommonSubseq {
    String       PROB_NAME  = "";
    String       TEST_NAME = "input00.txt";
    InputScanner i_scan;
    OutputWriter o_writ;
    Boolean      is_debug   = false;
    long         start_time = 0;
    long         end_time   = 0;

    public static void main(String[] args) {
        LongestCommonSubseq program = new LongestCommonSubseq(args);
        program.begin();
        program.end();
    }

    public void begin() {
        String s  = i_scan.next();
        String t = reverseString(s);

        o_writ.printLine(longestCommonSubsequenceTweeked(s,t));
       
    }
    /**
     * Find the longest common subsequence (LCS) between 2 strings using dynamic programming
     * @param s First string
     * @param t Second string
     * @return the common subsequence
     */
    String longestCommonSubsequence(String s, String t) {
    	int len1 = s.length();
    	int len2 = t.length();
    	
    	int[][] array = new int[len1][len2];
    	int maxSize = 0; //size of LCSso far
    	int maxIdx = 0;  //end index of LCS so far in string s
    	
    	for(int i = 0; i < len1; i++){
    		for (int j = 0; j < len2; j++){
    			if(s.charAt(i)==t.charAt(j)){
    				if (i==0 || j==0) array[i][j]=1;
    				else array[i][j] = array[i-1][j-1] + 1;
    				
    				if(array[i][j] > maxSize){
    					maxSize = array[i][j];
    					maxIdx = i;
    				}
    			}else{
    				array[i][j] = 0;
    			}
    		}
    	}
    	
    	return s.substring(maxIdx + 1 - maxSize, maxIdx + 1);
	}

    
    /**
     * Find the longest common subsequence (LCS) between 2 strings. Uses the
     * tweeked version - only those LCS are considered who distances from the end
     * of 't' is equal to distance from start of 's'. 
     * @param s First string
     * @param t Second string
     * @return the common subsequence
     */
    String longestCommonSubsequenceTweeked(String s, String t) {
    	int len1 = s.length();
    	int len2 = t.length();
    	
    	int[][] array = new int[len1][len2];
    	int maxSize = 0; //size of LCSso far
    	int maxIdx = 0;  //end index of LCS so far in string s
    	
    	for(int i = 0; i < len1; i++){
    		for (int j = 0; j < len2; j++){
    			if(s.charAt(i)==t.charAt(j)){
    				if (i==0 || j==0) array[i][j]=1;
    				else array[i][j] = array[i-1][j-1] + 1;
    				
    				if(array[i][j] > maxSize && (len2-j) == (i-array[i][j] +2)){
    					maxSize = array[i][j];
    					maxIdx = i;
    				}
    			}else{
    				array[i][j] = 0;
    			}
    		}
    	}
    	
    	return s.substring(maxIdx + 1 - maxSize, maxIdx + 1);
	}

	String reverseString(String s){
    	StringBuilder sb = new StringBuilder();
    	for(int i = s.length()-1; i >=0; i--){
    		sb.append(s.charAt(i));
    	}
    	return sb.toString();
    }

    public LongestCommonSubseq(String[] args) {
        if (args.length > 0 && args[0].equals("Test")) {
            i_scan = new InputScanner(new File(
                    "/home/sagar/code_arena/Eclipse Workspace/InterviewStr/inputs/"
                            + PROB_NAME + "/"+TEST_NAME));
        } else {
            i_scan = new InputScanner(System.in);
        }
        o_writ = new OutputWriter(System.out);
        if (is_debug) {
            start_time = System.currentTimeMillis();
        }
    }

    public void end() {
        if (is_debug) {
            end_time = System.currentTimeMillis();
            o_writ.printLine("Time (milisec): " + (end_time - start_time));
        }
        o_writ.close();
    }

    class InputScanner {
        private final BufferedReader br;
        StringTokenizer              tokenizer;

        public InputScanner(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }

        public InputScanner(File file) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            int num;
            try {
                num = Integer.parseInt(next());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            return num;
        }
        
        public float nextFloat(){
            float num;
            try{
                num = Float.parseFloat(next());
            }catch(NumberFormatException e){
                throw new RuntimeException(e);
            }
            return num;
        }
        
        public float nextLong(){
            long num;
            try{
                num = Long.parseLong(next());
            }catch(NumberFormatException e){
                throw new RuntimeException(e);
            }
            return num;
        }

        public String nextLine() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken("\n").trim();
        }

    }

    class OutputWriter {
        private final PrintWriter pw;

        public OutputWriter(OutputStream op) {
            pw = new PrintWriter(op);
        }

        public OutputWriter(Writer writer) {
            pw = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    pw.print(' ');
                pw.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            pw.println();
        }

        public void close() {
            pw.close();
        }
    }
}
