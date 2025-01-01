package My;

	public class WildCard extends Card {
	    private final Value type;

	    public WildCard(Value type) {
	        super(CardColor.WILD);
	        this.type = type;
	    }

	    @Override
	    public String getValue() {
	        return type.toString();
	    }

	    @Override
	    public String toString() {
	        return type.toString();
	    }
	}


