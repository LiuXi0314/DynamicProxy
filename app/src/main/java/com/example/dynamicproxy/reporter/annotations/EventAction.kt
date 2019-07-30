package com.example.dynamicproxy.reporter.annotations

/**
 * 声明Event行为
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class EventAction(val action: String)