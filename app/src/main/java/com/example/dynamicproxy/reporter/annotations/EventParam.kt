package com.example.dynamicproxy.reporter.annotations

/**
 * 声明Event参数
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class EventParam(val key: String)