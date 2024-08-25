package br.com.dv

data class YouthExchange(
    val partitionKey: String,
    val rowKey: String,
    val name: String,
    val age: Int,
    val country: String,
    val hp: Int,
    val firstHpSkill: String,
    val secondHpSkill: String,
    val lp: Int,
    val firstLpSkill: String,
    val secondLpSkill: String,
    val trainingSpeed: Int,
    val totalBalls: String,
    val owner: String,
    val stats: Map<String, Int>
)
