package br.com.dv

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class YouthExchangeController(private val azureTableStorageService: AzureTableStorageService) {

    @PostMapping("/youth-exchange")
    fun saveYouthExchangeData(@RequestBody data: YouthExchange): ResponseEntity<YouthExchange> {
        azureTableStorageService.saveEntity(data)
        return ResponseEntity.status(HttpStatus.CREATED).body(data)
    }

}
