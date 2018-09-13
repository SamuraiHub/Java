import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private List<Contact>[] contacts;

    public static void main(String[] args) 
    {
        PhoneBook a = new PhoneBook();
          
        a.contacts = new List[430108];
        
        for(int i = 0; i < a.contacts.length; i++)
        {
           a.contacts[i] = new ArrayList<>(24);
        }
                
        a.processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }
    
    private int hashCode(int num) 
    {
        return (int) (((5876958L*num+8564) % 13589753)%430108);
    }

    private void processQuery(Query query) {
        int nHash = hashCode(query.number);
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name          
            boolean wasFound = false;

            for (int i = 0; i < contacts[nHash].size() && contacts[nHash].get(i) != null; ++i)
                if (contacts[nHash].get(i).number == query.number) {
                    contacts[nHash].get(i).name = query.name;
                    wasFound = true;
                    break;
                }
            // otherwise, just add it
            if (!wasFound)
                contacts[nHash].add(new Contact(query.name, query.number));
        } else if (query.type.equals("del")) {
            for (int i = 0; i < contacts[nHash].size() && contacts[nHash].get(i) != null; ++i)
            {
                if (contacts[nHash].get(i).number == query.number) {
                    contacts[nHash].remove(i);
                    break;
                }
            }
        } else {
            String response = "not found";
            for (int i = 0; i < contacts[nHash].size() && contacts[nHash].get(i) != null; ++i)
                if (contacts[nHash].get(i).number == query.number) {
                    response = contacts[nHash].get(i).name;
                    break;
                }
            writeResponse(response);
        }
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
