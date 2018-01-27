

package swingbiometricsrecord;

import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BiometricRegistration extends javax.swing.JFrame {
    Database database = new Database();
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
    
    public BiometricRegistration() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        windowTitle = new javax.swing.JLabel();
        btnImg = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        testButton = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        studentIdBox = new javax.swing.JTextField();
        serachButton = new javax.swing.JButton();
        studentIdLabel = new javax.swing.JLabel();
        firstNameBanner = new javax.swing.JLabel();
        firstName = new javax.swing.JLabel();
        lastNameBanner = new javax.swing.JLabel();
        lastName = new javax.swing.JLabel();
        courseBanner = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        yearBanner = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        blockBanner = new javax.swing.JLabel();
        block = new javax.swing.JLabel();
        textArea = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        windowTitle.setFont(new java.awt.Font("Open Sans Extrabold", 1, 24)); // NOI18N
        windowTitle.setText("Finger Biometrics Record");

        openButton.setText("OPEN");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        registerButton.setText("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        testButton.setText("TEST");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        serachButton.setText("SEARCH");
        serachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serachButtonActionPerformed(evt);
            }
        });

        studentIdLabel.setText("Student ID:");

        firstNameBanner.setText("First Name:");

        lastNameBanner.setText("Last Name:");

        courseBanner.setText("Course:");

        yearBanner.setText("Year:");

        blockBanner.setText("Block");

        textArea.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N

        backButton.setText("LOGOUT");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(openButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(windowTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(firstNameBanner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(studentIdLabel)
                                .addGap(4, 4, 4)
                                .addComponent(studentIdBox, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(serachButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(firstName)
                                .addGap(50, 50, 50)
                                .addComponent(lastNameBanner)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastName)
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textArea)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(courseBanner)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(course)
                                        .addGap(12, 12, 12)
                                        .addComponent(yearBanner)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(year)
                                        .addGap(12, 12, 12)
                                        .addComponent(blockBanner)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(block)))))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(windowTitle)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentIdBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serachButton)
                    .addComponent(studentIdLabel))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameBanner)
                    .addComponent(firstName)
                    .addComponent(lastNameBanner)
                    .addComponent(lastName)
                    .addComponent(courseBanner)
                    .addComponent(course)
                    .addComponent(yearBanner)
                    .addComponent(year)
                    .addComponent(blockBanner)
                    .addComponent(block))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textArea)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(openButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(testButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton)
                        .addGap(86, 86, 86))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serachButtonActionPerformed
        String selectThisStudent = "SELECT * FROM `students` WHERE `studentId` = '"+studentIdBox.getText()+"' ";
        try{
            database.connect();
            Statement statement = database.connection.createStatement();
            ResultSet result = statement.executeQuery(selectThisStudent);
            if(result.next()){
                id = result.getInt("id");
                firstName.setText(result.getString("firstName"));
                lastName.setText(result.getString("lastName"));
                String courseName = "";
                int courseNumber = result.getInt("course");
                if (courseNumber == 1){
                    courseName = "BSCS";
                }else if (courseNumber == 2){
                    courseName = "BAT";
                }else if (courseNumber == 3){
                    courseName = "BEED";
                }else if(courseNumber == 4){
                    courseName = "BSED";
                }
                course.setText(courseName);
                year.setText(Integer.toString(result.getInt("year")));
                block.setText(result.getString("block"));
            }else{
                JOptionPane.showMessageDialog(null, "Student does'nt exist");
            }
        }catch(SQLException sql){
            sql.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        studentIdBox.setText("");
    }//GEN-LAST:event_serachButtonActionPerformed

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
	btnImg.resize(fpWidth, fpHeight);
	mbStop = false;
	workThread = new WorkThread();
	workThread.start();
	textArea.setText("Open succ!");
    }//GEN-LAST:event_openButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        if (firstName.getText().equals("")){
            textArea.setText("No student to be register.");
        }else{
            if(0 == mhDevice){
                textArea.setText("Please Open device first!");
                return;
            }
            if(!bRegister){
                enroll_idx = 0;
                bRegister = true;
                textArea.setText("Please your finger 3 times!");
            }
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        if (!test){
            test = true;
            bRegister = false;
            firstName.setText("");
            lastName.setText("");
            course.setText("");
            year.setText("");
            block.setText("");
        }
        textArea.setText("Testing ready, please place your right thumb.");
    }//GEN-LAST:event_testButtonActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        FreeSensor();
        textArea.setText("Close succ!");
        System.out.println("Device Off");
    }//GEN-LAST:event_btnCloseActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
        Authentication.main();
    }//GEN-LAST:event_backButtonActionPerformed

    private void FreeSensor()
	{
            mbStop = true;
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
	btnImg.setIcon(new ImageIcon(ImageIO.read(new File("fingerprint.bmp"))));
    } catch (IOException e) {
	e.printStackTrace();
    }
}
   
