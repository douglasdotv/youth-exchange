package br.com.dv

import com.azure.data.tables.TableClient
import com.azure.data.tables.TableClientBuilder
import com.azure.data.tables.models.TableEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AzureTableStorageService(
    @Value("\${azure.storage.accountName}") private val accountName: String,
    @Value("\${azure.storage.accountKey}") private val accountKey: String,
    @Value("\${azure.storage.tableName}") private val tableName: String
) {
    private val tableClient: TableClient = TableClientBuilder()
        .connectionString("DefaultEndpointsProtocol=https;AccountName=$accountName;AccountKey=$accountKey;EndpointSuffix=core.windows.net")
        .tableName(tableName)
        .buildClient()

    fun saveEntity(data: YouthExchange): TableEntity {
        val partitionKey = data.partitionKey
        val rowKey = data.rowKey

        val entity = TableEntity(partitionKey, rowKey)
            .addProperty("playerName", data.name)
            .addProperty("age", data.age)
            .addProperty("nationality", data.country)
            .addProperty("hp", data.hp)
            .addProperty("firstHpSkill", data.firstHpSkill)
            .addProperty("secondHpSkill", data.secondHpSkill)
            .addProperty("lp", data.lp)
            .addProperty("firstLpSkill", data.firstLpSkill)
            .addProperty("secondLpSkill", data.secondLpSkill)
            .addProperty("trainingSpeed", data.trainingSpeed)
            .addProperty("totalSkillBalls", data.totalBalls)
            .addProperty("owner", data.owner)

        data.stats.forEach { (key, value) ->
            entity.addProperty(key, value)
        }

        tableClient.upsertEntity(entity)

        return entity
    }
}
