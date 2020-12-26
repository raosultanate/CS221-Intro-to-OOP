import java.awt.List;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board as
 * read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to a
 * GUI according to options specified via command-line arguments.
 * 
 * @author danielrao
 */
public class CircuitTracer {
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	

	/**
	 * launch the program
	 * 
	 * @param args
	 *            three required arguments: first arg: -s for stack or -q for queue
	 *            second arg: -c for console output or -g for GUI output third arg:
	 *            input file name
	 */
	public static void main(String[] args) {
		
		//if there are more/less than 3 arguments in the command line provided by the user.
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); // create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		
		// any command line arguments error
		System.err.print(" Usage:Java CircuitTracer -s|-q -c|-g Filename");
	}

	/**
	 * Set up the CircuitBoard and all other components based on command line
	 * arguments.
	 * 
	 * @param args
	 *            command line arguments passed through from main()
	 * @throws FileNotFoundException
	 */
	private CircuitTracer(String[] args) throws FileNotFoundException {
	
		// parsing command line args
		
		String storage = args[0]; 			// first argument for stack or -queue
		String mode = args[1]; 				// second argument for -console or -GUI
		String filePath = args[2]; 			// third argument for filename

		
		// TODO: initialize the Storage to use either a stack or queue, other than than it will print out an error message about how to use the command line arguments.
		if (!storage.equals("-s") && !storage.equals("-q") ) {
			printUsage();
		} 
		//graphic user interface is not implemented so this message will be printed.
		if(mode.contentEquals("-g")) {
			System.out.println(" GUI not Implemented");
		}
		
		//if the user mode is other than -c than again the error message will be printed out on the console.
		if(!mode.equals("-c")) {
			printUsage();
		}
		
		//if user plans to use a queue than the stateStore will use queue implementation of the data structure to store all the trace states.
		if (storage.equals("-q")) {
			stateStore = new Storage<TraceState>(Storage.DataStructure.queue);
		} 
		//if user plans to use a stack than the stateStore will use stack implementation of the data structure to store all the trace states.
		else if (storage.equals("-s")) {
			stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
		}
		else {
			
			throw new FileNotFoundException(); //if the  user provides other parameters than "-s" or "-q" than an exception will be thrown.
		}

	
		// reading in circuit board from the given file
		try {		board = new CircuitBoard(filePath);
		} catch (FileNotFoundException E) {
			System.exit(1); //if the file is not found than FileNotFoundException() is thrown.
		}

	
		// running the search for most effective paths available
		//best paths will contain all the best paths.
		ArrayList<TraceState> bestPaths = new ArrayList<TraceState>();

		Point startPoint = board.getStartingPoint();
		Point endPoint = board.getEndingPoint();
		int xDiff = endPoint.x - startPoint.x;
		int yDiff = endPoint.y-startPoint.y ;

		TraceState movePath;
		if (board.isOpen(startPoint.x - 1, startPoint.y)) { 			// bottom trace state
			movePath = new TraceState(board, startPoint.x - 1, startPoint.y);
			stateStore.store(movePath);
		}
		if (board.isOpen(startPoint.x + 1, startPoint.y)) { 			// top trace state
			movePath = new TraceState(board, startPoint.x + 1, startPoint.y);
			stateStore.store(movePath);

		}
		if (board.isOpen(startPoint.x, startPoint.y + 1)) { 			// right trace state
			movePath = new TraceState(board, startPoint.x, startPoint.y + 1);
			stateStore.store(movePath);

		}
		if (board.isOpen(startPoint.x, startPoint.y - 1)) { 			// left trace state
			movePath = new TraceState(board, startPoint.x, startPoint.y - 1);
			stateStore.store(movePath);

		}
		//while stateStore is not empty the object keeps getting retrieved from the stack.
		while (!stateStore.isEmpty()) {
			TraceState obj = stateStore.retrieve();

			
			//checks if the object retrieved from the stateStore has a complete path.
			if (obj.isComplete() == true) {
				if (bestPaths.isEmpty()) { //checks if bestPaths is empty,
					bestPaths.add(obj); //if so than the object gets added to the bestPaths arrayList.
				} else if (obj.pathLength() == bestPaths.get(0).pathLength()) { //checks if the object paths length retrieved is equal to the one already present in the bestPath arrayList.
					bestPaths.add(obj); //if so than the object is added to the arrayList of bestPaths.
				} else if (obj.pathLength() < bestPaths.get(0).pathLength()) { //checks if the pathLength of the object retrieved from the stateStore is smaller than the one already present in the bestPaths arrayList.
					bestPaths.clear(); //if so gets rid of all the other previous best paths/
					bestPaths.add(obj); //and than finally adds the object to the bestPaths of arrayList.
				}
				
				//if the object 
			} else {
				if(obj.isOpen(obj.getRow()-1, obj.getCol())) { //checks if there is a valid open spot to the bottom of the row but same column
					stateStore.store(new TraceState(obj,obj.getRow()-1, obj.getCol())); //if so than new and updated object gets added to the stack of StateStore
				}
				if(obj.isOpen(obj.getRow()+1, obj.getCol())) {//checks if there is a valid open spot to the top of the row but same column
					stateStore.store(new TraceState(obj,obj.getRow()+1, obj.getCol())); //if so than new and updated object gets added to the stack of StateStore
				}
				if(obj.isOpen(obj.getRow(), obj.getCol()-1)) { //checks if there is a valid open spot to the left but same row.
					stateStore.store(new TraceState(obj,obj.getRow(), obj.getCol()-1)); //if so than new and updated object gets added to the stack of StateStore
				}
				if(obj.isOpen(obj.getRow(), obj.getCol()+1)) { //checks if there is a valid open spot to thev right but same column.
					stateStore.store(new TraceState(obj,obj.getRow(), obj.getCol()+1)); //if so than new and updated object gets added to the stack of StateStore
				}

		}

	
	}//exiting the while loop.
		
		// printing results through the use of toString.
		if (mode.equals("-c")) {
			if(storage.equals("-s")) {
			for (int i = 0; i < bestPaths.size(); i++) {
				System.out.println(bestPaths.get(i).toString());
			}
		} else if(storage.equals("-q"))
				{
			for (int i = bestPaths.size()-1; i >= 0; i--) {
				System.out.println(bestPaths.get(i).toString());
			}
				}
			else {
				}
			System.out.println("GUI is Not implemented");
		}
		
		

	}}
