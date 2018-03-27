package models;

import com.google.gson.annotations.SerializedName;

/**
 * 	Project's URL: https://github.com/talhaqamar/Android-Test
 * 	Created By: Talha Qamar on 25 March 2018
 * 	Author's Email: talhaaus@gmail.com
 */

/**
 * This class is the model for the data fetched from the api
 */
public class FactsVO {
   @SerializedName("title")
   String title;
   @SerializedName("description")
   String description;
   @SerializedName("imageHref")
   String imageHref;


   /**
	*  Default Constructor
	*/
   public FactsVO() {
   }

   /**
	*
	* @param title
	* @param description
	* @param imageHref
	*/
   public FactsVO(String title, String description, String imageHref) {
	  this.title = title;
	  this.description = description;
	  this.imageHref = imageHref;
   }

   /**
	* Setter getters
	*/
   public String getTitle() {
	  return title;
   }

   public void setTitle(String title) {
	  this.title = title;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getImageHref() {
	  return imageHref;
   }

   public void setImageHref(String imageHref) {
	  this.imageHref = imageHref;
   }

   @Override
   public String toString() {
	  return String.format("title:%s,description:%s,imageHref:%s", title, description, imageHref);
   }

}
