/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hardwarestore;

import hardwarestore.items.Item;
import hardwarestore.users.User;

import java.io.IOException;
import java.util.logging.Level;

/**
 * This is the main class of the Hardware Store database manager. It provides a
 * UI for a user to use the 10 main commands.
 * @author Alfonso
 */
public class MainAppUI extends javax.swing.JFrame {
    
    private final HardwareStore hardwareStore;
    private Log log;

    /**
     * Creates new form MainAppUI
     * @throws java.io.IOException
     */
    public MainAppUI() throws IOException{
        hardwareStore = new HardwareStore();
        log = new  Log("Logger.txt");
        log.logger.setLevel(Level.INFO);
        log.logger.info("Program Running.");
        initComponents();
    }
    private void resetBoxes(){
        UserIn_1.setText("Box 1");
        UserIn_2.setText("Box 2");
        UserIn_3.setText("Box 3");
        UserIn_4.setText("Box 4");
        UserIn_5.setText("Box 5");
        UserIn_6.setText("Box 6");
        UserIn_7.setText("Box 7");
        assistant.setText("Use the submit button to perform an action.");
        display.setText("");
    }
    private void setInstructions(int mode){
        resetBoxes();
        log.logger.info("Instructions output on display.");
        
        switch(mode){
            case 0: display.setText("Option 1: Displaying all Items.\n"
                    ); break;
            case 1: display.setText("Option 2: Adding or changing an item.\n"+
                                           "Please fill the boxes with the following information and then hit input:\n"+
                                           "Box 1: ID (5 alpha-numeric characters)\n"+
                                           "If updating hit search button. If item is found fields will be updated else you will have to fill them manually.\n"+
                                           "Box 2: Name\n"+
                                           "Box 3: Quantity\n"+
                                           "Box 4: Price\n"+
                                           "Box 5: Item Type(S = Small Hardware or A = Appliance)\n"+
                                           "If Small Hardware:\n"+
                                           "                 Box 6: Category (type a number)\n"+
                                           "                      1 - Door&Window\n"+
                                           "                      2 - Cabinet&Furniture\n"+
                                           "                      3 - Structural\n"+
                                           "                      4 - Other\n"+
                                           "If Appliance:\n"+
                                           "                 Box 6: Brand\n"+
                                           "                 Box 7: Type\n"+
                                           "Once finished push input button.\n"+
                                           "NOTE: I only update quantity but it would be easy to implement all other type of updates as well.\n"
                    ); break;
            case 2: display.setText("Option 3: Delete an item.\n"+
                                           "Please fill the boxes with the following information and then hit input:\n"+
                                           "Box 1: ID (5 alpha-numeric characters)\n"+
                                           "Once finished push input button.\n"
                    ); break;
            case 3: display.setText("Option 4: Search by name.\n"+
                                           "Please fill the boxes with the following information and then hit input:\n"+
                                           "Box 1: Name\n"+
                                           "Once finished push input button.\n"
                    ); break;
            case 4: display.setText("Option 5: Showing a list of users.\n"
                    ); break;
            case 5: display.setText("Option 6: Add new User.\n"+
                                           "Please fill the boxes with the following information and then hit input:\n"+
                                           "Box 1: ID \n"+
                                           "Box 2: First Name\n"+
                                           "Box 3: Last Name\n"+
                                           "Box 4: User Type(C = Customer or E = Employee)\n"+
                                           "If Customer:\n"+
                                           "                 Box 5: Phone\n"+
                                           "                 Box 6: Address\n"+
                                           "If Employee:\n"+
                                           "                 Box 5: SSN\n"+
                                           "                 Box 6: Monthly Salary\n"+
                                           "Once finished push input button.\n"
                    ); break;
            case 6: display.setText("Option 7: Update user info.\n"+
                                           "Please fill box 1 and 4 and then hit search button.\n"+
                                           "This will autofill the rest of the boxes. Then make your changes and hit the input button.\n"+
                                           "Box 1: ID \n"+
                                           "Box 2: First Name\n"+
                                           "Box 3: Last Name\n"+
                                           "Box 4: User Type(C = Customer or E = Employee)\n"+
                                           "If Customer:\n"+
                                           "                 Box 5: Phone\n"+
                                           "                 Box 6: Address\n"+
                                           "If Employee:\n"+
                                           "                 Box 5: SSN\n"+
                                           "                 Box 6: Monthly Salary\n"+
                                           "Once finished push input button.\n"
                    ); break;
            case 7: display.setText("Option 8: Complete a sale.\n"+
                                           "Box 1: Item ID \n"+
                                           "Box 2: Quantity\n"+
                                           "Box 3: Employee ID\n"+
                                           "Box 4: Customer ID\n"+
                                           "Once finished hit the input button to complete the sale.\n"
                    ); break;
            case 8: display.setText("Option 9: Showing a list of all sales.\n"
                    ); break;
            case 9: display.setText("Option 10: Exiting the program.\n Once finished push input button.\n"
                    ); break;
            default: display.setText("Option not recognized. Please report this issue.\n"
                    ); break;
        }
    }
    
