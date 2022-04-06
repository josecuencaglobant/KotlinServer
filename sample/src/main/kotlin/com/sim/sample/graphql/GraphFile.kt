package com.sim.sample.graphql

import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

data class Widget(val id: Int, val value: String)

@Component
class WidgetQuery : Query {
    fun widget(id: Int): Widget = Widget(id,"WidgetQuery")
}

@Component
class WidgetMutation : Mutation {
    fun updateWidget(id: Int, value: String): Boolean = true
}
