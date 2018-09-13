import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.start_time = arrival_time;
        this.process_time = process_time;
    }

    public int start_time;
    public int process_time;
    public Request rqn = null;
    public boolean dropped;
}

class Buffer {
    public Buffer(int size) {
        this.size = size;
        b = 0;
    }

    public void Process(Request request) {
        // write your code here
        if(rq == null){ rq = request; rt = request; b = 1;  return; }
 
            
          if(rt.start_time + rt.process_time <= request.start_time)  
          {
              rq = request;
              rt = request;
              b = 1;
              return;
          }
 
             if(rq.start_time + rq.process_time <= request.start_time)
             {
                rq = rq.rqn; b--;
             }
             
             if(b == size)
             {
                 request.dropped = true;
                 return;
             }

                request.start_time = rt.start_time + rt.process_time;
                rt.rqn = request;
                rt = request;
                b++;
    }

    private final int size;
    private Request rq = null;
    private Request rt = null;
    private int b;
}

class process_packages {
    private static Request[] ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
         Request[] requests = new Request[requests_count];
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests[i] = new Request(arrival_time, process_time);
        }
        return requests;
    }

    private static void ProcessRequests(Request[] requests, Buffer buffer) {

        for (int i = 0; i < requests.length; ++i) {
             buffer.Process(requests[i]);
       }
        
    }
    
    private static void PrintResponses(Request[] requests) {
        for (int i = 0; i < requests.length; ++i) {
            
            if (requests[i].dropped) {
                System.out.println(-1);
            } else {
                System.out.println(requests[i].start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        Request[] requests = ReadQueries(scanner);
        ProcessRequests(requests, buffer);
        PrintResponses(requests);
    }
}
