package swingbiometricsrecord;

import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import static swingbiometricsrecord.BiometricRegistration.byteArrayToInt;
import static swingbiometricsrecord.BiometricRegistration.changeByte;
import static swingbiometricsrecord.BiometricRegistration.writeBitmap;

public class AttendanceRecorder extends javax.swing.JFrame {
    Database database = new Database();
    Generate generate = new Generate();
    int fpWidth = 0;
    int fpHeight = 0;
    private byte[] lastRegTemp = new byte[2048];
    private int cbRegTemp = 0;
    private byte[][] regtemparray = new byte[3][2048];
    private boolean bRegister = false;
    private boolean bIdentify = true;
    private int iFid = 1;	
    private int nFakeFunOn = 1;
    static final int enroll_cnt = 3;
    private int enroll_idx = 0;	
    private byte[] imgbuf = null;
    private byte[] template = new byte[2048];
    private int[] templateLen = new int[1];	
    private boolean mbStop = true;
    private long mhDevice = 0;
    private long mhDB = 0;
    private WorkThread workThread = null;
    int id;
    boolean test = false;
    

    public AttendanceRecorder() {
        initComponents();
        eventName.setText(EventsSelector.selectedEvent);
        showAttendance();
        this.setLocationRelativeTo(null);
        textArea.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerSection = new javax.swing.JPanel();
        systemName = new javax.swing.JLabel();
        windowName = new javax.swing.JLabel();
        attendanceSection = new javax.swing.JPanel();
        biometricsSection = new javax.swing.JPanel();
        fingerPrint = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        attendanceButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();
        eventName = new javax.swing.JLabel();
        footerSection = new javax.swing.JPanel();
        textArea = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerSection.setBackground(new java.awt.Color(59, 89, 152));

        systemName.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        systemName.setForeground(new java.awt.Color(247, 247, 247));
        systemName.setText("Bicol University Gubat Campus Record and Monitoring System");

        windowName.setFont(new java.awt.Font("FreeSans", 1, 24)); // NOI18N
        windowName.setForeground(new java.awt.Color(247, 247, 247));
        windowName.setText("Attendance Record");

        attendanceSection.setBackground(new java.awt.Color(139, 157, 195));

        biometricsSection.setBackground(new java.awt.Color(223, 227, 238));

        fingerPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fingerPrintActionPerformed(evt);
            }
        });

        openButton.setText("Open Scanner");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        attendanceButton.setText("Start Attendance");
        attendanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close Scanner");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout biometricsSectionLayout = new javax.swing.GroupLayout(biometricsSection);
        biometricsSection.setLayout(biometricsSectionLayout);
        biometricsSectionLayout.setHorizontalGroup(
            biometricsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, biometricsSectionLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(biometricsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(fingerPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        biometricsSectionLayout.setVerticalGroup(
            biometricsSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fingerPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(biometricsSectionLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(openButton)
                .addGap(18, 18, 18)
                .addComponent(attendanceButton)
                .addGap(18, 18, 18)
                .addComponent(closeButton)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Course", "Year", "Block", "Time", "Date"
            }
        ));
        jScrollPane1.setViewportView(attendanceTable);

        eventName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eventName.setText("jLabel1");

        javax.swing.GroupLayout attendanceSectionLayout = new javax.swing.GroupLayout(attendanceSection);
        attendanceSection.setLayout(attendanceSectionLayout);
        attendanceSectionLayout.setHorizontalGroup(
            attendanceSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attendanceSectionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(eventName)
                .addGap(479, 479, 479))
            .addGroup(attendanceSectionLayout.createSequentialGroup()
                .addGroup(attendanceSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(attendanceSectionLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(attendanceSectionLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(biometricsSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        attendanceSectionLayout.setVerticalGroup(
            attendanceSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attendanceSectionLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(biometricsSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eventName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout headerSectionLayout = new javax.swing.GroupLayout(headerSection);
        headerSection.setLayout(headerSectionLayout);
        headerSectionLayout.setHorizontalGroup(
            headerSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerSectionLayout.createSequentialGroup()
                .addGroup(headerSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerSectionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(attendanceSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(headerSectionLayout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(windowName)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(headerSectionLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(systemName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerSectionLayout.setVerticalGroup(
            headerSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerSectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(systemName, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(attendanceSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        footerSection.setBackground(new java.awt.Color(59, 89, 152));

        textArea.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        textArea.setForeground(new java.awt.Color(225, 225, 225));
        textArea.setText("jLabel1");

        javax.swing.GroupLayout footerSectionLayout = new javax.swing.GroupLayout(footerSection);
        footerSection.setLayout(footerSectionLayout);
        footerSectionLayout.setHorizontalGroup(
            footerSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerSectionLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(textArea)
                .addContainerGap(879, Short.MAX_VALUE))
        );
        footerSectionLayout.setVerticalGroup(
            footerSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerSectionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textArea)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(footerSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headerSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerSection, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footerSection, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fingerPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fingerPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fingerPrintActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        if (0 != mhDevice)
	{
            textArea.setText("Please close device first!");
            return;
        }
        int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
        cbRegTemp = 0;
        bRegister = false;
        bIdentify = false;
        iFid = 1;
        enroll_idx = 0;
        if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init())
	{
            textArea.setText("Init failed!");
            return;
	}
	ret = FingerprintSensorEx.GetDeviceCount();
	if (ret < 0)
	{
            textArea.setText("No devices connected!");
            FreeSensor();
            return;
	}
	if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0)))
	{
            textArea.setText("Open device fail, ret = " + ret + "!");
            FreeSensor();
            return;
	}
	if (0 == (mhDB = FingerprintSensorEx.DBInit()))
	{
            textArea.setText("Init DB fail, ret = " + ret + "!");
            FreeSensor();
            return;
	}			
	FingerprintSensorEx.DBSetParameter(mhDB,  5010, 0);							
	byte[] paramValue = new byte[4];
	int[] size = new int[1];
	size[0] = 4;
	FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
	fpWidth = byteArrayToInt(paramValue);
	size[0] = 4;
	FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
	fpHeight = byteArrayToInt(paramValue);
	imgbuf = new byte[fpWidth*fpHeight];
	fingerPrint.resize(fpWidth, fpHeight);
	mbStop = false;
	workThread = new WorkThread();
	workThread.start();
	textArea.setText("Open succ!");
    }//GEN-LAST:event_openButtonActionPerformed

    private void attendanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceButtonActionPerformed
        if (!test){
            test = true;
            bRegister = false;
        }
        textArea.setText("Attendance ready.");
    }//GEN-LAST:event_attendanceButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        FreeSensor();
        textArea.setText("Close succ!");
        System.out.println("Device Off");
    }//GEN-LAST:event_closeButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
        EventsSelector.main();
    }//GEN-LAST:event_backButtonActionPerformed

    private void FreeSensor(){
            mbStop = true;
            test = false;
            try {		
		Thread.sleep(1000);
            } catch (InterruptedException e) {
		e.printStackTrace();
            }
            if (0 != mhDB){
		FingerprintSensorEx.DBFree(mhDB);
		mhDB = 0;
            }
            if (0 != mhDevice){
		FingerprintSensorEx.CloseDevice(mhDevice);
		mhDevice = 0;
            }
            FingerprintSensorEx.Terminate();
    }

    public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight,String path) throws IOException {
	java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
	java.io.DataOutputStream dos = new java.io.DataOutputStream(fos);

	int w = (((nWidth+3)/4)*4);
	int bfType = 0x424d; 
	int bfSize = 54 + 1024 + w * nHeight;
	int bfReserved1 = 0;
	int bfReserved2 = 0;
	int bfOffBits = 54 + 1024;

	dos.writeShort(bfType); 
	dos.write(changeByte(bfSize), 0, 4); 
                
	dos.write(changeByte(bfReserved1), 0, 2);
	dos.write(changeByte(bfReserved2), 0, 2);
	dos.write(changeByte(bfOffBits), 0, 4);

	int biSize = 40;
	int biWidth = nWidth;
	int biHeight = nHeight;
	int biPlanes = 1; 
	int biBitcount = 8;
	int biCompression = 0;
	int biSizeImage = w * nHeight;
	int biXPelsPerMeter = 0;
	int biYPelsPerMeter = 0;
	int biClrUsed = 0;
	int biClrImportant = 0;

	dos.write(changeByte(biSize), 0, 4);
	dos.write(changeByte(biWidth), 0, 4);
	dos.write(changeByte(biHeight), 0, 4);
	dos.write(changeByte(biPlanes), 0, 2);
	dos.write(changeByte(biBitcount), 0, 2);
	dos.write(changeByte(biCompression), 0, 4);
	dos.write(changeByte(biSizeImage), 0, 4);
	dos.write(changeByte(biXPelsPerMeter), 0, 4);
	dos.write(changeByte(biYPelsPerMeter), 0, 4);
	dos.write(changeByte(biClrUsed), 0, 4);
	dos.write(changeByte(biClrImportant), 0, 4);

	for (int i = 0; i < 256; i++) {
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(0);
	}

	byte[] filter = null;
	if (w > nWidth){
            filter = new byte[w-nWidth];
	}
		
	for(int i=0;i<nHeight;i++){
            dos.write(imageBuf, (nHeight-1-i)*nWidth, nWidth);
            if (w > nWidth)
		dos.write(filter, 0, w-nWidth);
            }
            dos.flush();
            dos.close();
            fos.close();
	}
    
    public static byte[] changeByte(int data) {
		return intToByteArray(data);
    }
    
    public static byte[] intToByteArray (final int number) {
	byte[] abyte = new byte[4];  
	abyte[0] = (byte) (0xff & number);    
	abyte[1] = (byte) ((0xff00 & number) >> 8);  
	abyte[2] = (byte) ((0xff0000 & number) >> 16);  
	abyte[3] = (byte) ((0xff000000 & number) >> 24);  
	return abyte; 
   }
    
   public static int byteArrayToInt(byte[] bytes) {
        int number = bytes[0] & 0xFF;   
        number |= ((bytes[1] << 8) & 0xFF00);  
        number |= ((bytes[2] << 16) & 0xFF0000);  
	number |= ((bytes[3] << 24) & 0xFF000000);  
	return number;  
    }
   
     private class WorkThread extends Thread {
       @Override
        public void run() {
	super.run();
	int ret = 0;
	while (!mbStop) {
            templateLen[0] = 2048;
	     if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen))){
	        if (nFakeFunOn == 1){
                    byte[] paramValue = new byte[4];
                    int[] size = new int[1];
                    size[0] = 4;
                    int nFakeStatus = 0;
                    ret = FingerprintSensorEx.GetParameters(mhDevice, 2004, paramValue, size);
                    nFakeStatus = byteArrayToInt(paramValue);
                    System.out.println("ret = "+ ret +",nFakeStatus=" + nFakeStatus);
                    if (0 == ret && (byte)(nFakeStatus & 31) != 31){
            		textArea.setText("Is a fake-finer?");
            		return;
                    }
                }
                OnCatpureOK(imgbuf);
                OnExtractOK(template, templateLen[0]);
	    }
            try {
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
	    }

	}
    }
    private void runOnUiThread(Runnable runnable) {
    
    }
}
    
     private void OnCatpureOK(byte[] imgBuf){
    try {
	writeBitmap(imgBuf, fpWidth, fpHeight, "fingerprint.bmp");
	fingerPrint.setIcon(new ImageIcon(ImageIO.read(new File("fingerprint.bmp"))));
    } catch (IOException e) {
	e.printStackTrace();
    }
}
     
     private void OnExtractOK(byte[] template, int len){
	if(test){
            try {
                database.connect();
                Statement statement = database.connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM `students`");
                int score = 0;
                while (resultSet.next()){
                    int studentId = resultSet.getInt("id");
                    byte[] templateFromDb = new byte[2048];
                    String base64Template = resultSet.getString("rightThumb");
                    FingerprintSensorEx.Base64ToBlob(base64Template, templateFromDb, 2048);
                    score = FingerprintSensorEx.DBMatch(mhDB, templateFromDb, template);
                    System.out.println(resultSet.getString("firstName") + " " + resultSet.getString("lastName") + " : " + score);
                    if (score > 0){
                        String checkAttendance = "SELECT * FROM `"+generate.eventsTableName(EventsSelector.selectedEvent)+"` WHERE `student` = "+studentId+" ";
                        System.out.println(checkAttendance);
                        try{
                            Statement checkStatement = database.connection.createStatement();
                            ResultSet isAlreadiExist = checkStatement.executeQuery(checkAttendance);
                            if(isAlreadiExist.next()){
                                JOptionPane.showMessageDialog(null, "You allready exist in "+EventsSelector.selectedEvent+" attendance list.");
                            }else{
                                DefaultTableModel model = (DefaultTableModel)attendanceTable.getModel();
                                model.setRowCount(0);
                                String attendanceQuery = "INSERT INTO `"+generate.eventsTableName(EventsSelector.selectedEvent)+"` (`student`, `date`, `time`) VALUES ("+studentId+", NOW(), NOW()) ";
                                System.out.println(attendanceQuery);
                                Statement statementTwo = database.connection.createStatement();
                                int attended = statementTwo.executeUpdate(attendanceQuery);
                                System.out.println("Return = " + attended);
                                showAttendance();
                            }
                        }catch(SQLException sql){
                            sql.printStackTrace();
                        }
                    }else{
                        
                    }
                }
                    database.connection.close();
            }catch (SQLException sql){
               sql.printStackTrace();
               System.out.println();
            }
        }
}
    
    //*******************************************************//
    
    public void showAttendance(){
        ArrayList<Attendance> attendanceList = getAttendanceRecord();
        DefaultTableModel model = (DefaultTableModel)attendanceTable.getModel();
        Object[] row = new Object[7];
        for(int x = 0; x < attendanceList.size(); x++){
            row[0] = attendanceList.get(x).getFirstName();
            row[1] = attendanceList.get(x).getLastname();
            row[2] = attendanceList.get(x).getCourse();
            row[3] = attendanceList.get(x).getYear();
            row[4] = attendanceList.get(x).getBlock();
            row[5] = attendanceList.get(x).getTime();
            row[6] = attendanceList.get(x).getDate();
            model.addRow(row);
        }
    }
    
    public ArrayList<Attendance> getAttendanceRecord(){
        ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
        try{
            database.connect();
            Statement statement = database.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `students` JOIN `"+generate.eventsTableName(EventsSelector.selectedEvent)+"` ON "+
                                                    " `students`.`id` = `"+generate.eventsTableName(EventsSelector.selectedEvent)+"`.`student` " );
            System.out.println("SELECT * FROM `students` JOIN `"+generate.eventsTableName(EventsSelector.selectedEvent)+"` ON `students`.`id` = `"+generate.eventsTableName(EventsSelector.selectedEvent)+"`.`student` " );
            while(resultSet.next()){
                Attendance attendance;
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastname");
                int tempCourse = resultSet.getInt("course");
                String courseName = "";
                if (tempCourse == 1){
                    courseName = "BSCS";
                }else if (tempCourse == 2){
                    courseName = "BAT";
                }else if (tempCourse == 3){
                    courseName = "BEED";
                }else if (tempCourse == 4){
                    courseName = "BSED";
                }
                int year = resultSet.getInt("year");
                String block = resultSet.getString("block");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                attendance = new Attendance(firstName, lastName, courseName, year, block, time, date);
                attendanceList.add(attendance);
            }
        }catch(SQLException sql){
            sql.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return attendanceList;
    }
    
    public static void main() {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceRecorder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton attendanceButton;
    private javax.swing.JPanel attendanceSection;
    private javax.swing.JTable attendanceTable;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel biometricsSection;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel eventName;
    private javax.swing.JButton fingerPrint;
    private javax.swing.JPanel footerSection;
    private javax.swing.JPanel headerSection;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel systemName;
    private javax.swing.JLabel textArea;
    private javax.swing.JLabel windowName;
    // End of variables declaration//GEN-END:variables
}
