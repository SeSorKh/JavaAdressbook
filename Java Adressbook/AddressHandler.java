import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import javax.swing.*;
public class AddressHandler {
  private ArrayList<Data> addresses;
  private String activeFile;
  // Constructor
  public AddressHandler() {
    addresses = new ArrayList<Data>();

    activeFile = "";
  }
  // Adds Data d to address book
  public void addAddress(Data d) {
    addresses.add(d);
  }
  //Retrieves data that matches string s
  Data retrieveAddress(String s){
      Data d = null;
    int size = addresses.size();
      for(int i = 0; i < size; i++){
          if(addresses.get(i).formatData().equals(s)){
              d = addresses.get(i);
              break;
          }
      }
      return d;
  }
  //Removes an address from addresses
  public void removeAddress(Data d){
      if(d != null){
          addresses.remove(d);
      }
  }
  //Returns the array list
  public ArrayList<Data> getArrayList() {
    return addresses;
  }
  //Return the size of the list
  public int size(){
      return addresses.size();
  }
  //Return element at index i
  Data getElement(int i){
      return addresses.get(i);
  }
  // Sorts address book by Name
  public void sortName(){
      addresses.sort(new NameComparator());
  }
  // Custom Comparators
  // Last Name Comparator
  static class NameComparator implements Comparator<Data>{
    @Override
    public int compare(Data d1, Data d2) {
        int val = d1.getLastName().compareTo(d2.getLastName());
        if(val == 0){
            val = d1.getFirstName().compareTo(d2.getFirstName());
        }
        return val;
    }
  }
  /* File menu options */
  public void newAddressBook() {
    addresses.clear();
    activeFile = "";
  }
  public void openAddresses(String path) {
    // JFileChooser fileChooser = new JFileChooser();
    // fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    // if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    //   File file = fileChooser.getSelectedFile();
    //   // TODO: Check if file is correct format?
    //   addresses.clear();
    //   parseOpenedFile(file);
    // }
    activeFile = path;
    File file = new File(activeFile);
    parseOpenedFile(file);
  }
  public void saveAddresses() {
    if (activeFile == "") {
      // Prompt for file name to save address
      saveAddressesAs();
    } else {
      System.out.println(activeFile);
      File file = new File(activeFile);
      if (file == null) {
        // The active file was deleted
        saveAddressesAs();
        return;
      }
      PrintWriter pw = null;
      try {
         FileWriter fw = new FileWriter(file);
         pw = new PrintWriter(fw);
         pw.println(formatAddressesForFile());
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (pw != null) {
          pw.close();
         }
      }
    }
  }
  public void saveAddressesAs() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      PrintWriter pw = null;
      try {
         FileWriter fw = new FileWriter(file);
         pw = new PrintWriter(fw);
         pw.println(formatAddressesForFile());
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (pw != null) {
          File f1 = file.getAbsoluteFile();
          activeFile = f1.getAbsolutePath();
          pw.close();
         }
      }
    }
  }
  /* End file menu options */
  /* Helper functions */
  // Format a string of all files to be saved in a .txt file
  private String formatAddressesForFile() {
    String s = "";
    for (int i = 0; i < addresses.size(); i++) {
      s += addresses.get(i).formatDataForFile();
    }
    return s;
  }
  // Parse a saved address book and add each address to the address book
  private void parseOpenedFile(File file) {
    try {
      Scanner scan = new Scanner(file);
      String line = "";
      Data data;
      while (scan.hasNext()) {
        line = scan.nextLine();
        data = readAddress(line);
        addresses.add(data);
      }
      scan.close();
    } catch (Exception e) {
    }
  }
  // Read address in saved file format and create a Data object
  private Data readAddress(String line) {
    //System.out.println(line);
    String[] array = line.split(":");
    //System.out.println(array[6]);
    Data d = new Data(array[1], array[0], array[2], array[3], array[4], array[5], array[6], array[7], array[8],array[9],array[10]);
    return d;
  }
}