package engine.model.components.concrete;

public class PurchasableComponentData {
	private int myPurchaseValue;
	
	public PurchasableComponentData()
	{
	}
	
	public int getBuyPrice()
	{
		return myPurchaseValue;
	}
	
	public void setBuyPrice(int aPrice)
	{
		myPurchaseValue = aPrice;
	}
}
