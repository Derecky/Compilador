package slr;

import java.io.*;
import java.util.*;

public class SLRTable {
    private Map<String, Integer> tableHeader;
    private String[][] tableContent;
    private int tableContentNumLines;
    private int tableContentNumColumns;

    public SLRTable(String path) {
        try {
            File csvFile = new File(path);
            long archiveSize = csvFile.length();
            FileInputStream csvFileStream = new FileInputStream(path);
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(csvFileStream));
            
            //System.out.println( csvFile.length());
            
            
            lineNumberReader.skip(archiveSize);
            this.tableContentNumLines = lineNumberReader.getLineNumber() - 1;
            int lineCounter = 0;

            String line;

            BufferedReader buffer = new BufferedReader(new FileReader(csvFile));

            tableHeader = new HashMap();

            line = buffer.readLine();
            //System.out.println(line);
            for (String column : line.split(",")) {
                this.tableHeader.put(column.replace("'", ""), lineCounter);
                lineCounter++;
            }
            //System.out.println(tableHeader);
            this.tableContentNumColumns = lineCounter;
            tableContent = new String[this.tableContentNumLines][this.tableContentNumColumns];
            lineCounter = 0;
            //System.out.println("A  " + this.tableContentNumLines +" N  " + this.tableContentNumColumns);
            line =  buffer.readLine();
            //System.out.println(tableHeader.size());
            while (line != null) {
                int columnCounter = 0;
                StringBuilder element = new StringBuilder();
                
                for (char c : line.toCharArray()) {
                	//System.out.println(c);
                    if (c == ',' ) {
                    	//System.out.println(lineCounter +  columnCounter);
                        tableContent[lineCounter][columnCounter] = element.length() == 0 ? null : element.toString();
                        element.setLength(0);
                        columnCounter++;
                    } else if (c != '\'') {
                    	//System.out.println("PS" );
                        element.append(c);
                    }
                }
                
                tableContent[lineCounter][columnCounter] = element.length() == 0 ? null : element.toString();

                lineCounter++;
                line =  buffer.readLine();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Falha ao ler o arquivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getTableHeader() {
        return tableHeader;
    }

    public String[][] getTableContent() {
        return tableContent;
    }

    public int getTableContentNumLines() {
        return tableContentNumLines;
    }

    public int getTableContentNumColumns() {
        return tableContentNumColumns;
    }
}
