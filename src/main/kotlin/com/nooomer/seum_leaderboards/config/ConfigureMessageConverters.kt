package com.nooomer.seum_leaderboards.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ConfigureMessageConverters(
    var jsonConfig: JsonConfig
) : WebMvcConfigurer {
    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val converter = KotlinSerializationJsonHttpMessageConverter(jsonConfig.setSerializableParams())
        converters.forEachIndexed { index, httpMessageConverter ->
            if (httpMessageConverter is KotlinSerializationJsonHttpMessageConverter) {
                converters[index] = converter
                return
            }
        }
    }


}