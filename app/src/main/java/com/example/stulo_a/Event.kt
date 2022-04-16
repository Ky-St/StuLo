package com.example.stulo_a

import java.io.Serializable

class Event(employer: String, salary: String, hours: String, result: String, calendar: String)
    : Serializable
{
    var employer: String = ""
    var salary: String = ""
    var hours: String = ""
    var result: String = ""
    var calendar: String = ""

    init {
        this.employer = employer
        this.salary = salary
        this.hours = hours
        this.result = result
        this.calendar = calendar
    }
}