    //Function 1
    /**
     * This method shows all items in the inventory.
     */
    private void showAllItems(){
        HardwareStore.sortItemList();
        display.setText(hardwareStore.getAllItemsFormatted());
        assistant.setText("All items displayed.");
        log.logger.info("showAllItems success.");
    }
    
    //Function 5
    /**
     * This method shows all users in the system.
     */
    public void showAllUsers() {
        display.setText(hardwareStore.getAllUsersFormatted());
        assistant.setText("All users displayed.");
        log.logger.info("showAllUsers success.");
    }
    
    //Function 9
    /**
     * This method shows all transactions in the system.
     */
    public void showAllTransactions(){
        display.setText(hardwareStore.getAllTransactionsFormatted());
        assistant.setText("All transactions displayed.");
        log.logger.info("showAllTransactions success.");
    }
    
    //Function 2
    /**
     * This method will add items quantity with given number. If the item does
     * not exist, it will call another method to add it.
     *
     */
    public void addItemQuantity() {
        
        log.logger.info("addItemQuantity called.");
        
        // Check for all fields updated
        if(
                UserIn_1.getText().equals("Box 1") ||
                UserIn_2.getText().equals("Box 2") ||
                UserIn_3.getText().equals("Box 3") ||
                UserIn_4.getText().equals("Box 4") ||
                UserIn_5.getText().equals("Box 5") ||
                UserIn_6.getText().equals("Box 6")
          
        ){
            assistant.setText("Please fill all fields and use input or use search button to autofill.");
            return;
        }
        
        // Set all the fields in the boxes
        String brand = "";
        String type = "";
        Integer category_i = -1;
        Integer quantity = -1;
        Float price = -1f;
        
        String idNumber = UserIn_1.getText();
        String name = UserIn_2.getText();
        try {quantity = Integer.parseInt(UserIn_3.getText());}catch(Exception e){log.logger.warning("Exception caught in addItemQuantity"); assistant.setText("Illegal input: Box 3 must input an integer."); UserIn_3.setText("Error"); return;}
        try{price = Float.parseFloat(UserIn_4.getText());}catch(Exception e){log.logger.warning("Exception caught in addItemQuantity"); assistant.setText("Illegal input: Box 4 must input an float."); UserIn_4.setText("Error"); return;}
        String category = UserIn_5.getText();
        if(category.equals("S") || category.equals("Small Hardware")){
            try {category_i = Integer.parseInt(UserIn_6.getText());}catch(Exception e){log.logger.warning("Exception caught in addItemQuantity"); assistant.setText("Illegal input: Box 5 must input an integer."); UserIn_6.setText("Error"); return;}
            switch(category_i){
                case 1: category = "Door&Window"; break;
                case 2: category = "Cabinet&Furnitur"; break;
                case 3: category = "Structural"; break;
                case 4: category = "Other"; break;
                default: assistant.setText("Category not recognized. Please choose a number between 1 and 4."); return;
            }
        } else if (category.equals("A") || category.equals("Appliance")){
            brand = UserIn_6.getText();
            type  = UserIn_7.getText();
        } else{
            assistant.setText("Please enter S or A in Box 5"); UserIn_5.setText("Error"); return;
        }
        
        if (!idNumber.matches("[A-Za-z0-9]{5}")) {
                assistant.setText("Invalid ID Number: not proper format. "
                        + "\nID Number must be 5 alphanumeric characters.");
                UserIn_1.setText("Error");
                return;
        }
        
        int itemIndex = hardwareStore.findItemIndex(idNumber);
        
        if (itemIndex != -1) { // If item exists in the database
            if(quantity <= 0){
                assistant.setText("You can only add quantities bigger than 0."); 
                return;
            }
            hardwareStore.addQuantity(itemIndex, quantity);
            assistant.setText("Quantity updated.");
        } else{
            if(quantity <= 0){
                assistant.setText("You need to have more than 1 item to add it.");
                UserIn_3.setText("Error");
                return;
            }
            
            if(price <= 0){
                assistant.setText("An item needs to cost more than 0.");
                UserIn_4.setText("Error");
                return;
            }
            
            if (UserIn_5.getText().equals("A") || UserIn_5.getText().equals("Appliance")){
                hardwareStore.addNewAppliance(idNumber, name, quantity, price, brand, type);
                assistant.setText("Item added.");
            }else if(UserIn_5.getText().equals("S") || UserIn_5.getText().equals("Small Hardware")){
                hardwareStore.addNewSmallHardwareItem(idNumber, name, quantity, price, category);
                assistant.setText("Item added.");
            }else{
                assistant.setText("Unable to add item, check all fields.");
            }
        }
        log.logger.info("addItemQuantity success.");
    }
    
    
    //Function 3
    /**
     * This method will remove the item with given ID.
     * If the item does not exist, it will show an appropriate message.
     */
    public void removesItem() {
        
        log.logger.info("removesItem called.");
        
        // Check for all fields updated
        if(
                UserIn_1.getText().equals("Box 1") 
        ){
            assistant.setText("Please fill all necessary fields..");
            return;
        }
        
        String idNumber = UserIn_1.getText();
        if (!idNumber.matches("[A-Za-z0-9]{5}")) {
                assistant.setText("Invalid ID Number: not proper format. "
                        + "\nID Number must be 5 alphanumeric characters.");
                UserIn_1.setText("Error");
                return;
        }
        int itemIndex = hardwareStore.findItemIndex(idNumber);
        
        if(itemIndex == -1){
            assistant.setText("Item not found please check ID number.");
            UserIn_1.setText("Error");
        } else{
            hardwareStore.removeItem(itemIndex);
            assistant.setText("Item deleted.");
        }
        
        log.logger.info("removesItem success.");
    }
    
