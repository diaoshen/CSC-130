package heap;

import util.SortUtil;

public class MaxPQ<Key extends Comparable<Key>> {

//This Version implement MaxPQ as BinaryHeap
	
	
/*
 * Supported Operations : 
 * 
 * boolean isEmpty()  // Returns IsTreeEmpty? True : False
 * int size() // Returns # of tree nodes
 * void push(Key v) // Add tree node 
 * Key pop() // Returns root node
 * 
 */

	private Key pq[]; // heap ordered complete binary tree
	private int size = 0; // in pq[1..N] with pq[0] unused
	
	@SuppressWarnings("unchecked")
	public MaxPQ(int maxSize) {
		pq = (Key[]) new Comparable[maxSize+1];
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	public void push(Key v) {
		pq[++size] = v;
		swim(size);
	}
	public Key pop() {
		Key max = pq[1]; //Retrieve root
		SortUtil.swap(pq, 1, size); //replace root with last element
		pq[size] = null; //Unlink last element
		size--; // decrease tree size by 1
		sink(1); //Restore max heap
		return max;
	}
	
	
	//Privates
	private void swim(int k ) {
		//As long as parent exists AND parent < me then swap
		while(k > 1 && SortUtil.isLessThan(pq[k/2], pq[k])) {
			SortUtil.swap(pq, k/2, k);
			k = k/2;
		}
	}
	private void sink(int parent ) {
		//As long as there is child AND child is > me then swap 
		while(2*parent <= size) {
			//Check if there is a 2 CHILD , 
			int child = 2*parent;
			//If child == size then there is no 2 CHILD. 
			//1st condition checks if I'm the 1st child
			//2nd condition checks if I'm the smaller one
			if(child < size && SortUtil.isLessThan(pq[child], pq[child+1])) {
				child++;
			}
			//Parent > Child , Do nothing
			if(!SortUtil.isLessThan(pq[parent], pq[child]))
				break;
			//Swap and repeat if necessary
			SortUtil.swap(pq, parent, child);
			parent = child;
		}		
	}

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
