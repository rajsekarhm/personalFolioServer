package com.personalfolio.voyage.store;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {
    @Value("${aws.access.key.id}")
    private String accessKeyId;

    @Value("${aws.secret.access.key}")
    private String secretAccessKey;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 config(){

          AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
                  return AmazonS3ClientBuilder.standard()
                       .withCredentials(new AWSStaticCredentialsProvider(credentials))
                         .withRegion(region)
                         .build();

//        return  S3Client.builder().region(Region.of("")).build();
    }
}
