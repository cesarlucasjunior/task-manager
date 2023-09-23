package com.cesarlucasjunior.taskmanager.adapter.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

@Configuration
class SqsConfiguration {

    @Value("\${sqs.endpoint}")
    private lateinit var sqsEndpoint:String

    @Value("\${aws.access.key}")
    private lateinit var accessKey: String

    @Value("\${aws.secret.key}")
    private lateinit var secretKey: String

    @Value("\${aws.region}")
    private lateinit var awsRegion: String

    @Bean
    fun sqsClient(): SqsClient {
        return SqsClient.builder()
            .region(Region.of(awsRegion))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
            .endpointOverride(URI.create(sqsEndpoint))
            .build()
    }
}