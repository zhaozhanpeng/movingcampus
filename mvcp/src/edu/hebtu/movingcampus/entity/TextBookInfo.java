/**
 * 
 */
package edu.hebtu.movingcampus.entity;

/**
 * @author leijie
 *	@aim 教科书的介绍
 */
public class TextBookInfo {
	private String  Name;//教材课程
	private String  Author;//教材课程
	private String  PublishHouse;//教材课程
	private String  Printing;//教材课程
	private String  Price;//教材课程
	private String  BookOrderId;//教材课程
	private String  BookId;//教材课程
	private String  GoodBook;//教材课程
	private String  PublishedTime;//教材课程
	//以下是getter 和setter 还有 tostring
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		Author = author;
	}
	/**
	 * @return the publishHouse
	 */
	public String getPublishHouse() {
		return PublishHouse;
	}
	/**
	 * @param publishHouse the publishHouse to set
	 */
	public void setPublishHouse(String publishHouse) {
		PublishHouse = publishHouse;
	}
	/**
	 * @return the printing
	 */
	public String getPrinting() {
		return Printing;
	}
	/**
	 * @param printing the printing to set
	 */
	public void setPrinting(String printing) {
		Printing = printing;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return Price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		Price = price;
	}
	/**
	 * @return the bookOrderId
	 */
	public String getBookOrderId() {
		return BookOrderId;
	}
	/**
	 * @param bookOrderId the bookOrderId to set
	 */
	public void setBookOrderId(String bookOrderId) {
		BookOrderId = bookOrderId;
	}
	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return BookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		BookId = bookId;
	}
	/**
	 * @return the goodBook
	 */
	public String getGoodBook() {
		return GoodBook;
	}
	/**
	 * @param goodBook the goodBook to set
	 */
	public void setGoodBook(String goodBook) {
		GoodBook = goodBook;
	}
	/**
	 * @return the publishedTime
	 */
	public String getPublishedTime() {
		return PublishedTime;
	}
	/**
	 * @param publishedTime the publishedTime to set
	 */
	public void setPublishedTime(String publishedTime) {
		PublishedTime = publishedTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TextBookInfo [Name=" + Name + ", Author=" + Author
				+ ", PublishHouse=" + PublishHouse + ", Printing=" + Printing
				+ ", Price=" + Price + ", BookOrderId=" + BookOrderId
				+ ", BookId=" + BookId + ", GoodBook=" + GoodBook
				+ ", PublishedTime=" + PublishedTime + "]";
	}
	
}
