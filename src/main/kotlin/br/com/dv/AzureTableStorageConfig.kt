package br.com.dv

import com.azure.data.tables.TableClient
import com.azure.data.tables.TableClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AzureTableStorageConfig(
    @Value("\${azure.storage.accountName}") private val accountName: String,
    @Value("\${azure.storage.accountKey}") private val accountKey: String,
    @Value("\${azure.storage.tableName}") private val tableName: String
) {

    @Bean
    fun tableClient(): TableClient {
        return TableClientBuilder()
            .connectionString("DefaultEndpointsProtocol=https;AccountName=$accountName;AccountKey=$accountKey;EndpointSuffix=core.windows.net")
            .tableName(tableName)
            .buildClient()
    }

}