    //Function 4
    /**
     * This method can search item by a given name (part of name.
     * Case-insensitive.) Will display all items with the given name.
     */
    public void searchItemByName() {
        
        log.logger.info("searchItemByName called.");
        
        String name = UserIn_2.getText();
        if (name.equals("Box 2")) {
                assistant.setText("Please fill in box 2 with the name");
                UserIn_2.setText("Error");
                return;
        }
        
        String output = hardwareStore.getMatchingItemsByName(name);
        if (output == null) {
            assistant.setText("Item not found with: " + name + ".");
        } else {
            display.setText(output);
        }
        
        log.logger.info("searchItemByName success.");
    }
    
    //Function 6
    /**
     * This method will add a user (customer or employee) to the system.
     *
     */
    public void addUser() {
        
        log.logger.info("addUser called.");
        
        // Check for all fields updated
        if(
                UserIn_1.getText().equals("Box 1") ||
                UserIn_2.getText().equals("Box 2") ||
                UserIn_3.getText().equals("Box 3") ||
                UserIn_4.getText().equals("Box 4") ||
                UserIn_5.getText().equals("Box 5") ||
                UserIn_6.getText().equals("Box 6")
          
        ){
            assistant.setText("Please fill all fields and use input or use search button to autofill.");
            return;
        }
        
        // User
        int id = -1;
        try {id = Integer.parseInt(UserIn_1.getText());}catch(Exception e){log.logger.warning("Exception caught in addUser"); assistant.setText("Illegal input: Box 1 must input an integer."); UserIn_1.setText("Error"); return;}
        String firstName = UserIn_2.getText();
        String lastName = UserIn_3.getText();
        
        String type = UserIn_4.getText();
        
        // Employee
        int SSN = -1;
        float salary = -1;
        
        // Customer
        String phone = "";
        String address = "";
        
        
        if(type.equals("C") || type.equals("Customer")){
            phone = UserIn_5.getText();
            address = UserIn_6.getText();
        }else if(type.equals("E") || type.equals("Employee")){
            try {SSN = Integer.parseInt(UserIn_5.getText());}catch(Exception e){log.logger.warning("Exception caught in addUser"); assistant.setText("Illegal input: Box 5 must input an integer."); UserIn_5.setText("Error"); return;}
            try{salary = Float.parseFloat(UserIn_6.getText());}catch(Exception e){log.logger.warning("Exception caught in addUser"); assistant.setText("Illegal input: Box 6 must input an float."); UserIn_6.setText("Error"); return;}
        }else{
            assistant.setText("Type of user not recognized. Please checl box 4."); return;
        }
        
        // Check all values:
        if(type.equals("E") || type.equals("Employee")){
            if (SSN <= 100000000 || SSN > 999999999) {
                assistant.setText("Invalid social security number. "
                        + "SSN is a 9-digit integer.");
                return;
            }else if (salary < 0){
                assistant.setText("Invalid salary."
                        + "It must be (at least) 0.");
                return;
            } else{
                hardwareStore.addEmployee(firstName,lastName, SSN, salary);
                assistant.setText("Employee added.");
                log.logger.info("addUser success.");
                return;
            }
            
            
        } else if(type.equals("C") || type.equals("Customer")){
            hardwareStore.addCustomer(firstName, lastName, phone, address);
            assistant.setText("Customer added.");
            log.logger.info("addUser success.");
            return;
        } else{
            assistant.setText("Easter egg."); return;
        }
        
    }
    
