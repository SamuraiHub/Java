import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

 class Job{
    
    public Job(int thread, long startTime, long finish_time) {
        this.thread = thread;
        this.startTime = startTime;
        this.finish_time = finish_time;
    }

    public int thread;
    public long startTime;
    public long finish_time;
}

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private Job[] assignedWorker;

    private FastScanner in;
    private PrintWriter out;
    
    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }
    
public class minHeap {

	public Job [] mH;
	public int position;
	public minHeap(int size){

		mH = new Job [size+1];
		position = 1;
	}

	public void insert(Job x)
        {
	        int pos = position;
		while(pos>1 && (mH[pos/2].finish_time > x.finish_time || 
                        (mH[pos/2].finish_time == x.finish_time && mH[pos/2].thread > x.thread)))
                {
			mH[pos]=mH[pos/2];
			pos = pos/2;
		}
                mH[pos] = x;
                position++;
	}
	
	public Job extractMin()
        {
		Job min = mH[1];
		position--;
			
                int k = 1, smallest = 2;
                while(smallest < position)
                {
		   if(smallest+1<position && (mH[smallest].finish_time>mH[smallest+1].finish_time || 
                        (mH[smallest+1].finish_time == mH[smallest].finish_time && mH[smallest].thread > mH[smallest+1].thread)))
                   {
                       smallest++;
		   }
		   if(mH[position].finish_time>mH[smallest].finish_time || 
                        (mH[position].finish_time == mH[smallest].finish_time && mH[position].thread > mH[smallest].thread))
                   {
                     mH[k] = mH[smallest];
                     k = smallest;
                     smallest *=2;
		   }
                   else
                       break;
                }
                mH[k] = mH[position];
                mH[position]=null;
		return min;
	}
        
}	
    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }
    
    

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i].thread + " " + assignedWorker[i].startTime);
        }
    }

    private void assignJobs() 
    {
        // TODO: replace this code with a faster algorithm.

        assignedWorker = new Job[jobs.length];
        minHeap nextFreeTime = new minHeap(jobs.length);
        
        for (int j = 0; j < numWorkers && j <jobs.length; ++j) 
        {
          assignedWorker[j] = new Job(j, 0, jobs[j]);  
          nextFreeTime.insert(assignedWorker[j]);
        }
        
        for (int i = numWorkers; i < jobs.length; i++) 
        {
          Job J = nextFreeTime.extractMin();
          assignedWorker[i] = new Job(J.thread,J.finish_time,J.finish_time+jobs[i]);
          nextFreeTime.insert(assignedWorker[i]);
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
