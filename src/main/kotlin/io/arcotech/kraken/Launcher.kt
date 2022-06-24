package io.arcotech.kraken

import io.quarkus.scheduler.Scheduled
import io.vertx.core.AbstractVerticle
import org.eclipse.microprofile.config.inject.ConfigProperty
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class Launcher: AbstractVerticle() {

    @Inject
    lateinit  var sqs: SqsClient

    @ConfigProperty(name = "queue.url")
    var queueUrl: String? = null

    override fun start() {
        println("started !!!123")
        println("SQS Started")
    }

    override fun stop() {
        println("Stoped")
        println("SQS Stoped")
    }

    @Scheduled(every="10s")
    fun receive() {
        val messages: List<Message> = sqs.receiveMessage { m: ReceiveMessageRequest.Builder ->
            m.maxNumberOfMessages(
                10
            ).queueUrl(queueUrl)
        }.messages()

        messages.stream().map(Message::body).forEach { println(it) }
    }




}