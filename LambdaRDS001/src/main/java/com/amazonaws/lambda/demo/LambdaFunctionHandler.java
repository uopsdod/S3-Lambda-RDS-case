package com.amazonaws.lambda.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.mysql.cj.jdbc.MysqlDataSource;

public class LambdaFunctionHandler implements RequestHandler<S3Event, String> {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public LambdaFunctionHandler() {}

    // Test purpose only.
    LambdaFunctionHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);
        
        execute();


        
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT ID FROM USERS");
//        ...
//        rs.close();
//        stmt.close();
//        conn.close();
        
        // Get the object from the event and show its content type
//        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
//        String key = event.getRecords().get(0).getS3().getObject().getKey();
//        try {
//            S3Object response = s3.getObject(new GetObjectRequest(bucket, key));
//            String contentType = response.getObjectMetadata().getContentType();
//            context.getLogger().log("CONTENT TYPE 123: " + contentType);
//            return contentType;
//        } catch (Exception e) {
//            e.printStackTrace();
//            context.getLogger().log(String.format(
//                "Error getting object %s from bucket %s. Make sure they exist and"
//                + " your bucket is in the same region as this function.", key, bucket));
//            throw e;
//        }
        
        return "Done";
    }

	private void execute() {
        System.out.println("execute() starts");
        MysqlDataSource dataSource = new MysqlDataSource();
        // Set dataSource Properties
        dataSource.setServerName("lambdards-stsai.c6nw5udaqhrg.us-east-1.rds.amazonaws.com");
        dataSource.setPortNumber(3306);
//        dataSource.setDatabaseName("Sam");
        dataSource.setUser("admin");
        dataSource.setPassword("XXXXXXX"); // TODO: change this 
        
        System.out.println("trying to connect to db ... ");
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("connected to DB");
        	TimeUnit.SECONDS.sleep(60);
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("execute() ends");
	}
	
	public static void main(String[] args) {
		LambdaFunctionHandler lambda = new LambdaFunctionHandler();
		System.out.println("test001");
		lambda.execute();
	}
	
	
}