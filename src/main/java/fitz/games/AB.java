package fitz.games;


//N =2-50  K=0-(N(N-1)/2) incl.
public class AB {
    public String createString(int n, int k) {
        //string[i]='A' String[j]='B' j>i
        //EX  n=10 k=12  "BAABBABAAB";
    	String returnString = new String();
    	
    	
    	ABTree root = new ABTree(null, A_B_VALUE.B);
    	ABTree currChild = root;
    	for(int i=1; i<n; i++) {
    		// create whole line as B to start??
    		currChild = currChild.createChild(A_B_VALUE.B);
    	}
    	
    	
    	currChild = root;
    	while (root.getABTreeValue() != k && currChild != null) {
    		//change value to A to test tree value
    		currChild.setValue(A_B_VALUE.A);
    		
    		//if this 'A' makes the count too high switch it back to B
    		if(root.getABTreeValue() > k) {
    			currChild.setValue(A_B_VALUE.B);
    		}
    		currChild = currChild.getChild();
    	}
    	
    	
    	// if found string create the string
    	if(root.getABTreeValue() == k) {
    		currChild = root;
    		for(int i=0; i<n; i++) {
    			if(currChild.getValue() == A_B_VALUE.A) {
    				returnString = returnString + "A";
    			} else {
    				returnString = returnString + "B";
    			}
    			currChild = currChild.getChild();
    		}
    	}
    	//if the max value of arrays after this one > k set to B
    	//else set to A and continue

    	return returnString;
    }
    

	public static enum A_B_VALUE {
		A, B
	}
	
    public class ABTree {

    	private int bsFromHere;
    	
    	private int abTreeValue;
    	private A_B_VALUE value;
    	private ABTree parent;
    	private ABTree child;
    	
    	
    	
       	public ABTree(ABTree parent, A_B_VALUE initValue) {
    		this.parent = parent;
    		//Tree value from here out is blank until a child is added
    		this.abTreeValue = 0;
    		
    		this.value = initValue;
    		bsFromHere = 0;
    		if(value == A_B_VALUE.B) {
    			this.addBsFromHere();
    		}
    	}

		public int getABTreeValue() {
			return abTreeValue;
		}
    	
    	public void setValue(A_B_VALUE value) {
    		if(this.value != value) {
    			this.value = value;
    			//if switching to A
    			if(value == A_B_VALUE.B) {
    				addBsFromHere();
    				calculateTreeValue();
    			} else {
    				subtractBsFromHere();
    				calculateTreeValue();
    			}
    		}
    	}

		private void addBsFromHere() {
			this.bsFromHere++;
			if(parent != null) {
				parent.addBsFromHere();
			}
		}

		public ABTree createChild(A_B_VALUE initValue) {
    		child = new ABTree(this, initValue);
    		this.calculateTreeValue();
    		return child;
    	}
    	
		private void subtractBsFromHere() {
			this.bsFromHere--;
			if(parent != null) {
				parent.subtractBsFromHere();	
			}
		}
		
		// if a value is A add all bs after it and call its parent
    	private void calculateTreeValue() {
			if(value == A_B_VALUE.A) {
				abTreeValue = bsFromHere;
				if(child != null) {
					abTreeValue += child.getABTreeValue();
				}
			} else if(child != null) {
				abTreeValue = child.getABTreeValue();
			} else {
				abTreeValue = 0;
			}
			if(parent != null) {
				parent.calculateTreeValue();
			}
		}

		private void addToBsAfterThis() {
			if(parent != null) {
				parent.addToBsAfterThis();
			}
			if(this.value == A_B_VALUE.A) {
				this.abTreeValue++;
			}
		}
    	
    	public A_B_VALUE getValue() {
    		return value;
    	}
    	
    	public ABTree getChild() {
    		return child;
    	}
    }
}

