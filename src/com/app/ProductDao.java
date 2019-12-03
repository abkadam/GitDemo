package com.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.security.auth.message.callback.SecretKeyCallback.Request;

public class ProductDao {
	
	public static Connection getConnection()
	{
		
		
		 
	        Connection con=null;  
	        try{  
	            Class.forName("com.mysql.jdbc.Driver");  
	            System.out.println("Driver found");
	            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","welcome");
	            System.out.println("Connection Established");
	        }catch(Exception e){System.out.println(e);}  
	        return con;  	
	}
	
	public static int saveProduct(Product p)
	{
		 int status=0;  
	        try{  
	            Connection con=ProductDao.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "insert into product(title,author,publisher,descriptions,price,image) values (?,?,?,?,?,?)");
	            ResultSet rs=null;
	           FileInputStream fis=null;
	           File image=new File(p.getBase64Image());
	            
	            
	            ps.setString(1, p.getTitle());
	            ps.setString(2, p.getAuthor());
	            ps.setString(3, p.getPublisher());
	            ps.setString(4, p.getDescription());
	            ps.setDouble(5, p.getPrice());
	            fis=new FileInputStream(image);
	            ps.setBinaryStream(6, (InputStream)fis,(int) (image.length()));
	            
	            status=ps.executeUpdate();
	            con.close();
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	            
		
		
		return status;	
	}
	
	

}
