package com.example.dynamicproxy.reporter.annotations

/**
 * 声明Event参数集合
 * 用于标记Map类型的参数
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class EventParamMap