package com.igc.reporter.annotations

/**
 * 声明Event参数
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class EventParam(val key: String)