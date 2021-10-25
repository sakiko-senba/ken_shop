package ken.bean;

import java.io.Serializable;

public class Item implements Serializable {
	private int itemID;
	private String itemName;
	private String itemArtist;
	private int itemPrice;
	private String itemImage;

	@Override
	public String toString() {
		return "Item[itemID=" + itemID + ", itemName="+ itemName
				+ ", itemArtist=" + itemArtist + ", itemPrice " + itemPrice
				+ ", itemImage=" + itemImage +"]";
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemArtist() {
		return itemArtist;
	}

	public void setItemArtist(String itemArtist) {
		this.itemArtist = itemArtist;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

}