    //Function 7
    /**
     * This method will edit a user (customer or employee).
     *
     */
    public void editUser() {
        
        log.logger.info("editUser called.");
        // Check for all fields updated
        if(
                UserIn_1.getText().equals("Box 1") ||
                UserIn_2.getText().equals("Box 2") ||
                UserIn_3.getText().equals("Box 3") ||
                UserIn_4.getText().equals("Box 4") ||
                UserIn_5.getText().equals("Box 5") ||
                UserIn_6.getText().equals("Box 6")
          
        ){
            assistant.setText("Please fill all fields and use input or use search button to autofill.");
            return;
        }
        
        // User
        int id = -1;
        try {id = Integer.parseInt(UserIn_1.getText());}catch(Exception e){log.logger.warning("Exception caught in editUser"); assistant.setText("Illegal input: Box 1 must input an integer."); UserIn_1.setText("Error"); return;}
        String firstName = UserIn_2.getText();
        String lastName = UserIn_3.getText();
        String type = UserIn_4.getText();
        
        User tempUser = hardwareStore.findUser(id);
            
        if (tempUser == null) {
            assistant.setText("User not found.");
            return;
        }
        
        // Employee
        int SSN = -1;
        float salary = -1;
        
        // Customer
        String phone = "";
        String address = "";
        
        
        if(type.equals("C") || type.equals("Customer")){
            phone = UserIn_5.getText();
            address = UserIn_6.getText();
        }else if(type.equals("E") || type.equals("Employee")){
            try {SSN = Integer.parseInt(UserIn_5.getText());}catch(Exception e){log.logger.warning("Exception caught in editUser"); assistant.setText("Illegal input: Box 5 must input an integer."); UserIn_5.setText("Error"); return;}
            try{salary = Float.parseFloat(UserIn_6.getText());}catch(Exception e){log.logger.warning("Exception caught in editUser"); assistant.setText("Illegal input: Box 6 must input an float."); UserIn_6.setText("Error"); return;}
        }else{
            assistant.setText("Type of user not recognized. Please checl box 4."); return;
        }
        
        // Check all values:
        if(type.equals("E") || type.equals("Employee")){
            if (SSN <= 100000000 || SSN > 999999999) {
                assistant.setText("Invalid social security number. "
                        + "SSN is a 9-digit integer.");
                return;
            }else if (salary < 0){
                assistant.setText("Invalid salary."
                        + "It must be (at least) 0.");
                return;
            } else{
                hardwareStore.editEmployeeInformation(id, firstName,lastName, SSN, salary);
                assistant.setText("Employee updated.");
                log.logger.info("editUser success.");
                return;
            }
            
            
        } else if(type.equals("C") || type.equals("Customer")){
            hardwareStore.editCustomerInformation(id, firstName, lastName, phone, address);
            assistant.setText("Customer updated.");
            log.logger.info("editUser success.");
            return;
        } else{
            assistant.setText("Easter egg."); return;
        }
    }
    
