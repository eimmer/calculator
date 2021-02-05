package com.signanthealth.myapplication.model

enum class Operation(val display: String) {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("/"),
    MOD("%"),
    EQUALS("="),
    CLEAR("C"),
    DECIMAL(".");
}