private void OnExtractOK(byte[] template, int len){
    if(bRegister){
	int[] fid = new int[1];
	int[] score = new int [1];
        int ret = FingerprintSensorEx.DBIdentify(mhDB, template, fid, score);
        if (ret == 0){
            textArea.setText("the finger already enroll by " + fid[0] + ",cancel enroll");
            bRegister = false;
            enroll_idx = 0;
            return;
        }
        if (enroll_idx > 0 && FingerprintSensorEx.DBMatch(mhDB, regtemparray[enroll_idx-1], template) <= 0){
            textArea.setText("please press the same finger 3 times for the enrollment");
            return;
        }
        System.arraycopy(template, 0, regtemparray[enroll_idx], 0, 2048);
        enroll_idx++;
        if (enroll_idx == 3) {
            int[] _retLen = new int[1];
            _retLen[0] = 2048;
            byte[] regTemp = new byte[_retLen[0]];
        
            if (0 == (ret = FingerprintSensorEx.DBMerge(mhDB, regtemparray[0], regtemparray[1], regtemparray[2], regTemp, _retLen)) &&
            0 == (ret = FingerprintSensorEx.DBAdd(mhDB, iFid, regTemp))) {
            iFid++;
            cbRegTemp = _retLen[0];
            System.arraycopy(regTemp, 0, lastRegTemp, 0, cbRegTemp);
            String biometricInsert = "UPDATE `students` SET `rightThumb` = '"+FingerprintSensorEx.BlobToBase64(regTemp, 2048)+"' WHERE `id` = "+id+" ";
            System.out.println(biometricInsert);
                try {
                    database.connect();
                    Statement statement = database.connection.createStatement();
                    int updateFinger = statement.executeUpdate(biometricInsert);
                    if (updateFinger == 0){
                        System.out.println("right thumb data inserted");
                    }
                    database.connection.close();
                }catch (SQLException sql){
                    sql.printStackTrace();
                }
            textArea.setText("Registration Success");
            bRegister = false;
            firstName.setText("");
            lastName.setText("");
            course.setText("");
            year.setText("");
            block.setText("");
            } else {
                textArea.setText("Registration fail please try again");
                enroll_idx = 0;
            }
        } else {
            textArea.setText("You need to press the " + (3 - enroll_idx) + " times fingerprint");
            
        }
    }
    else{
	if(test){
            try {
                database.connect();
                Statement statement = database.connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM `students`");
                int score = 0;
                while (resultSet.next()){
                    byte[] templateFromDb = new byte[2048];
                    String base64Template = resultSet.getString("rightThumb");
                    FingerprintSensorEx.Base64ToBlob(base64Template, templateFromDb, 2048);
                    score = FingerprintSensorEx.DBMatch(mhDB, templateFromDb, template);
                    System.out.println(resultSet.getString("firstName") + " " + resultSet.getString("lastName") + " : " + score);
                    if (score > 0){
                        byte[] paramValueX = new byte[4];
                        FingerprintSensorEx.SetParameters(mhDevice, 104, paramValueX, 1);
                        String firstNameInfo = resultSet.getString("firstName");
                        String lastNameInfo = resultSet.getString("lastName");
                        int tempCourse = resultSet.getInt("course");
                        String courseString ="";
                        if (tempCourse == 1){
                            courseString = "BSCS";
                        }else if(tempCourse == 2){
                            courseString = "BAT";
                        }else if(tempCourse == 3){
                        courseString = "BEED";
                        }else if(tempCourse == 4){
                            courseString = "BSED";
                        }
                        String yearInfo = Integer.toString(resultSet.getInt("year"));
                        String blockInfo = resultSet.getString("block");
                        firstName.setText(firstNameInfo);
                        lastName.setText(lastNameInfo);
                        course.setText(courseString);
                        year.setText(yearInfo);
                        block.setText(blockInfo);
                        textArea.setText("");
                    }
                }
                    database.connection.close();
            }catch (SQLException sql){
               sql.printStackTrace();
            }
            test = false;
        }
    }
}

    public static void main() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BiometricRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel block;
    private javax.swing.JLabel blockBanner;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnImg;
    private javax.swing.JLabel course;
    private javax.swing.JLabel courseBanner;
    private javax.swing.JLabel firstName;
    private javax.swing.JLabel firstNameBanner;
    private javax.swing.JLabel lastName;
    private javax.swing.JLabel lastNameBanner;
    private javax.swing.JButton openButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton serachButton;
    private javax.swing.JTextField studentIdBox;
    private javax.swing.JLabel studentIdLabel;
    private javax.swing.JButton testButton;
    private javax.swing.JLabel textArea;
    private javax.swing.JLabel windowTitle;
    private javax.swing.JLabel year;
    private javax.swing.JLabel yearBanner;
    // End of variables declaration//GEN-END:variables
}