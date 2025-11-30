# kafkaWithMultipleQueueAndMultiThread
This microservice—running within a Eureka-based architecture—uses Kafka with multiple queue listeners that can be enabled or disabled dynamically based on database configuration. Each message received from Kafka is handed over to a ThreadPoolTaskExecutor for parallel processing to achieve high throughput.
