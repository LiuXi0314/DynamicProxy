package com.example.dynamicproxy.reporter.param

import com.example.dynamicproxy.reporter.IGCReporter
import com.example.dynamicproxy.reporter.annotations.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Method

/**
 * 参数解析器
 */
object ParamParser {

    /**
     * 解析method中的信息 和 参数值集合args
     */
    fun parse(method: Method, args: Array<out Any>): Parameter {

        val builder: Parameter.Builder = Parameter.Builder()

        //解析类体上的注解
        parseClassAnnotations(method.declaringClass.annotations, builder)

        //解析方法上的注解
        parseMethodAnnotations(method.annotations, builder)

        //解析方法参数中的注解
        parseMethodParameterAnnotations(method.parameterAnnotations, args, builder)

        return builder.build()

    }

    /**
     * 解析类体上的注解
     */
    private fun parseClassAnnotations(annotations: Array<Annotation>, build: Parameter.Builder) {
        annotations.forEach {
            when (it) {
                is EventName -> build.eventName = it.name
                is EventReporter -> build.reporterClass = it.clazz
            }
        }

        if (build.reporterClass == null) {
            //设置默认Event上报者
            build.reporterClass = IGCReporter.DEFAULT_REPORTER_KCLASS
        }

    }

    /**
     * 解析方法注解
     */
    private fun parseMethodAnnotations(annotations: Array<Annotation>, build: Parameter.Builder) {
        annotations.forEach {
            when (it) {
                is EventAction -> {
                    build.eventAction = it.action
                }
            }
        }
    }

    /**
     * 解析方法参数中的所有注解
     */
    private fun parseMethodParameterAnnotations(
        annotationsArray: Array<Array<Annotation>>,
        args: Array<out Any>,
        build: Parameter.Builder
    ) {

        val parameterCount = annotationsArray.size
        val argsCount = args.size

        validateParameterCount(parameterCount, argsCount)

        if (parameterCount > 0) {

            for (i in 0 until parameterCount) {
                val parameterAnnotations = annotationsArray[i]

                parameterAnnotations.forEach {
                    parseParameterAnnotationItem(it, args[i], build)
                }
            }
        }
    }

    /**
     * 参数安全性检查
     */
    private fun validateParameterCount(parameterCount: Int, argsCount: Int) {
        if (parameterCount != argsCount) {
            throw IllegalArgumentException(
                "Argument count ($argsCount) doesn't match expected count ($parameterCount)"
            )
        }
    }

    /**
     * 解析参数中的注解
     */
    private fun parseParameterAnnotationItem(annotation: Annotation, arg: Any, build: Parameter.Builder) {

        when (annotation) {

            is EventParam -> build.eventMap[annotation.key] = arg

            is EventParamExt -> {
                //解析JSON数据为map
                parseJsonToMap(arg, build)
            }
            is EventParamMap -> {
                //arg 必须是Map<out String, Any?>
                (arg as? Map<out String, Any?>)?.let {
                    build.eventMap.putAll(it)
                }
            }

        }
    }


    /**
     * 解析JSON数据
     */
    private fun parseJsonToMap(json: Any, build: Parameter.Builder) {
        if (json !is String) {
            throw IllegalArgumentException("The parameter json must be ${String::class.java},can not be ${json::class.java}")
        }
        try {
            val jsonObject = JSONObject(json)
            jsonObject.keys().forEach {
                //数据直接存入build中
                build.eventMap[it] = jsonObject.opt(it)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}