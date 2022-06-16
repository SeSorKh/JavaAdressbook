import java.util.Comparator;
import java.util.ArrayList;
public class Data {
  private String firstName;
  private String lastName;
  private String middleName;
  private String address;
  private String addressTwo;
  private String city;
  private String state;
  private String zipCode;
  private String country;
  private String phoneNumber;
  private String email;
  public Data(String f, String l,String m, String a, String a2, String ci, String s, String z,String co, String p, String e) {
    firstName = f;
    lastName = l;
    middleName = m;
    address = a;
    addressTwo = a2;
    city = ci;
    state = s;
    zipCode = z;
    country = co;
    phoneNumber = p;
    email = e;
  }
  public String formatData() {

   
    return lastName + "," + firstName +"                                               ";
  }
  public String formatDataForFile() {
    String s = lastName + ":" + firstName + ":" + middleName + ":"+ address + ":" + addressTwo + ":" + city + ":" + state + ":" + zipCode + ":"+ country+ ":" + phoneNumber + ":" + email + ":end\r\n";
    return s;
  }
  // Public get functions
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getMiddleName() {
	  return middleName;
  }
  public String getAddress() {
    return address;
  }
  public String getAddressTwo() {
    return addressTwo;
  }
  public String getCity() {
    return city;
  }
  public String getState() {
    return state;
  }
  public String getZipCode() {
    return zipCode;
  }
  public String getCountry() {
	  return country;
  }
  public String getPhoneNumber(){
      return phoneNumber;
  }
  public String getEmail(){
      return email;
  }
  //Public set functions
  public void setFirstName(String f) {
    firstName = f;
}
  public void setLastName(String l) {
    lastName = l;
  }
  public void setmiddleName(String m) {
	  middleName = m;
  }
  public void setAddress(String a) {
    address = a;
  }
  public void setAddressTwo(String a) {
    addressTwo = a;
  }
  public void setCity(String c) {
    city = c;
  }
  public void setState(String s) {
    state = s;
  }
  public void setZipCode(String z) {
    zipCode = z;
  }
  public void setcountry(String co) {
	  country = co;
  }
  public void setPhoneNumber(String p){
    phoneNumber = p;
  }
  public void setEmail(String e){
    email = e;
  }
}