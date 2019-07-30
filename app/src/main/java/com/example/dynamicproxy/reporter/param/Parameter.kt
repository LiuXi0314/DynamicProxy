package com.example.dynamicproxy.reporter.param

import com.example.dynamicproxy.reporter.IReporter
import kotlin.reflect.KClass

/**
 * Event参数
 */
class Parameter(

    /**
     * 事件名称
     */
    val eventName: String?,

    /**
     * 事件行为
     */
    val eventAction: String?,

    /**
     * 事件参数集合
     */
    val eventMap: Map<String, Any?>,

    /**
     * 上报者类的Kotlin反射类
     */
    val reporterClass: KClass<out IReporter>

) {
    class Builder {

        var eventName: String? = ""
        var eventAction: String? = ""
        var eventMap: HashMap<String, Any?> = HashMap()
        var reporterClass: KClass<out IReporter>? = null

        fun build(): Parameter {

            if (reporterClass == null) {
                throw IllegalArgumentException(
                    "The parameter of 'reporterClass' can not be null," +
                            "It is recommended to use 'IGCReporter.setDefaultReport()' to set the default value."
                )
            }

            return Parameter(eventName, eventAction, eventMap, reporterClass!!)

        }
    }
}