    //Function 8
    /**
     * This method will lead user to complete a transaction.
     */
    public void finishTransaction(){
        
        log.logger.info("finishTransaction called.");
        
        // Check for all fields updated
        if(
                UserIn_1.getText().equals("Box 1") ||
                UserIn_2.getText().equals("Box 2") ||
                UserIn_3.getText().equals("Box 3") ||
                UserIn_4.getText().equals("Box 4")
          
        ){
            assistant.setText("Please fill all fields and use input or use search button to autofill.");
            return;
        }
        
        // Variables
        String itemID = UserIn_1.getText();
        int itemQuantity = -1;
        int employeeID = -1;
        int userID = -1;
        
        try {itemQuantity = Integer.parseInt(UserIn_2.getText());}catch(Exception e){log.logger.warning("Exception caught in finishTransaction"); assistant.setText("Illegal input: Box 2 must input an integer."); UserIn_2.setText("Error"); return;}
        try {employeeID = Integer.parseInt(UserIn_3.getText());}catch(Exception e){log.logger.warning("Exception caught in finishTransaction"); assistant.setText("Illegal input: Box 3 must input an integer."); UserIn_3.setText("Error"); return;}
        try {userID = Integer.parseInt(UserIn_4.getText());}catch(Exception e){log.logger.warning("Exception caught in finishTransaction"); assistant.setText("Illegal input: Box 4 must input an integer."); UserIn_4.setText("Error"); return;}
        
        int itemIndex = hardwareStore.findItemIndex(itemID);
        
        if (itemIndex == -1) {
            assistant.setText("Item not found. Will return to main menu.");
            return;
        }else {
            Item tempItem = hardwareStore.findItem(itemID);
            // Check quantity
            if (itemQuantity <= 0) {
                assistant.setText("Invalid input: must be greater than 0.");
                return;
            } else if (itemQuantity > tempItem.getQuantity()) {
                assistant.setText("Invalid input: Number too big. Transaction cannot progress.");
                return;
            }
            // Check employee ID
            if (hardwareStore.findUserIndex(employeeID) == -1) {
                assistant.setText("Employee not found.");
                return;
            } else if (!hardwareStore.findUser(employeeID).isEmployee) {
                assistant.setText("This user is not an employee.");
                return;
            }
            
            // Check customer ID
            if (hardwareStore.findUserIndex(userID) == -1) {
                assistant.setText("Customer not found.");
                return;
            } else if (hardwareStore.findUser(userID).isEmployee) {
                assistant.setText("This user is not an customer.");
                return;
            }
            
            // Complete sale
            hardwareStore.progressTransaction(itemID, itemQuantity, userID, employeeID, itemIndex);
            assistant.setText("Transaction complete.");
            log.logger.info("finishTransaction success.");
        }
        
    }
        
