package com.nooomer.seum_leaderboards.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig(
    var jsonConfig: JsonConfig
): RestTemplate() {

    override fun setMessageConverters(messageConverters: MutableList<HttpMessageConverter<*>>) {
        val converter = KotlinSerializationJsonHttpMessageConverter(jsonConfig.setSerializableParams())
        messageConverters.forEachIndexed { index, httpMessageConverter ->
            if (httpMessageConverter is KotlinSerializationJsonHttpMessageConverter) {
                messageConverters[index] = converter
                return
            }
        }
        super.setMessageConverters(messageConverters)
    }
}