import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class check_brackets {
    
    static boolean Match(char a, char c) {
        if (a == '[' && c == ']')
            return true;
        if (a == '{' && c == '}')
            return true;
        if (a == '(' && c == ')')
            return true;
        return false;
    }
    

    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine(); boolean match = true;
        Stack<Integer> obp = new Stack<Integer>();
        Stack<Character> opening_brackets_stack = new Stack<Character>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(next);
                obp.push(position+1);
                match = false;
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if(opening_brackets_stack.empty())
                {
                    match = false;
                    obp.push(position+1);
                    break;
                }
                else
                {
                    if(!Match(opening_brackets_stack.pop(), next))
                    {
                        obp.push(position+1);
                        break;
                    }
                    else
                    {
                        match = opening_brackets_stack.empty();
                        obp.pop();
                    }
                }
            }
        }
        // Printing answer, write your code here
        if(match)
            System.out.println("Success");
        else
            System.out.println(obp.peek());
    }
}
