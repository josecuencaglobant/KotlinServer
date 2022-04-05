package com.sim.sample.configuration

import com.fasterxml.classmate.TypeResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.async.DeferredResult
import springfox.documentation.builders.*
import springfox.documentation.schema.AlternateTypeRules.newRule
import springfox.documentation.schema.ModelKeyBuilder
import springfox.documentation.schema.ScalarType
import springfox.documentation.schema.WildcardType
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.util.function.Consumer


@Configuration
@EnableSwagger2
class Swagger {

    @Bean
    fun getApi(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.sim.sample.controllers"))
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/")
            .directModelSubstitute(LocalDate::class.java, String::class.java)
            .genericModelSubstitutes(ResponseEntity::class.java)
            .alternateTypeRules(
                newRule(
                    typeResolver?.resolve(
                        DeferredResult::class.java,
                        typeResolver.resolve(ResponseEntity::class.java, WildcardType::class.java)
                    ),
                    typeResolver?.resolve(WildcardType::class.java)
                )
            )
            .useDefaultResponseMessages(false)
            .globalResponses(
                HttpMethod.GET,
                listOf(
                    ResponseBuilder()
                        .code("500")
                        .description("500 message")
                        .representation(MediaType.TEXT_XML)
                        .apply(Consumer { r: RepresentationBuilder ->
                            r.model { m: ModelSpecificationBuilder ->
                                m.referenceModel { ref: ReferenceModelSpecificationBuilder ->
                                    ref.key { k: ModelKeyBuilder ->
                                        k.qualifiedModelName { q: QualifiedModelNameBuilder ->
                                            q.namespace("some:namespace")
                                                .name("ERROR")
                                        }
                                    }
                                }
                            }
                        })
                        .build()
                )
            )
            .securitySchemes(listOf(apiKey()))
            .securityContexts(listOf(securityContext()))
            .enableUrlTemplating(true)
//            .additionalModels(typeResolver?.resolve(AdditionalModel::class.java))
    }

    @Autowired
    private val typeResolver: TypeResolver? = null

    private fun apiKey(): ApiKey? {
        return ApiKey("mykey", "api_key", "header")
    }

    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("/anyPath.*"))
            .build()
    }

    fun defaultAuth(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(
            SecurityReference("mykey", authorizationScopes)
        )
    }

    @Bean
    fun security(): SecurityConfiguration? {
        return SecurityConfigurationBuilder.builder()
            .clientId("test-app-client-id")
            .clientSecret("test-app-client-secret")
            .realm("test-app-realm")
            .appName("test-app")
            .scopeSeparator(",")
            .additionalQueryStringParams(null)
            .useBasicAuthenticationWithAccessCodeGrant(false)
            .enableCsrfSupport(false)
            .build()
    }

    @Bean
    fun uiConfig(): UiConfiguration? {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(false)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .showCommonExtensions(false)
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build()
    }


}