    //Function 10
    /**
     * These method is called to save the database before exit the system.
     * @throws IOException
     */
    private void saveDatabase()throws IOException{
        hardwareStore.writeDatabase();
        display.setText("All data saved.");
        log.logger.info("Data saved exiting program.");
        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        MainPanel = new javax.swing.JPanel();
        menuOptionsComboBox = new javax.swing.JComboBox<>();
        assistant = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        inputButton = new javax.swing.JButton();
        UserIn_1 = new javax.swing.JTextField();
        UserIn_2 = new javax.swing.JTextField();
        UserIn_3 = new javax.swing.JTextField();
        UserIn_4 = new javax.swing.JTextField();
        UserIn_5 = new javax.swing.JTextField();
        UserIn_6 = new javax.swing.JTextField();
        UserIn_7 = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ye Olde Hardware Store", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Old English Text MT", 0, 36))); // NOI18N

        menuOptionsComboBox.setMaximumRowCount(15);
        menuOptionsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1. Show all existing items records in the database.", "2. Add new item (or quantity) to the database.", "3. Delete an item from a database.", "4. Search for an item given its name.", "5. Show a list of users in the database.", "6. Add new user to the database.", "7. Update user info (given their id).", "8. Complete a sale transaction.", "9. Show completed sale transactions.", "10. Exit program." }));
        menuOptionsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOptionsComboBoxActionPerformed(evt);
            }
        });

        assistant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        assistant.setText("Use the submit button to perform an action.");
        assistant.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assistant", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 11))); // NOI18N
        assistant.setFocusable(false);

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        display.setColumns(20);
        display.setRows(5);
        display.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Display", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 2, 11))); // NOI18N
        display.setFocusable(false);
        jScrollPane1.setViewportView(display);

        inputButton.setText("Input");
        inputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputButtonActionPerformed(evt);
            }
        });

        UserIn_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_1.setText("Box 1");

        UserIn_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_2.setText("Box 2");

        UserIn_3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_3.setText("Box 3");

        UserIn_4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_4.setText("Box 4");

        UserIn_5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_5.setText("Box 5");

        UserIn_6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_6.setText("Box 6");

        UserIn_7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserIn_7.setText("Box 7");

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(assistant, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuOptionsComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 462, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserIn_1)
                    .addComponent(UserIn_2)
                    .addComponent(UserIn_3)
                    .addComponent(UserIn_4)
                    .addComponent(UserIn_5)
                    .addComponent(UserIn_6)
                    .addComponent(UserIn_7)
                    .addComponent(SearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addContainerGap())
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menuOptionsComboBox)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(assistant))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(UserIn_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserIn_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserIn_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserIn_4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserIn_5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserIn_6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserIn_7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 516, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This button uses user input to display information about a chosen action
     * and explain the steps the user has to follow.
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        display.setText(String.valueOf(menuOptionsComboBox.getSelectedIndex()));
        
        setInstructions(menuOptionsComboBox.getSelectedIndex()); 

            switch(menuOptionsComboBox.getSelectedIndex()){
                case 0: showAllItems(); break;
                case 4: showAllUsers(); break; // to show all users
                case 8: showAllTransactions(); break; // to show all transactions
                default:break;
            }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void menuOptionsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOptionsComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuOptionsComboBoxActionPerformed

    /**
     * This button uses user input to finalize commands and perform changes in
     * database
     */
    private void inputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputButtonActionPerformed
        // Stores what is in the input box in a string.
        try{
            switch(menuOptionsComboBox.getSelectedIndex()){
                case 1: addItemQuantity(); return; 
                case 2: removesItem(); return;
                case 3: searchItemByName(); return;
                case 5: addUser(); return;
                case 6: editUser(); return; 
                case 9: saveDatabase(); return;
                case 7: finishTransaction(); return;
                default: assistant.setText("Input button disabled for this action.");
            }
        } catch (IOException e) {
            display.setText("IOException caught. Exiting program.");
            log.logger.severe("Exception caught in inputButtonActionPerformed");
            System.exit(0);
        }
        
    }//GEN-LAST:event_inputButtonActionPerformed

    /**
     * This button uses user input to fill the fields when seen by the user
     * when the data is already available.
     */
    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        int method = menuOptionsComboBox.getSelectedIndex();
        
        if(method == 2 || method == 3){
            if(UserIn_1.getText().equals("Box 1")){
                assistant.setText("Please fill Box 1 with the ID"); UserIn_1.setText("Error"); return;
            } else if(UserIn_5.getText().equals("Box 5")){
                assistant.setText("Please fill Box 5 with the Type (S or A)"); UserIn_5.setText("Error"); return;
            }
            String idNumber = UserIn_1.getText();
            String type = UserIn_1.getText();
            
            if (!idNumber.matches("[A-Za-z0-9]{5}")) {
                assistant.setText("Invalid ID Number: not proper format. "
                        + "\nID Number must be 5 alphanumeric characters.");
                UserIn_1.setText("Error");
                return;
            }
            
            int itemIndex = hardwareStore.findItemIndex(idNumber);
            
            if(itemIndex == -1){
                assistant.setText("Item not found. If you wish to add it follow option 2 instructions.");
            }else{
                Item temp = hardwareStore.getItem(itemIndex);
                if(temp != null){
                    UserIn_2.setText(temp.getName());
                    UserIn_3.setText(String.valueOf(temp.getQuantity()));
                    UserIn_4.setText(Float.toString(temp.getPrice()));
                    
                    if(UserIn_5.getText().equals("S") || UserIn_5.getText().equals("S")){
                        UserIn_6.setText("Other");
                        assistant.setText("Small Hardware info loaded.");
                    } else if (UserIn_5.getText().equals("A") || UserIn_5.getText().equals("Appliance")){
                        UserIn_6.setText("Brand");
                        UserIn_7.setText("Type");
                        assistant.setText("Appliance info loaded.");
                    } else{
                        assistant.setText("Type not recognized check Box 5.");
                        return;
                    }
                }else{
                    assistant.setText("Item not found.");
                }
            }
            
        }else if(method == 5 || method == 6){
            int id = -1;
            try {id = Integer.parseInt(UserIn_1.getText());}catch(Exception e){log.logger.warning("Exception caught in SearchButtonActionPerformed"); assistant.setText("Illegal input: Box 1 must input an integer."); UserIn_1.setText("Error"); return;}
            User tempUser = hardwareStore.findUser(id);
            
            if (tempUser == null) {
            assistant.setText("User not found.");
            return;
            } else{
                UserIn_2.setText(tempUser.getFirstName());
                UserIn_3.setText(tempUser.getLastName());
            }
            
        }
    }//GEN-LAST:event_SearchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAppUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                new MainAppUI().setVisible(true);
                } catch (IOException e){ 
                }
            }
        });
    }

    private String userInput;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField UserIn_1;
    private javax.swing.JTextField UserIn_2;
    private javax.swing.JTextField UserIn_3;
    private javax.swing.JTextField UserIn_4;
    private javax.swing.JTextField UserIn_5;
    private javax.swing.JTextField UserIn_6;
    private javax.swing.JTextField UserIn_7;
    private javax.swing.JTextField assistant;
    private javax.swing.JTextArea display;
    private javax.swing.JButton inputButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> menuOptionsComboBox;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
