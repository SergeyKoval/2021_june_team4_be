package com.exadel.discount.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Configuration
public class AmazonConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String CLOUD_ACCESS_KEY;
    @Value("${cloud.aws.credentials.secret-key}")
    private String CLOUD_SECRET_KEY;

    @Bean
    public AmazonS3 generateAmazonS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(CLOUD_ACCESS_KEY, CLOUD_SECRET_KEY);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }
}
