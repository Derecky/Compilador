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
        //System.out.println(this.left.toString() + elements[1].trim().split(" ").toString());
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
	
	@Override
    public String toString() {
        String fmt = "          {%s} = {%s}";
        
        return String.format(fmt, this.left, this.right.toString());
	}

}
