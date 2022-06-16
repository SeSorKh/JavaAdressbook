import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class BookFrame extends JFrame {
  private JLabel errorLabel;
  private AddressHandler addresses;
  private DefaultListModel<String> model;
  private int index;
  public BookFrame(String path) {
        //Create and set up the window.
        super("Address Book");
        this.setPreferredSize(new Dimension(700, 400));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        addresses = new AddressHandler();
        //Labels
        //Spaces are a super hacky way to get label to align correctly w/ field box
        JLabel lastLabel = new JLabel("Last Name            ");
        JLabel firstLabel = new JLabel("First Name                  ");
        JLabel middleLabel = new JLabel("Middle Name");
        JLabel address1Label = new JLabel("Address Line 1");
        JLabel address2Label = new JLabel("Address Line 2");
        JLabel cityLabel = new JLabel("City                         ");
        JLabel stateLabel = new JLabel("State");
        JLabel zipLabel = new JLabel("Zip Code            ");
        JLabel countryLabel=new JLabel("Country");
        JLabel phoneLabel = new JLabel("Phone Number          ");
        JLabel emailLabel = new JLabel("Email");
        errorLabel = new JLabel("");
        //Text entry boxes
        JTextField lastField = new JTextField(10);
        JTextField firstField = new JTextField(10);
        JTextField middleField = new JTextField(10);
        JTextField address1Field = new JTextField(15);
        JTextField address2Field = new JTextField(15);
        JTextField cityField = new JTextField(10);
        JTextField stateField = new JTextField(2);
        JTextField zipField = new JTextField(5);
        JTextField countryField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JTextField emailField = new JTextField(12);
        //Buttons
        JButton newButton = new JButton("        New        ");
        JButton editButton = new JButton("        Edit        ");
        JButton deleteButton = new JButton("        Delete        ");
        JButton saveButton = new JButton("        Save        ");
        JButton loadButton = new JButton("        Load        ");
        JButton cancelButton = new JButton("       Cancel       ");
        //Panels to contain Labels and text entry boxes
        JPanel nameLabelPanel = new JPanel(new FlowLayout(5, 20,1));
        JPanel nameFieldPanel = new JPanel(new FlowLayout(5, 20,1));
        JPanel addressLabelPanel1 = new JPanel(new FlowLayout(5, 20,1));
        JPanel addressFieldPanel1 = new JPanel(new FlowLayout(5, 20,1));
        JPanel addressLabelPanel2 = new JPanel(new FlowLayout(5, 20,1));
        JPanel addressFieldPanel2 = new JPanel(new FlowLayout(5, 20,1));
        JPanel addressLabelPanel3 = new JPanel(new FlowLayout(5, 20,1));
        JPanel addressFieldPanel3 = new JPanel(new FlowLayout(5, 20,1));
        JPanel phoneEmailLabelPanel = new JPanel(new FlowLayout(5, 20,1));
        JPanel phoneEmailFieldPanel = new JPanel(new FlowLayout(5, 20,1));
        JPanel errorPanel = new JPanel(new FlowLayout(5, 20,1));
        //Panel for buttons
        JPanel btn = new JPanel();
        btn.setLayout(new BoxLayout(btn, BoxLayout.X_AXIS)); 
        btn.add(newButton);
        btn.add(editButton);
        btn.add(deleteButton);
        btn.add(saveButton);
        btn.add(loadButton);
        btn.add(cancelButton);
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        //Jlist for listing entries

        
        model = new DefaultListModel<>();
        JList<String> entryList = new JList<>(model);
        JScrollPane entryPane = new JScrollPane(entryList);
        entryPane.setSize(300,400);


        //add fields and labels to each panel
        //name labels
        nameLabelPanel.add(firstLabel);
        nameLabelPanel.add(lastLabel);
        nameLabelPanel.add(middleLabel);
        //name fields
        nameFieldPanel.add(firstField);
        nameFieldPanel.add(lastField);
        nameFieldPanel.add(middleField);
        //address line 1 label and field
        addressLabelPanel1.add(address1Label);
        addressFieldPanel1.add(address1Field);
        //address line 2 label and field
        addressLabelPanel2.add(address2Label);
        addressFieldPanel2.add(address2Field);
        //address 3 label
        addressLabelPanel3.add(cityLabel);
        addressLabelPanel3.add(stateLabel);
        addressLabelPanel3.add(zipLabel);
        addressLabelPanel3.add(countryLabel);
        //address 2 field
        addressFieldPanel3.add(cityField);
        addressFieldPanel3.add(stateField);
        addressFieldPanel3.add(zipField);
        addressFieldPanel3.add(countryField);
        //phone+email labels
        phoneEmailLabelPanel.add(phoneLabel);
        phoneEmailLabelPanel.add(emailLabel);
        //phone+email fields
        phoneEmailFieldPanel.add(phoneField);
        phoneEmailFieldPanel.add(emailField);
        //error msg area
        errorPanel.add(errorLabel);
        //add to main panel
        allPanel.add(nameLabelPanel);
        allPanel.add(nameFieldPanel);
        allPanel.add(addressLabelPanel1);
        allPanel.add(addressFieldPanel1);
        allPanel.add(addressLabelPanel2);
        allPanel.add(addressFieldPanel2);
        allPanel.add(addressLabelPanel3);
        allPanel.add(addressFieldPanel3);
        allPanel.add(phoneEmailLabelPanel);
        allPanel.add(phoneEmailFieldPanel);
        
        allPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        allPanel.add(errorPanel);
        //panel for entry for list
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(entryPane);
        //add panels to main this
        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(allPanel);
        splitPane.setEnabled(false);
        //Split Pane contains entries on left and fields on right
        this.add(splitPane);
        /* Buttons */
        // "New" button action listener
        // Verifies information and adds to address book
        add(btn,BorderLayout.SOUTH);
        
        newButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String l = lastField.getText();
              String f = firstField.getText();
              String m = middleField.getText();
              String a = address1Field.getText();
              String a2 = address2Field.getText();
              String ci = cityField.getText();
              String st = stateField.getText();
              String z = zipField.getText();
              String co= countryField.getText();
              String p = phoneField.getText();
              String em = emailField.getText();
              if (!isValidEntry(l, f,m, a, a2, ci, st, z,co, p, em)) {
                return;
              }
              Data d = new Data(f, l,m, a, a2, ci, st, z,co, p, em);
              addresses.addAddress(d);
              //String s = addresses.formatAddresses();
              String s = d.formatData();
              model.addElement(s);
              //System.out.printf("%d\n", .getSize() );
                //empty the entry after finished
                lastField.setText("");
                firstField.setText("");
                middleField.setText("");
              address1Field.setText("");
              address2Field.setText("");
              cityField.setText("");
              stateField.setText("");
              zipField.setText("");
              countryField.setText("");
              phoneField.setText("");
              emailField.setText("");
              addresses.sortName();
              refreshList();
          }
        });
            // "Edit" button action listener
        //  Edits the highlighted entry from the address book
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = entryList.getSelectedIndex();
                if(selectedIndex != -1){
                  String l = lastField.getText();
                  String f = firstField.getText();
                  String m = middleField.getText();
                  String a = address1Field.getText();
                  String a2 = address2Field.getText();
                  String ci = cityField.getText();
                  String st = stateField.getText();
                  String z = zipField.getText();
                  String co = countryField.getText();
                  String p = phoneField.getText();
                  String em = emailField.getText();
                  if (!isValidEntry(l, f,m, a, a2, ci, st, z,co, p, em)) {
                    return;
                  }
                  Data d = addresses.getElement(selectedIndex);
                  //Set any fields that have been changed
                  d.setLastName(l);
                  d.setFirstName(f);
                  d.setmiddleName(m);
                  d.setAddress(a);
                  d.setAddressTwo(a2);
                  d.setCity(ci);
                  d.setState(st);
                  d.setZipCode(z);
                  d.setcountry(co);
                  d.setPhoneNumber(p);
                  d.setEmail(em);
                  d.formatData();
                  //Update entry
                  model.set(selectedIndex, d.formatData());
                }
            }
        });
       // "Delete" button action listener
       //  Deletes a highlighted entry from the address book
       deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = entryList.getSelectedIndex();
                if(selectedIndex != -1){
                    Data d = addresses.getElement(selectedIndex);
                    model.remove(selectedIndex);
                    addresses.removeAddress(d);
                    //empty the entry after finished
                        lastField.setText("");
                        firstField.setText("");
                        middleField.setText("");
                    address1Field.setText("");
                    address2Field.setText("");
                    cityField.setText("");
                    stateField.setText("");
                    zipField.setText("");
                    countryField.setText("");
                    phoneField.setText("");
                    emailField.setText("");
                }
            }
          });
       //cancel Button
       cancelButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                   //empty the entry 
                     lastField.setText("");
                     firstField.setText("");
                    middleField.setText("");
                   address1Field.setText("");
                   address2Field.setText("");
                   cityField.setText("");
                   stateField.setText("");
                   zipField.setText("");
                   countryField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
               }
           
         });
       // Entry List mouse Listener
       // Fills in info when clicking an entry
       entryList.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent e){
           try {
             if(e.getClickCount() == 1){

                     int selectedIndex = entryList.getSelectedIndex();
                     System.out.println(entryList.getSelectedValue());
                     //addresses
                     Data d = addresses.getElement(selectedIndex);
                     //Set text fields to current info
                     if(selectedIndex != -1) {
                         lastField.setText(d.getLastName());
                         firstField.setText(d.getFirstName());
                         middleField.setText(d.getMiddleName());
                         address1Field.setText(d.getAddress());
                         address2Field.setText(d.getAddressTwo());
                         cityField.setText(d.getCity());
                         stateField.setText(d.getState());
                         zipField.setText(d.getZipCode());
                         countryField.setText(d.getCountry());
                         phoneField.setText(d.getPhoneNumber());
                         emailField.setText(d.getEmail());
                     }
                 }
           } catch (Exception ex) {
           }
           }
       });
       //save and load buttons
       saveButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
             BookFrame.this.addresses.saveAddressesAs();
           }
         });
       loadButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
             JFileChooser fileChooser = new JFileChooser();
             fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
             if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
               File file = fileChooser.getSelectedFile();
               File f1 = file.getAbsoluteFile();
               String path = f1.getAbsolutePath();
               BookFrame frame = new BookFrame(path);
             }
           }
         });
        // Event listener for when the JFrame is closed
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              Main.WINDOW_COUNT--;
              if (Main.WINDOW_COUNT <= 0) {
                System.exit(0);
              }
            }
        });
        // Load file if a file path is provided with Constructor
        if (!path.equals("")) {
          addresses.openAddresses(path);
          ArrayList<Data> a = BookFrame.this.addresses.getArrayList();
          Data d;
          for (int i = 0; i < a.size(); i++) {
            d = a.get(i);
            BookFrame.this.model.addElement(d.formatData());
          }
        }
        //Display the window.
        this.pack();
        this.setVisible(true);
        Main.WINDOW_COUNT++;
    }
    // Checks address entry and populates error log on GUI. Returns true if valid entry, false otherwise.
    private boolean isValidEntry(String l, String f,String m, String a, String a2, String ci, String st, String z,String co, String p, String em) {
      boolean error = false;
      // Check for First or Last name
      if ((l.length() == 0 && f.length() == 0)) {
        errorLabel.setForeground(Color.red);
        errorLabel.setText("First or Last name is required");
        return false;
      }
      // Check for at least one contact field
      if (a.equals("")
        && a2.equals("")
        && ci.equals("")
        && st.equals("")
        && z.equals("")
        && co.equals("")
        && p.equals("")
        && em.equals("")) {
          errorLabel.setForeground(Color.red);
          errorLabel.setText("Error: At least one contact field is required.");
          return false;
        }
      // Check that state is entered in two letter format
      if (st.length() != 0 && st.length() != 2) {
        errorLabel.setForeground(Color.orange);
        errorLabel.setText("Warning: State field should be a two letter abreviation.");
        error = true;
      }
      // Check zip code field
      // First check for format of xxxxx
      if (z.length() != 0 && z.length() != 5) {
        // Next check for xxxxx-xxxxx
        if (z.length() != 11 || !z.contains("-")) {
          errorLabel.setForeground(Color.orange);
          errorLabel.setText("Warning: Zip code should be xxxxx or xxxxx-xxxxx.");
          error = true;
        }
      }
      // Count number of digits in phone number
      int count = 0;
      int len = p.length();
      for (int i = 0; i < len; i++) {
        if (Character.isDigit(p.charAt(i))) {
          count++;
        }
      }
      // Total digits should be either 10 or 11 with country code for U.S. phone number
      if (count != 10 && count != 11) {
        errorLabel.setForeground(Color.orange);
        errorLabel.setText("Warning: U.S. phone number should be 10 or 11 digits.");
        error = true;
      }
      // Check email field
      if (em.length() != 0 && !em.contains("@")) {
        errorLabel.setForeground(Color.orange);
        errorLabel.setText("Warning: Email field should contain '@' symbol.");
        error = true;
      }
      // All verifications passed, add entry and update text area, input fields, and error log
      if (!error) {
    	errorLabel.setForeground(Color.green);
        errorLabel.setText("No problem in Entry");
      }
      return true;
    }
  // Refreshes the entry list
  public void refreshList(){
    model.clear();
    for(int i =0; i < addresses.size(); i++){
        model.addElement(addresses.getElement(i).formatData());
    }
  }
   }