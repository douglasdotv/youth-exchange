package br.com.dv

import com.azure.data.tables.TableClient
import com.azure.data.tables.models.TableEntity
import org.springframework.stereotype.Service

@Service
class AzureTableStorageService(private val tableClient: TableClient) {

    fun saveEntity(data: YouthExchange): TableEntity {
        val entity = createTableEntity(data)
        tableClient.upsertEntity(entity)
        return entity
    }

    private fun createTableEntity(data: YouthExchange): TableEntity {
        return TableEntity(data.partitionKey, data.rowKey).apply {
            addProperty("playerName", data.name)
            addProperty("age", data.age)
            addProperty("nationality", data.country)
            addProperty("hp", data.hp)
            addProperty("firstHpSkill", data.firstHpSkill)
            addProperty("secondHpSkill", data.secondHpSkill)
            addProperty("lp", data.lp)
            addProperty("firstLpSkill", data.firstLpSkill)
            addProperty("secondLpSkill", data.secondLpSkill)
            addProperty("trainingSpeed", data.trainingSpeed)
            addProperty("totalSkillBalls", data.totalBalls)
            addProperty("owner", data.owner)
            data.stats.forEach { (key, value) -> addProperty(key, value) }
        }
    }
    
}