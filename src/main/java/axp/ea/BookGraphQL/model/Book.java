package axp.ea.BookGraphQL.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mjafary
 *
 */

@Table
@Entity
public class Book {
	
	@Id
	private String isn;
	private String title;
	private String publisher;
	private String[] authors;
	private String publishedDate;
	
	
	
	/**
	 * 
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isn
	 * @param title
	 * @param publisher
	 * @param authors
	 * @param publishedDate
	 */
	public Book(String isn, String title, String publisher, String[] authors,
			String publishedDate) {
		super();
		this.isn = isn;
		this.title = title;
		this.publisher = publisher;
		this.authors = authors;
		this.publishedDate = publishedDate;
	}

	/**
	 * @return the isn
	 */
	public String getIsn() {
		return isn;
	}

	/**
	 * @param isn the isn to set
	 */
	public void setIsn(String isn) {
		this.isn = isn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the authors
	 */
	public String[] getAuthors() {
		return authors;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	/**
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	

}
