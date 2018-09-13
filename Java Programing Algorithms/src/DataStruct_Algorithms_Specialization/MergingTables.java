import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

  class Table {
        private Table parent;
        int numberOfRows;
        int rank;

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
        }
        
        public static Table createInstance(int numberOfRows) 
        {
         Table instance = new Table(numberOfRows);
         instance.parent = instance;
         return instance;
        }
        
        void setParent(Table parent)
        {
            this.parent = parent;
        }
        
        Table getParent() 
        {
            // find super parent and compress path
            return parent;
        }
    }

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

    int maximumNumberOfRows = -1;

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        while(realDestination != realDestination.getParent())
        {
          realDestination = realDestination.getParent();
        }
        
        Table realSource = source.getParent();
        while(realSource != realSource.getParent())
        {
          realSource = realSource.getParent();
        }
        
        if (realDestination != realSource) 
        {
            if(realDestination.rank > realSource.rank)
            {
                realSource.setParent(realDestination);
                realDestination.numberOfRows = realDestination.numberOfRows +
                  realSource.numberOfRows;
                realSource.numberOfRows = 0;
                
                Table TempDestination = destination.getParent();
                while(realDestination != TempDestination)
                {
                  destination.setParent(realDestination);
                  destination = TempDestination;
                  TempDestination = TempDestination.getParent();
                }
                
                TempDestination = source.getParent();
                while(realDestination!= TempDestination)
                {
                  source.setParent(realDestination);
                  source = TempDestination;
                  TempDestination = TempDestination.getParent();
                }
            }
            else
            {
             realDestination.setParent(realSource);
             if(realDestination.rank == realSource.rank) {realSource.rank++;}
             realSource.numberOfRows = realDestination.numberOfRows +
                  realSource.numberOfRows;
                realDestination.numberOfRows = 0;
                
                Table TempDestination = destination.getParent();
                while(realSource != TempDestination)
                {
                  destination.setParent(realSource);
                  destination = TempDestination;
                  TempDestination = TempDestination.getParent();
                }
                
                TempDestination = source.getParent();
                while(realSource != TempDestination)
                {
                  source.setParent(realSource);
                  source = TempDestination;
                  TempDestination = TempDestination.getParent();
                }
            }
        }
        // merge two components here
        // use rank heuristic
        // update maximumNumberOfRows
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = Table.createInstance(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, tables[destination].getParent().numberOfRows);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
