package com.igc.reporter.annotations


/**
 * 声明Event行为
 */
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class EventAction(val action: String)