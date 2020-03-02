package slr;

public class ProductionRules {
	private int size;
    private String left;
    private String[] right;
    
    
	public ProductionRules(String production) {
		String[] elements = production.split("=");
        this.left = elements[0].trim();
        this.right = elements[1].trim().split(" ");
        this.size = right.length;
	}


	public int getSize() {
		return size;
	}


	public String getLeft() {
		return left;
	}


	public String[] getRight() {
		return right;
	}

}
