package com.exadel.discount.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Setter
@ConfigurationProperties(prefix = "cloud.aws.s3.credentials")
@Configuration
@Slf4j
public class AmazonConfig {
    private final String NOT_VALID_SIGNATURE = "SignatureDoesNotMatch";
    private String ACCESS_KEY;
    private String SECRET_KEY;

    @Bean
    public AmazonS3 generateAmazonS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    @PostConstruct
    public void verifyCredentials() {
        try {
            AmazonS3 s3Client = generateAmazonS3Client();
            s3Client.getS3AccountOwner();
        } catch (AmazonS3Exception exception) {
            if (StringUtils.equals(exception.getErrorCode(), NOT_VALID_SIGNATURE)) {
                log.error("Exception stack trace: ", exception);
                throw exception;
            } else {
                log.warn("Exception stack trace: ", exception);
            }
        } catch (Exception exception) {
            log.error("Exception stack trace: ", exception);
            throw exception;
        }
    }
}